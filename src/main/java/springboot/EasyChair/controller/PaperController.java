package springboot.EasyChair.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springboot.EasyChair.dto.PaperCreateDto;
import springboot.EasyChair.dto.UpdatePaperDto;
import springboot.EasyChair.entity.Paper;
import springboot.EasyChair.service.PaperService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/papers")
@Api(tags = "Paper API")
public class PaperController {

	@Autowired
    private PaperService paperService;
	
    @PostMapping
    @ApiOperation("Create a new paper")
    public ResponseEntity<String> createPaper(@RequestBody PaperCreateDto paperDto) {
        Paper createdPaper = paperService.createPaper(paperDto);
        if (createdPaper != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Paper created successfully");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create paper");
        }
        
        
    }
    
    @PatchMapping("/update/{id}")
    @ApiOperation("Update a paper by ID")
    public ResponseEntity<String> updatePaper(@PathVariable("id") long id, @RequestBody UpdatePaperDto paperUpdateDto) {
        Paper paper = paperService.findPaperById(id);
        if (paper != null) {
            paperService.updatePaper(id, paperUpdateDto);
            return ResponseEntity.ok("Paper updated successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/findByTitle/{title}")
    @ApiOperation("Find papers by title")
    public ResponseEntity<List<Paper>> findByTitleContaining(@PathVariable("title") String title) {
        List<Paper> papers = paperService.findByTitleContaining(title);
        return ResponseEntity.ok(papers); 
    }
    
    @GetMapping("/findByKeywords/{keyword}")
    @ApiOperation("Find papers by keywords")
    public ResponseEntity<List<Paper>> findByKeywordsContaining(@PathVariable("keyword") String keyword) {
        List<Paper> papers = paperService.findByKeywordsContaining(keyword);
        return ResponseEntity.ok(papers);
    }

    @GetMapping("/findPaperById/{id}")
    @ApiOperation("Find a paper by ID")
    public ResponseEntity<Paper> findPaperById(@PathVariable("id") long id) {
        Paper paper = paperService.findPaperById(id);
        if (paper != null) {
            return ResponseEntity.ok(paper);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/delete/{id}")
    @ApiOperation("Delete a paper by ID")
    public ResponseEntity<String> deletePaper(@PathVariable("id") long id) {
        paperService.deletePaper(id);
        return ResponseEntity.ok("Paper deleted successfully");
    }
    
    @GetMapping
    @ApiOperation("Get all papers")
    public List<PaperCreateDto> getAllPapers() {
        return paperService.findAllPapers();
    }
    
    
}
