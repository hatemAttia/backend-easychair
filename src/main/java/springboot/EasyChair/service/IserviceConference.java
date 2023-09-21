package springboot.EasyChair.service;



import java.util.List;

import springboot.EasyChair.dto.ConferenceDto;
import springboot.EasyChair.entity.Conference;

public interface IserviceConference {
	
	public Conference createConference(ConferenceDto conferenceDto);

    public Conference updateConference(ConferenceDto conferenceDto);
    
    public List<ConferenceDto> getAllConferences();
        
    public Conference findConferenceById(long id);
    
    public Conference findConferenceByTitle(String title);

   

}
