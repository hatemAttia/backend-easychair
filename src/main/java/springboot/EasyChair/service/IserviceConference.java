package springboot.EasyChair.service;



import java.util.List;

import springboot.EasyChair.entity.Conference;

public interface IserviceConference {
	
	public Conference createConference(Conference conference);

    public Conference updateConference(Conference conference);
    
    public List<Conference> getAllConferences();
        
    public Conference findConferenceById(int id);
   

}
