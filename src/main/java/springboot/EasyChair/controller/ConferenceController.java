package springboot.EasyChair.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;
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
    public Conference createConference(@RequestBody Conference conference) {
        return iserviceConference.createConference(conference);
    }

    @PutMapping
    @ApiOperation("Update an existing conference")
    public Conference updateConference(@RequestBody Conference conference) {
        return iserviceConference.updateConference(conference);
    }

    @GetMapping
    @ApiOperation("Get all conferences")
    public List<Conference> getAllConferences() {
        return iserviceConference.getAllConferences();
    }

    @GetMapping("/{id}")
    @ApiOperation("Get a conference by ID")
    public Conference getConferenceById(@PathVariable int id) {
        return iserviceConference.findConferenceById(id);
    }
}
