package springboot.EasyChair.dto;

import java.util.Date;

import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import springboot.EasyChair.entity.User;


@Data
@NoArgsConstructor
@AllArgsConstructor


public class UpdateConferenceDto {
	
	private Long id;
	
	@NotEmpty
    private String Title;
	
    @NotEmpty
    private String description;
    
    @NotEmpty
    private Date startDate;    
    
    @NotEmpty
    private Date endDate;
    
    private User organizerId;

}
