package com.ssafy.bangrang.domain.event.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.bangrang.domain.event.api.request.EventPutDto;
import com.ssafy.bangrang.domain.event.api.request.EventSignUpDto;
import com.ssafy.bangrang.domain.event.api.response.EventGetDto;
import com.ssafy.bangrang.domain.event.api.response.GetEventAllResponseDto;
import com.ssafy.bangrang.domain.event.entity.Event;
import com.ssafy.bangrang.domain.event.repository.EventRepository;
import com.ssafy.bangrang.domain.inquiry.entity.Comment;
import com.ssafy.bangrang.domain.member.entity.WebMember;
import com.ssafy.bangrang.domain.member.repository.WebMemberRepository;
import com.ssafy.bangrang.domain.member.service.WebMemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class EventWebService {

    private final EventRepository eventRepository;
    private final WebMemberRepository webMemberRepository;
    private final ObjectMapper objectMapper;

    @Value("${cloud.naver.client_id}")
    String client_id;

    @Value("${cloud.naver.client_secret}")
    String client_secret;



    @Transactional
    public void saveEvent(EventSignUpDto eventSignUpDto, UserDetails userDetails) throws JsonProcessingException {
        log.info("이벤트 저장 시작");
        WebMember webMember = webMemberRepository.findById(userDetails.getUsername())
                .orElseThrow(()->new IllegalArgumentException("찾을수 없어요!!"));

        HttpHeaders headers = new HttpHeaders();
        headers.add("X-NCP-APIGW-API-KEY-ID", client_id);
        headers.add("X-NCP-APIGW-API-KEY", client_secret);
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

        String query = eventSignUpDto.getAddress();
        String url = "https://naveropenapi.apigw.ntruss.com/map-geocode/v2/geocode?query="+query;

        log.info("네이버 Geocoding api요청 시작");
        ResponseEntity<String> response =
                restTemplate.exchange(url,
                        HttpMethod.GET,
                        new HttpEntity<>(null, headers),
                        String.class);
        String body = response.getBody();
        log.info("네이버 Geocoding api요청 완료");


        JsonNode root = objectMapper.readTree(body);
        JsonNode addresses = root.get("addresses");
        if (addresses.isArray() && addresses.size() > 0) {
            JsonNode firstAddress = addresses.get(0);
            double latitude = Double.parseDouble(firstAddress.get("x").asText());
            double longitude = Double.parseDouble(firstAddress.get("y").asText());

            Event event = Event.builder()
                    .title(eventSignUpDto.getTitle())
                    .subTitle(eventSignUpDto.getSubTitle())
                    .address(eventSignUpDto.getAddress())
                    .content(eventSignUpDto.getContent())
                    .eventUrl(eventSignUpDto.getEventUrl())
                    .longitude(longitude)
                    .latitude(latitude)
                    .startDate(LocalDateTime.parse(eventSignUpDto.getStartDate())) // 수정된 부분
                    .endDate(LocalDateTime.parse(eventSignUpDto.getEndDate())) // 수정된 부분
                    .webMember(webMember)
                    .build();

            event = eventRepository.save(event);
        }





    }

    @Transactional
    public void updateEvent(Long eventIdx,EventPutDto eventPutDto){

        Event event = eventRepository.findByIdx(eventIdx)
                .orElseThrow(()->new IllegalArgumentException("찾을 수 없다"));

        event.update(eventPutDto);
    }

    public void deleteEvent(Long eventIdx,UserDetails userDetails){
        Optional<Event> event = eventRepository.findByIdx(eventIdx);
        if (event.isPresent()) {
            eventRepository.delete(event.get());
        } else {
            throw new IllegalArgumentException("Comment not found with ID: " + eventIdx);
        }
    }

    public List<EventGetDto> getAllEvents(Long webMemberIdx) {

        WebMember webMember = webMemberRepository.findById(webMemberIdx)
                .orElseThrow(()->new IllegalArgumentException("찾을수없다"));

        List<EventGetDto> eventList = eventRepository.findAllByWebMember(webMember)
                .stream()
                .map(e-> EventGetDto.builder()
                        .title(e.getTitle())
                        .content(e.getContent())
                        .address(e.getAddress())
                        .startDate(e.getStartDate())
                        .endDate(e.getEndDate())
                        .eventUrl(e.getEventUrl())
                        .build())
                .collect(Collectors.toList());

        return eventList;
    }
}
