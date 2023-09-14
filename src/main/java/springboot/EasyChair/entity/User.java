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

@Table(name = "users")

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;
    
    @Column(nullable = false)
    private String phoneNumber;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column()
    private Boolean isActive;

    @Column(nullable = true)
    private Date deletedAt;

    @OneToMany(mappedBy = "user")
    private Set<Role> roles = new HashSet<>();
    
    //@OneToMany(mappedBy = "organizer")
    //private Set<Conference> organizedConferences = new HashSet<>();

		
}


