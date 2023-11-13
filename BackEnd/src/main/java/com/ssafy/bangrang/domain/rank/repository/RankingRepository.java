package com.ssafy.bangrang.domain.rank.repository;

import com.ssafy.bangrang.domain.map.model.vo.RegionType;
import com.ssafy.bangrang.domain.rank.entity.Ranking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;

import java.time.LocalTime;
import java.util.List;

public interface RankingRepository extends JpaRepository<Ranking, Long> {

    @Query("SELECT r FROM Ranking r WHERE r.appMember = :appMember AND FUNCTION('DATE', r.createdAt) = FUNCTION('DATE', :givenDateTime)")
    List<Ranking> findTodayRankingByAppMember(@Param("appMember") Long appMember, @Param("givenTime") LocalTime givenTime);

    @Query("SELECT r FROM Ranking r WHERE r.regionType = :regionType AND FUNCTION('DATE', r.createdAt) = FUNCTION('DATE', :givenDateTime) ORDER BY r.rank ASC")
    List<Ranking> findTodayRanking10(@Param("regionType")RegionType regionType, @Param("givenTime") LocalTime givenTime);

    @Query("SELECT r FROM Ranking r WHERE r.regionType = :regionType AND FUNCTION('DATE', r.createdAt) = FUNCTION('DATE', :givenDateTime) AND r.rank BETWEEN :start AND :end ORDER BY r.rank ASC")
    List<Ranking> findTodayRankingTopDownAppMember(@Param("regionType")RegionType regionType, @Param("givenTime") LocalTime givenTime, @Param("start") int start, @Param("end") int end);
}
