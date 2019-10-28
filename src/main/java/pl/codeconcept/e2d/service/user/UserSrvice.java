package pl.codeconcept.e2d.service.user;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import pl.codeconcept.e2d.database.entity.RoleType;
import pl.codeconcept.e2d.database.entity.User;
import pl.codeconcept.e2d.database.repository.UserRepo;
import pl.codeconcept.e2d.dto.RegistrationDto;


@Service
@RequiredArgsConstructor
public class UserSrvice {

    private final UserRepo userRepo;

    public User findUserByUsername(String username) {
        return userRepo.findByUsername(username);
    }

    public User save(User users) {
        return userRepo.save(users);
    }

    public boolean existByUsername(String username) {
        return userRepo.existsByUsername(username);
    }

    public RoleType getRoleType( RegistrationDto registrationDto) {
        String role = registrationDto.getRole().toLowerCase();
        RoleType roleType=null;

        switch (role){
            case "admin":
                roleType= RoleType.ROLE_ADMIN;
                break;
            case "user":
                roleType=RoleType.ROLE_USER;
                break;
        }
        return roleType;
    }
}
