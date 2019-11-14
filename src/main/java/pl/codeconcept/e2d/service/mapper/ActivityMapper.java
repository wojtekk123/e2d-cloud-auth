package pl.codeconcept.e2d.service.mapper;


import pl.codeconcept.e2d.database.entity.enums.ActionType;
import pl.codeconcept.e2d.database.entity.UserActivity;

import java.util.Date;

 class ActivityMapper {

     static UserActivity mapToEntity(ActionType actionType) {

        UserActivity userActivity = new UserActivity();
        userActivity.setActionType(actionType);
        userActivity.setDate(new Date());
        return userActivity;
    }
}
