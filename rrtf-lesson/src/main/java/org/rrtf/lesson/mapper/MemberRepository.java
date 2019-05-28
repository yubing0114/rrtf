package org.rrtf.lesson.mapper;

import org.rrtf.lesson.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member,Integer> {
	//通过userId查找某一个会员
	Member findByUserId(int userId);
}
