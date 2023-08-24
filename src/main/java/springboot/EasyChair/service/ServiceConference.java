package springboot.EasyChair.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springboot.EasyChair.entity.Conference;
import springboot.EasyChair.repository.ConferenceRepository;

@Service
public class ServiceConference implements IserviceConference {

	@Autowired
	ConferenceRepository conferenceRepository;

    @Override
    public Conference createConference(Conference conference) {
    	
        return conferenceRepository.save(conference);
    }

    @Override
    public Conference updateConference(Conference conference) {
    	
        return conferenceRepository.save(conference);
    }

    @Override
    public List<Conference> getAllConferences() {
    	
        return conferenceRepository.findAll();
    }

	@Override
	public Conference findConferenceById(int id) {
		
        return conferenceRepository.findConferenceById(id);

	}

    
}
