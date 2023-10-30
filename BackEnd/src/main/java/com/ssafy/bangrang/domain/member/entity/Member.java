package com.ssafy.bangrang.domain.member.entity;

import com.ssafy.bangrang.domain.member.model.vo.AlarmReceivedStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "DTYPE")
@Table(name = "member")
@ToString(of = {"idx"})
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
