package com.ssafy.bangrang.domain.event.entity;

import com.ssafy.bangrang.domain.member.entity.AppMember;
import com.ssafy.bangrang.global.common.entity.CommonEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "stamp",
    uniqueConstraints = {
        @UniqueConstraint(
                name="stamp_unique",
                columnNames = {
                        "event_idx",
                        "member_idx"
                }
        )
    })
@Builder
public class Stamp extends CommonEntity {

    @Id
    @GeneratedValue
    @Column(name = "stamp_idx")
    private Long idx;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_idx", nullable = false)
    private Event event;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_idx", nullable = false)
    private AppMember appMember;

}
