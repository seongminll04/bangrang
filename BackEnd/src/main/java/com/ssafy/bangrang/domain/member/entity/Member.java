package com.ssafy.bangrang.domain.member.entity;

import com.ssafy.bangrang.domain.member.model.vo.AlarmReceivedStatus;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "DTYPE")
@Table(name = "member")
public abstract class Member {

    @Id
    @GeneratedValue
    @Column(name = "member_idx")
    private Long idx;

    @Column(name = "member_access_token")
    protected String accessToken;

    @Column(name = "member_refresh_token")
    protected String refreshToken;

}
