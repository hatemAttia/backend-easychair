package springboot.EasyChair.service;

import java.util.List;

import springboot.EasyChair.dto.PaperCreateDto;
import springboot.EasyChair.dto.UpdatePaperDto;
import springboot.EasyChair.entity.Paper;
import springboot.EasyChair.entity.User;

public interface PaperService {
	
	Paper createPaper(PaperCreateDto paperDTO);

	Paper updatePaper(Long id, UpdatePaperDto paperUpdateDto); 

	void deletePaper(Long id); 
	
	List<Paper> findByAuthors(User author);

    List<Paper> findByTitleContaining(String title);

    List<Paper> findByKeywordsContaining(String keyword);

	Paper findPaperById(long id);

   
    

}
