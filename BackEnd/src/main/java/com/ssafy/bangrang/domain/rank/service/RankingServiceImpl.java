package com.ssafy.bangrang.domain.rank.service;

import com.ssafy.bangrang.domain.map.model.vo.RegionType;
import com.ssafy.bangrang.domain.member.entity.AppMember;
import com.ssafy.bangrang.domain.member.repository.AppMemberRepository;
import com.ssafy.bangrang.domain.rank.api.response.MyRegionDto;
import com.ssafy.bangrang.domain.rank.api.response.RankList;
import com.ssafy.bangrang.domain.rank.api.response.RegionDto;
import com.ssafy.bangrang.domain.rank.entity.Ranking;
import com.ssafy.bangrang.domain.rank.repository.RankingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class RankingServiceImpl implements RankingService{

    private final RankingRepository rankingRepository;
    private final AppMemberRepository appMemberRepository;

    @Override
    public RegionDto getRankingAll(UserDetails userDetails) {

        AppMember appMember = appMemberRepository.findById(userDetails.getUsername()).orElseThrow();

        List<Ranking> memberRankings = rankingRepository.findTodayRankingByAppMember(appMember.getIdx(), LocalTime.now());
        HashMap<RegionType, Long> myrakingInRegion = new HashMap<>();
        List<MyRegionDto> myRatings = memberRankings
                .stream()
                .map(ranking -> MyRegionDto.builder()
                        .region(ranking.getRegionType())
                        .rate(ranking.getRank())
                        .percent(ranking.getPercent())
                        .build())
                .collect(Collectors.toList());
        int rating = 0;
        for(Ranking ranking: memberRankings){
            myrakingInRegion.put(ranking.getRegionType(), ranking.getRank());

            if (ranking.getRegionType() == RegionType.KOREA) {
                rating = ranking.getRating().intValue(); // 혹은 다른 필드 값으로 설정
//                break; // rating 값을 찾았으면 루프 종료
            }
        }

        RegionDto result = RegionDto.builder()
                .myRatings(myRatings)
                .rating(rating)
                .build();

//        HashMap<RegionType, List<RankList>> top10Ranking = new HashMap<>();
        for (RegionType region : RegionType.values()) {
            List<RankList> regionRankings = rankingRepository.findTodayRanking10(region, LocalTime.now())
                    .stream()
                    .map(ranking -> RankList.builder()
                            .userNickname(ranking.getAppMember().getNickname())
                            .userImg(ranking.getAppMember().getImgUrl())
                            .percent(ranking.getPercent())
                            .build())
                    .collect(Collectors.toList());

//            List<RankList> regionMyRankingTopDwons = rankingRepository.findTodayRankingTopDownAppMember(region, LocalTime.now(), myrakingInRegion.get(region)-1, myrakingInRegion.get(region)+1);

        }

        return result;
    }
}
