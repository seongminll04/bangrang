package com.ssafy.bangrang.global.batch;

import com.ssafy.bangrang.domain.map.entity.KoreaBorderArea;
import com.ssafy.bangrang.domain.map.entity.MemberMapArea;
import com.ssafy.bangrang.domain.map.model.vo.RegionType;
import com.ssafy.bangrang.domain.map.repository.KoreaBorderAreaRepository;
import com.ssafy.bangrang.domain.map.repository.MemberMapAreaRepository;
import jakarta.persistence.EntityManagerFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.locationtech.jts.geom.Geometry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.item.database.builder.JpaPagingItemReaderBuilder;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class BatchConfig {
    private final JobLauncher jobLauncher;
    private final JobRepository jobRepository;
    private final PlatformTransactionManager batchTransactionManager;
    private static final int BATCH_SIZE = 5;

    private final EntityManagerFactory entityManagerFactory; // EntityManagerFactory 주입
    private final KoreaBorderAreaRepository koreaBorderAreaRepository;
    private final MemberMapAreaRepository memberMapAreaRepository;
    private final Map<Long, RegionType> map = new HashMap<>();

    @Bean
    public Job myJob() {

        log.info("[ JOB ] myJob 수행중 ");
        log.info("1. 지역별 사용자 면적 계산 -> ");
        log.info("2. 랭킹 계산 -> ");
        log.info("3. 랭킹 관련 알림 보내기 ");
        return new JobBuilder("my_job", jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(getRegionMapAreaStep())
                .next(getMemberRankingStep())
                .next(postFCMStep())
                .build();
    }

    @Bean
    public Step getRegionMapAreaStep() {
        log.info("[ STEP 01 ] 지역별 사용자 면적 계산");
        return new StepBuilder("region_map_area_step", jobRepository)
                .<String, String>chunk(BATCH_SIZE, batchTransactionManager)
//                .reader(reader())
//                .processor(proce)
//                .writer(writer())
                .build();
    }

    @Bean
    public Step getMemberRankingStep(){
        log.info("[ STEP 02 ] 랭킹 계산");
        return new StepBuilder("member_ranking_step", jobRepository)
                .<String, String>chunk(BATCH_SIZE, batchTransactionManager)
                .reader(regionMapAreaReader())
//                .writer(writer())
                .build();
    }

    @Bean
    public Step postFCMStep(){
        log.info("[ STEP 03 ] FCM 메시지 발송");
        return new StepBuilder("post_fcm_step", jobRepository)
                .<String, String>chunk(BATCH_SIZE, batchTransactionManager)
//                .reader(reader())
//                .writer(writer())
                .build();
    }

    @Bean
    public JpaPagingItemReader regionMapAreaReader() {
        LocalDateTime yesterday = LocalDateTime.now().minusDays(1); // 어제 날짜 계산

        return new JpaPagingItemReaderBuilder<MemberMapArea>()
                .name("memberMapAreaReader")
                .entityManagerFactory(entityManagerFactory)
                .queryString("select m from MemberMapArea m where m.createdAt >= :yesterday and m.createdAt < :today and m.regionType = :regionType")
                .parameterValues(Map.of("yesterday", yesterday, "today", LocalDateTime.now(), "regionType", RegionType.KOREA))
                .pageSize(1000)
                .build();
    }

    @Bean
    public ItemProcessor<MemberMapArea, List<MemberMapArea>> memberMapAreaProcessor() {
        return item -> {
            return koreaBorderAreaRepository.findAll()
                    .stream()
                    .map(koreaBorderArea -> {
                        if(koreaBorderArea.getShape().intersects(item.getShape())){
                            Geometry shape = koreaBorderArea.getShape().intersection(item.getShape());
                            return MemberMapArea.builder()
                                    .shape(shape)
                                    .dimension(shape.getArea())
                                    .appMember(item.getAppMember())
                                    .regionType(convertRegionType(koreaBorderArea.getIdx()))
                                    .build();
                        }else{
                            return null;
                        }
                    })
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
        };
    }

    private RegionType convertRegionType(Long idx) {
        return RegionType.KOREA;
    }

    @Bean
    public ItemWriter<List<MemberMapArea>> writer() {
        log.info("writer 중 ");
        return items -> {
            for (var item : items) {
                log.info("Writing items: {}", item);
                memberMapAreaRepository.save(item);
            }
        };
    }
}

