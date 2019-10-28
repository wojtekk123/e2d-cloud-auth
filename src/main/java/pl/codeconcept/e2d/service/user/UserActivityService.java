package pl.codeconcept.e2d.service.user;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.codeconcept.e2d.database.entity.ActionType;
import pl.codeconcept.e2d.database.entity.UserActivity;
import pl.codeconcept.e2d.database.entity.User;
import pl.codeconcept.e2d.database.repository.UserActivityRepo;

@Service
@RequiredArgsConstructor
public class UserActivityService {

    private final UserActivityRepo userActivityRepo;
    private final UserSrvice userSrvice;

    public UserActivity saveActivity(String users, ActionType actionType) {

        User tempUsers = userSrvice.findUserByUsername(users);
        return userActivityRepo.save(new UserActivity(tempUsers.getId(), actionType));
    }
}
