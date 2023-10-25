package com.ssafy.bangrang.domain.event.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StampKey implements Serializable {
    @Column(name = "pk_event_idx")
    private Long eventIdx;

    @Column(name = "pk_app_member_idx")
    private Long appMemberIdx;
}
