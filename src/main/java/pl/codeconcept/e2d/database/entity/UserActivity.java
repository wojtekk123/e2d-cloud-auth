package pl.codeconcept.e2d.database.entity;


import javax.persistence.*;
import java.util.Date;

@Entity
public class UserActivity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long id_users;
    private String action_type;

    public UserActivity() {
    }

    public UserActivity(Long id_users, String action_type) {
        this.id_users = id_users;
        this.action_type = action_type;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId_users() {
        return id_users;
    }

    public void setId_users(Long id_users) {
        this.id_users = id_users;
    }

    public String getAction_type() {
        return action_type;
    }

    public void setAction_type(String action_type) {
        this.action_type = action_type;
    }
}

