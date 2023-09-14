package springboot.EasyChair.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import springboot.EasyChair.dto.ConferenceDto;
import springboot.EasyChair.dto.UpdateConferenceDto;
import springboot.EasyChair.entity.Conference;
import springboot.EasyChair.service.ConferenceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/conferences")
@Api(tags = "Conference API")
public class ConferenceController {

    private  ConferenceService conferenceService;
    
    public ConferenceController(ConferenceService conferenceService) {
        this.conferenceService = conferenceService;
    }

    @PostMapping
    @ApiOperation("Create a new conference")
    public ResponseEntity<String> createConference(@RequestBody ConferenceDto conferenceDto) {
        Conference createdConference = conferenceService.createConference(conferenceDto);
        if (createdConference != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Conference created successfully");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create conference");
        }
    }
    
    
//    @GetMapping("update/{id}")
//    @ApiOperation("Get a conference for update by ID")
//    public ResponseEntity<Conference> getConferenceForUpdate(@PathVariable long id) {
//        Conference conference = conferenceService.findConferenceById(id);
//        if (conference != null) {
//            return ResponseEntity.ok(conference);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }

    @PatchMapping("update/{id}")
    @ApiOperation("Update a conference by ID")
    public ResponseEntity<String> updateConference(@PathVariable("id") long id, @RequestBody UpdateConferenceDto updateConferenceDto) {
    	Conference conferance = conferenceService.findConferenceById(id);
    	if(conferance != null){
    	 conferenceService.updateConference(id, updateConferenceDto);
    	}
          return ResponseEntity.ok("Conference updated successfully");

    }




    @GetMapping
    @ApiOperation("Get all conferences")
    public List<ConferenceDto> getAllConferences() {
        return conferenceService.getAllConferences();
    }

    @GetMapping("/{id}")
    @ApiOperation("Get a conference by ID")
    public Conference getConferenceById(@PathVariable long id) {
        return conferenceService.findConferenceById(id);
    }
    
    @GetMapping("/title/{title}")
    @ApiOperation("Get a conference by title")
    public Conference getConferenceByTitle(@PathVariable String title) {
        return conferenceService.findConferenceByTitle(title);

        
    }

}
