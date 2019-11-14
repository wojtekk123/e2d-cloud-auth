package pl.codeconcept.e2d.service.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.codeconcept.e2d.database.entity.enums.ActionType;
import pl.codeconcept.e2d.database.entity.UserRegistration;
import pl.codeconcept.e2d.database.entity.enums.RoleType;
import pl.codeconcept.e2d.dto.UserDto;

import java.util.Date;
@RequiredArgsConstructor
public class RegistrationMapper {


    public static UserRegistration mapToEntity(UserDto userDto,String password ,ActionType actionType) {

        UserRegistration userRegistration = new UserRegistration();
        userRegistration.setUsername(userDto.getUsername());
        userRegistration.setPassword(password);
        userRegistration.setRole(getRoleType(userDto));
        userRegistration.setUserActivity(ActivityMapper.mapToEntity(actionType));
        return userRegistration;
    }

    private static RoleType getRoleType(UserDto userDto) {
        String role = userDto.getRole().toLowerCase();
        RoleType roleType=null;

        switch (role){
            case "admin":
                roleType= RoleType.ROLE_ADMIN;
                break;
            case "user":
                roleType=RoleType.ROLE_USER;
                break;
            case "school":
                roleType=RoleType.ROLE_SCHOOL;
        }
        return roleType;
    }
    
}
