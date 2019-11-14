package pl.codeconcept.e2d.service.mapper;

import pl.codeconcept.e2d.database.entity.enums.ActionType;
import pl.codeconcept.e2d.database.entity.UserLogin;
import pl.codeconcept.e2d.dto.LoginDto;

public class LoginMapper {

    public static UserLogin mapToEntity(LoginDto loginDto, ActionType actionType) {

        UserLogin userLogin = new UserLogin();
        userLogin.setUsername(loginDto.getUsername());
        userLogin.setUserActivity(ActivityMapper.mapToEntity(actionType));
        return userLogin;

    }

}
