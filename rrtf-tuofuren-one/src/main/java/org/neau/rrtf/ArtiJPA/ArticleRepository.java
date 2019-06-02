package org.neau.rrtf.ArtiJPA;

import java.util.List;

import org.neau.rrtf.Entity.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface ArticleRepository extends JpaRepository<Article, Integer> {
	/* List<Article> findByArticleType(String type); */

	List<Article> findAll();

	Article findByArticleId(int id);

	List<Article> findByTeacherId(int id);

	List<Article> findTop6ByArticleType(String type, Sort sort);

	Page<Article> findByArticleType(String articleType, Pageable pageable);

	@Transactional // 提交事务
	@Modifying
	@Query(value = "insert into user_article values (?1,?2)", nativeQuery = true)
	int save1(int userId, int ArticleId);

	@Transactional
//	@Query(value="select * from user_article where user_id=?1 and article_id=?2",nativeQuery=true)
	@Query(value = "select * from user_article where user_id=?1 and article_id=?2", nativeQuery = true)
	List<Object> select1(int userId, int articleId);

	@Transactional
//	@Query(value="select * from user_article where user_id=?1 and article_id=?2",nativeQuery=true)
	@Query(value = "select a.article_id,a.article_detail,a.article_type,a.article_title,a.article_picture,a.regtime,a.teacher_id,a.status,a.pageview from user_article as ua left join article as a on a.article_id = ua.article_id left join user as u on u.user_id=ua.user_id where ua.user_id=?1 /*#pageable*/ ORDER BY a.regtime"
		, countQuery="select count(a.article_id) from user_article as ua left join article as a on a.article_id = ua.article_id left join user as u on u.user_id=ua.user_id where ua.user_id=?1"
		, nativeQuery = true)
	Page<Article> select2(int userId, Pageable pageable);

	@Transactional // 提交事务
	@Modifying
	@Query(value = "delete from user_article  where user_id=?1 and article_id=?2", nativeQuery = true)
	int delete1(int userId, int articleId);

	Page<Article> findByTeacherIdOrderByRegtime(int teacherId, Pageable pageable);

	Page<Article> findByTeacherIdOrderByRegtimeDesc(int teacherId, Pageable pageable);

	Page<Article> findByArticleTitleLikeAndArticleType(String articleTitle, String articleType, Pageable pageable);

	Page<Article> findByArticleTypeAndTeacherId(String articleType, int teacherId, Pageable pageable);

	Page<Article> findAll(Pageable pageable);

	Page<Article> findByArticleTitleAndArticleTypeAndTeacherId(String articleTitle, String articleType, int teacherId,
			Pageable pageable);

	Page<Article> findByArticleTypeAndArticleTitle(String articleTitle, String articleType, Pageable pageable);

	List<Article> findByArticleType(String articleType);

	@Transactional // 提交事务
	@Modifying
	@Query(value = "delete from article where article_id in (?1)", nativeQuery = true)
	int deleteArticle(List<Integer> ids);
	
	
	@Transactional // 提交事务
	@Modifying
	@Query(value = "update article set status=?1 where article_id in (?2)", nativeQuery = true)
	int updateByStatus(int status,List<Integer> ids);
	
	@Transactional 
	@Query(value = "select * from user_article where article_id=?1", nativeQuery = true)
	List<Object>selectTotalike(int articleId);
	
	
	@Transactional // 提交事务
	@Modifying
	@Query(value = "delete from article where article_id = ?1", nativeQuery = true)
	int deleteart(int articleId);
	
	@Transactional // 提交事务
	@Modifying
	@Query(value = "update article set pageview=pageview+1 where article_id =?1", nativeQuery = true)
	int updatePagev(int articleId);
	
	@Transactional // 提交事务
	@Modifying
	@Query(value = "update article set article_title=?1,article_picture=?2,article_type=?3,article_detail=?4 where article_id =?5", nativeQuery = true)
	int updatesave(String articleTitle,String articlePicture,String articleType,String articleDetail,int articleId);


}
