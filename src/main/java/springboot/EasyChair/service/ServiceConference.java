package springboot.EasyChair.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springboot.EasyChair.dto.ConferenceDto;
import springboot.EasyChair.entity.Conference;
import springboot.EasyChair.entity.User;
import springboot.EasyChair.repository.ConferenceRepository;
import springboot.EasyChair.repository.UserRepository;

@Service
public class ServiceConference implements IserviceConference {

	@Autowired
	private ConferenceRepository conferenceRepository;
	private UserRepository userRepository;
	
	
	
	public ServiceConference(ConferenceRepository conferenceRepository, UserRepository userRepository) {
		this.conferenceRepository = conferenceRepository;
		this.userRepository = userRepository;
	}
	
	
	@Override
	public Conference createConference(ConferenceDto conferenceDto) {
		Conference newConference = new Conference();
		newConference.setTitle(conferenceDto.getTitle());
		newConference.setDescription(conferenceDto.getDescription());
		newConference.setStartDate(conferenceDto.getStartDate());
		newConference.setEndDate(conferenceDto.getEndDate());
		
		User user = userRepository.getById(conferenceDto.getOrganizerId());
		
		newConference.setOrganizer(user);

		return conferenceRepository.save(newConference);
	}
	
	
	@Override
	public Conference updateConference(ConferenceDto conferenceDto) {
	    Conference existingConference = conferenceRepository.findById(conferenceDto.getId())
	                                                        .orElseThrow();

	    // Update the existingConference entity using the data from conferenceDto
	    existingConference.setTitle(conferenceDto.getTitle());
	    existingConference.setDescription(conferenceDto.getDescription());
	    existingConference.setStartDate(conferenceDto.getStartDate());
	    existingConference.setEndDate(conferenceDto.getEndDate());
	    
	    // Save the updated entity back to the database
	    return conferenceRepository.save(existingConference);
	}


	@Override
	public List<ConferenceDto> getAllConferences() {
        List<Conference> conferences = conferenceRepository.findAll();
        return conferences.stream()
                .map((conference) -> mapToConferenceDto(conference))
                .collect(Collectors.toList());

	}
	private ConferenceDto mapToConferenceDto(Conference conference) {
		ConferenceDto conferenceDto = new ConferenceDto();
		conferenceDto.setTitle(conference.getTitle());
	    conferenceDto.setDescription(conference.getDescription());
	    conferenceDto.setStartDate(conference.getStartDate());
	    conferenceDto.setEndDate(conference.getEndDate());
	   // User organizer = conference.getOrganizer();

	    return conferenceDto;
	    }


	@Override
	public Conference findConferenceById(long id) {
		
        return conferenceRepository.findConferenceById(id);

	}


	@Override
	public Conference findConferenceByTitle(String title) {
        return conferenceRepository.findConferenceByTitle(title);

	}

   
}
