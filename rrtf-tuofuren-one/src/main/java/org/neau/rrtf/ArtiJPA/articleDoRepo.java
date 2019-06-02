package org.neau.rrtf.ArtiJPA;

import java.util.List;


import org.neau.rrtf.Entity.ArticleDomment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;



public interface articleDoRepo extends JpaRepository<ArticleDomment,Integer>{

	List<ArticleDomment> findTop8ByArticleIdOrderByCtimeDesc(int id);
	
    int countByArticleId(int aid);
    
	Page<ArticleDomment> findByArticleIdOrderByCtimeDesc(int articleId, Pageable pageable);

	List<ArticleDomment> findByArticleIdOrderByCtimeDesc(int articleId);


}
