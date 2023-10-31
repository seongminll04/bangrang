package com.ssafy.bangrang.domain.member.service;

import com.ssafy.bangrang.domain.member.entity.AppMember;
import com.ssafy.bangrang.domain.member.repository.AppMemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class AppMemberServiceImpl implements AppMemberService {

    private final AppMemberRepository appMemberRepository;

    @Override
    public Long findIdxByNickname(String nickname){
        return appMemberRepository.findIdxByNickname(nickname);
    }

    @Override
    public Optional<AppMember> findAppMemberByAccessToken(String accessToken) {
        return appMemberRepository.findAppMemberByAccessToken(accessToken);
    }


}
