package com.ssafy.bangrang.domain.rank.repository;

import com.ssafy.bangrang.domain.rank.entity.Ranking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RankingRepository extends JpaRepository<Ranking, Long> {
}
