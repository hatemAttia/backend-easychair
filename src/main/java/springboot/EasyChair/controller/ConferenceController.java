package springboot.EasyChair.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springboot.EasyChair.dto.ConferenceDto;
import springboot.EasyChair.entity.Conference;
import springboot.EasyChair.service.IserviceConference;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/conferences")
@Api(tags = "Conference API")
public class ConferenceController {

    private  IserviceConference iserviceConference;
    
    public ConferenceController(IserviceConference iserviceConference) {
        this.iserviceConference = iserviceConference;
    }

    @PostMapping
    @ApiOperation("Create a new conference")
    public ResponseEntity<String> createConference(@RequestBody ConferenceDto conferenceDto) {
        Conference createdConference = iserviceConference.createConference(conferenceDto);
        if (createdConference != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Conference created successfully");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create conference");
        }
    }


    @PutMapping
    @ApiOperation("Update an existing conference")
    public Conference updateConference(@RequestBody ConferenceDto conferenceDto) {
        return iserviceConference.updateConference(conferenceDto);
    }

    @GetMapping
    @ApiOperation("Get all conferences")
    public List<ConferenceDto> getAllConferences() {
        return iserviceConference.getAllConferences();
    }

    @GetMapping("/{id}")
    @ApiOperation("Get a conference by ID")
    public Conference getConferenceById(@PathVariable long id) {
        return iserviceConference.findConferenceById(id);
    }
    
    @GetMapping("/title/{title}")
    @ApiOperation("Get a conference by title")
    public Conference getConferenceByTitle(@PathVariable String title) {
        return iserviceConference.findConferenceByTitle(title);

        
    }

}
