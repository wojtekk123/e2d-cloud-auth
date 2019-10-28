package pl.codeconcept.e2d.database.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor

public class UserActivity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "id_users")
    private Long userId;
    @Column(name = "action_type")
    @Enumerated(EnumType.STRING)
    private ActionType actionType;

    public UserActivity(Long userId, ActionType actionType) {
        this.userId = userId;
        this.actionType = actionType;
    }
}

