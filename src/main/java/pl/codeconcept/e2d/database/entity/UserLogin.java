package pl.codeconcept.e2d.database.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "user_login")
public class UserLogin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_name")
    private String username;

    @OneToOne(cascade= CascadeType.PERSIST)
    @JoinColumn(name = "action_date")
    private UserActivity userActivity;
}
