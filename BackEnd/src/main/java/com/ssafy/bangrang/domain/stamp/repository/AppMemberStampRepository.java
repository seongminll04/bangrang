package com.ssafy.bangrang.domain.stamp.repository;

import com.ssafy.bangrang.domain.member.entity.AppMember;
import com.ssafy.bangrang.domain.stamp.entity.AppMemberStamp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AppMemberStampRepository extends JpaRepository<AppMemberStamp, Long> {

    List<AppMemberStamp> findAllByAppMember(AppMember appMember);
}
