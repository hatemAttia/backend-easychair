package springboot.EasyChair.service;



import java.util.List;

import springboot.EasyChair.dto.ConferenceDto;
import springboot.EasyChair.dto.UpdateConferenceDto;
import springboot.EasyChair.entity.Conference;

public interface ConferenceService {
	
	public Conference createConference(ConferenceDto conferenceDto);

    public Conference updateConference(long id , UpdateConferenceDto updateConferenceDto);
    
    public List<ConferenceDto> getAllConferences();
        
    public Conference findConferenceById(long id);
    
    public Conference findConferenceByTitle(String title);

	public void deleteConference(long id);
   

}
