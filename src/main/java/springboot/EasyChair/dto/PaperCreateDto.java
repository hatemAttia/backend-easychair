package springboot.EasyChair.dto;

import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor

public class PaperCreateDto {
	
	
	@NotEmpty(message = "Le titre ne peut pas être vide")
    private String title;

    @NotEmpty(message = "Le résumé ne peut pas être vide")
    private String resume;

    @NotEmpty(message = "Les mots-clés ne peuvent pas être vides")
    private String keywords;

    @NotEmpty(message = "Le chemin du document PDF ne peut pas être vide")
    private String docPdf;

    @NotEmpty(message = "Le statut ne peut pas être vide")
    private String status;
    
    private Long authorId; 
    
    private Long conferenceId; 
}
