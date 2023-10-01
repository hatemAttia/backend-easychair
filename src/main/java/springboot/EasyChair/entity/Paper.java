package springboot.EasyChair.entity;



import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity


public class Paper {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;
    private String resume;
    private String keywords;
    private String docPdf;
    private String status;
    
    @ManyToMany
    @JoinTable(
        name = "user_paper",
        joinColumns = @JoinColumn(name = "paper_id"),
        inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    @JsonIgnore
    private Set<User> authors = new HashSet<>();
    
    @ManyToOne
    @JoinColumn(name = "conference_id") 
    private Conference conference;

    
}


