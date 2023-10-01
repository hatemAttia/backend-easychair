package springboot.EasyChair.entity;



import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity

@Table(name="roles")

public class Role {
	
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @Column(nullable=false, unique=true)
	    private String name;

	    @ManyToOne
	    @JoinColumn(name = "user_id") // Nom de la colonne de clé étrangère dans la table Role
	    private User user; // Référence à l'entité User
	    
	    @ManyToOne
	    @JoinColumn(name = "conference_id") // Nom de la colonne de clé étrangère dans la table Role
	    private Conference conference; // Référence à l'entité Conference
}
