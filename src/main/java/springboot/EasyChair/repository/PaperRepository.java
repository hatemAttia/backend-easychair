package springboot.EasyChair.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import springboot.EasyChair.entity.Paper;
import springboot.EasyChair.entity.User;

public interface PaperRepository extends JpaRepository<Paper, Long>{
	
	List<Paper> findByAuthors(User author);
	
	List<Paper> findByTitleContaining(String title);
    
    List<Paper> findByKeywordsContaining(String keyword);

    

}
