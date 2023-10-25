package com.ssafy.bangrang.domain.inquiry.entity;

import com.ssafy.bangrang.domain.member.entity.AppMember;
import com.ssafy.bangrang.domain.member.entity.WebMember;
import com.ssafy.bangrang.global.common.entity.CommonEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "inquiry")
public class Inquiry extends CommonEntity {
    @Id
    @GeneratedValue
    @Column(name = "inquiry_idx")
    private Long idx;

    @Column(name = "inquiry_title")
    private String title;

    @Column(name = "inquiry_content")
    private String content;

    @Column(name = "inquiry_type")
    private String type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "app_member_idx")
    private AppMember appMember;

}
