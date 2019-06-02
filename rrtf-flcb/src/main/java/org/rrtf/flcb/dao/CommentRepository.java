package org.rrtf.flcb.dao;


import org.rrtf.flcb.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
	@Transactional
	@Modifying//只需要加在update和delete sql注解前
	@Query("delete from Comment where commentId=?1")
	int deleteByCommentId(int commentId);//通过ID删除课程
}
