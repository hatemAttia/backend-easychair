package springboot.EasyChair.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springboot.EasyChair.dto.PaperCreateDto;
import springboot.EasyChair.dto.UpdatePaperDto;
import springboot.EasyChair.entity.Conference;
import springboot.EasyChair.entity.Paper;
import springboot.EasyChair.entity.User;
import springboot.EasyChair.repository.ConferenceRepository;
import springboot.EasyChair.repository.PaperRepository;
import springboot.EasyChair.repository.UserRepository;

@Service
public class PaperServiceImpl implements PaperService  {
	
	@Autowired
    private PaperRepository paperRepository;
	
	@Autowired
    private UserRepository userRepository;
	
	@Autowired
    private ConferenceRepository conferenceRepository;

	@Override
	public Paper createPaper(PaperCreateDto paperDto) {
	    Paper newPaper = new Paper();
	    newPaper.setTitle(paperDto.getTitle());
	    newPaper.setResume(paperDto.getResume());
	    newPaper.setKeywords(paperDto.getKeywords());
	    newPaper.setDocPdf(paperDto.getDocPdf());
	    newPaper.setStatus(paperDto.getStatus());
	    
	    User author = userRepository.getUserById(paperDto.getAuthorId());
	    
	    Conference conference = conferenceRepository.findConferenceById(paperDto.getConferenceId());
	    
	    if (author == null || conference == null) {
	        return null; 
	    }
	    
	    Set<User> authorsSet = new HashSet<>();
	    authorsSet.add(author);
	    newPaper.setAuthors(authorsSet);
	    
	    newPaper.setConference(conference);

	    return paperRepository.save(newPaper);
	}

	@Override
	public Paper updatePaper(Long id, UpdatePaperDto paperUpdateDto) {
	    Paper existingPaper = paperRepository.findById(id).orElse(null);
	    if (existingPaper != null) {
	        existingPaper.setTitle(paperUpdateDto.getTitle());
	        existingPaper.setResume(paperUpdateDto.getResume());
	        existingPaper.setKeywords(paperUpdateDto.getKeywords());
	        existingPaper.setDocPdf(paperUpdateDto.getDocPdf());
	        existingPaper.setStatus(paperUpdateDto.getStatus());

	        return paperRepository.save(existingPaper);
	    } else {
	        return null; 
	    }
	}

	@Override
	public void deletePaper(Long id) {
        paperRepository.deleteById(id);
		
	}

	@Override
	public List<Paper> findByAuthors(User author) {
        return paperRepository.findByAuthors(author);

	}

	@Override
	public List<Paper> findByTitleContaining(String title) {
        return paperRepository.findByTitleContaining(title);

	}

	@Override
	public List<Paper> findByKeywordsContaining(String keyword) {
        return paperRepository.findByKeywordsContaining(keyword);

	}

	@Override
	public Paper findPaperById(long id) {
		return paperRepository.getById(id);
	}

	
    
    
   
}
