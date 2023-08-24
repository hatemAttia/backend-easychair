package springboot.EasyChair.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import springboot.EasyChair.entity.Conference;


public interface ConferenceRepository extends JpaRepository<Conference, Long>{
	
    Conference findConferenceById(long id);
    
    Conference findConferenceByTitle(String title);

}
