package com.ssafy.bangrang.domain.member.repository;

import com.ssafy.bangrang.domain.member.entity.Friendship;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FriendshipRepository extends JpaRepository<Friendship, Long> {

    Optional<Friendship> findFriendshipByFriendIdxAndAppMember_Idx(Long friendshipIdx, Long appMemberIdx);
}
