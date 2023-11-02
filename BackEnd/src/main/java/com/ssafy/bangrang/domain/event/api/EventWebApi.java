package com.ssafy.bangrang.domain.event.api;


import com.ssafy.bangrang.domain.event.api.request.EventPutDto;
import com.ssafy.bangrang.domain.event.api.request.EventSignUpDto;
import com.ssafy.bangrang.domain.event.api.response.EventGetDto;
import com.ssafy.bangrang.domain.event.service.EventWebService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/web/event")
@RequiredArgsConstructor
@Slf4j
public class EventWebApi {

    private final EventWebService eventWebService;

    @PostMapping(path = "/regist", consumes = {"multipart/form-data"})
    public ResponseEntity<?> postWebEvent(@RequestBody @Valid EventSignUpDto eventSignUpDto,
                                          @AuthenticationPrincipal UserDetails userDetails
    ) {
        log.info("웹 이벤트 생성중");
        try {
            eventWebService.saveEvent(eventSignUpDto, userDetails);
            return ResponseEntity.ok().body("이벤트 생성 완료");
        } catch (Exception e){
            log.info("작성 실패");
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{eventIdx}")
    public ResponseEntity<?> putEvent(@RequestBody @Valid EventPutDto eventPutDto, @PathVariable Long eventIdx) {
        log.info("답변 수정");
        try {
            eventWebService.updateEvent(eventIdx, eventPutDto);
            return ResponseEntity.ok().body("이벤트 수정완료");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/delete/{eventIdx}")
    public ResponseEntity<?> deleteEvent(@PathVariable Long eventIdx,
                                         @AuthenticationPrincipal UserDetails userDetails) {
        log.info("이벤트 삭제");
        try {
            eventWebService.deleteEvent(eventIdx,userDetails);
            return ResponseEntity.ok().body("이벤트 삭제 완료");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{webMemberIdx}")
    public ResponseEntity<?> getWebMemberEvents(@PathVariable Long webMemberIdx) {
        log.info("웹멤버의 이벤트 목록 보기");
        try {
            List<EventGetDto> eventList = eventWebService.getAllEvents(webMemberIdx);
            return ResponseEntity.ok().body(eventList);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
