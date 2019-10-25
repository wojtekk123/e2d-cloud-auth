package pl.codeconcept.e2d.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.codeconcept.e2d.database.entity.UserActivity;
import pl.codeconcept.e2d.database.entity.Users;
import pl.codeconcept.e2d.database.repository.UserActivityRepo;
import pl.codeconcept.e2d.database.repository.UserRepo;

import java.util.Date;

@Service
public class UserActivityService {
    @Autowired
    private UserActivityRepo userActivityRepo;
    @Autowired
    private UserSrvice userSrvice;


    public UserActivity saveActivity(String users,  String acttion) {

        Users tempUsers = userSrvice.findUserByUsername(users);
        return userActivityRepo.save(new UserActivity(tempUsers.getId(),  acttion));

    }

}
