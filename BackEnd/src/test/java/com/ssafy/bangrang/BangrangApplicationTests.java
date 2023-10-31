package com.ssafy.bangrang;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.bangrang.domain.member.entity.AppMember;
import com.ssafy.bangrang.domain.member.entity.QAppMember;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Transactional
class BangrangApplicationTests {

    @PersistenceContext
    EntityManager em;

    @Test
    void contextLoads() {
        AppMember appMember = AppMember.builder().build();
        em.persist(appMember);


        JPAQueryFactory query = new JPAQueryFactory(em);
        QAppMember qAppMember = new QAppMember("m");

        AppMember appMember1 = query.selectFrom(qAppMember)
                .fetchOne();
        System.out.println(appMember1.getIdx());
        Assertions.assertThat(appMember).isEqualTo(appMember1);
    }

}
