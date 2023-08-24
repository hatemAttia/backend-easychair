package springboot.EasyChair.entity;


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity

@Table(name = "conferences")

public class Conference {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String title;
    
    @Column(nullable = false)
    private String descreption;

    @Column(nullable = false)
    private Date startDate;
    
    @Column(nullable = false)
    private Date endDate;
    
    @OneToMany(mappedBy = "conference")
    private Set<Role> role = new HashSet<>();
    
    @ManyToOne
    @JoinColumn(name = "organizer_id") // Nom de la colonne de clé étrangère dans la table Conference
    private User organizer; // Référence à l'entité User

		
}


