package com.ssafy.bangrang.domain.rank.service;

import com.ssafy.bangrang.domain.rank.api.response.RegionDto;
import org.springframework.security.core.userdetails.UserDetails;

public interface RankingService {

    RegionDto getRankingAll(UserDetails userDetails);
}
