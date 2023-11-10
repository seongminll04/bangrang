package com.ssafy.bangrang.domain.rank.service;

import com.ssafy.bangrang.domain.rank.repository.RankingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class RankingServiceImpl implements RankingService{

    private final RankingRepository rankingRepository;
}
