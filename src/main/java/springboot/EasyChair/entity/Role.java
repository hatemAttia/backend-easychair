package springboot.EasyChair.entity;


import java.util.List;

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

	    @ManyToMany(mappedBy="roles")
	    private List<User> users;
}