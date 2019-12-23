package pl.codeconcept.e2d.database.entity;


import lombok.Data;
import pl.codeconcept.e2d.database.entity.enums.ActionType;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "user_activity")
public class UserActivity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "action_type")
    @Enumerated(EnumType.STRING)
    private ActionType actionType;

    @Column(name = "action_date")
    private Date date;
}

