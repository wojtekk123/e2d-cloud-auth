package pl.codeconcept.e2d.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.codeconcept.e2d.database.entity.Users;
import pl.codeconcept.e2d.database.repository.UserRepo;


@Service
public class UserSrvice {

    @Autowired
    UserRepo userRepo;

   public Users findUserByUsername (String username) {
       return userRepo.findByUsername(username);
    }

    public Users save(Users users) {
        return userRepo.save(users);
    }

    public boolean existByUsername (String username){
        return userRepo.existsByUsername(username);
    }
}
