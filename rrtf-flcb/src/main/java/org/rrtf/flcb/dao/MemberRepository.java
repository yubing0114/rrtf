package org.rrtf.flcb.dao;


import org.rrtf.flcb.entity.Member;
import org.rrtf.flcb.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
	Member findByMemberId(int memberId);
	Member findByUser(User user);
}
