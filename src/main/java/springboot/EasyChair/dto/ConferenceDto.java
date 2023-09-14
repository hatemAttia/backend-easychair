package springboot.EasyChair.dto;

import java.util.Date;

import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor


public class ConferenceDto {
	
	
	@NotEmpty
    private String Title;
	
    @NotEmpty
    private String description;
    
    @NotEmpty
    private Date startDate;    
    
    @NotEmpty
    private Date endDate;
    
    private Long organizerId;

}
