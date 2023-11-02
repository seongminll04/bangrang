package com.ssafy.bangrang.global.fcm.entity;

import com.ssafy.bangrang.domain.member.entity.AppMember;
import com.ssafy.bangrang.global.common.entity.CommonEntity;
import com.ssafy.bangrang.global.fcm.model.vo.AlarmType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "alarm")
public class Alarm extends CommonEntity {

    @Id
    @GeneratedValue
    @Column(name = "alarm_idx")
    private Long idx;

    @Enumerated(EnumType.STRING)
    @Column(name = "alarm_type")
    private AlarmType type;

    @Column(name = "alarm_content")
    private String content;

    @Column(name = "alarm_status")
    private Number status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_idx")
    private AppMember appMember;

}
