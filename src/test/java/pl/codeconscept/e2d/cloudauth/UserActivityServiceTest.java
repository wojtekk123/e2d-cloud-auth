package pl.codeconscept.e2d.cloudauth;


import org.junit.*;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.BDDMockito;
import org.mockito.junit.MockitoJUnitRunner;
import pl.codeconcept.e2d.database.entity.UserActivity;
import pl.codeconcept.e2d.database.entity.Users;
import pl.codeconcept.e2d.database.repository.UserActivityRepo;
import pl.codeconcept.e2d.service.user.UserActivityService;
import pl.codeconcept.e2d.service.user.UserSrvice;


@RunWith(MockitoJUnitRunner.class)
public class UserActivityServiceTest {


    @Mock
    UserSrvice userSrvice;
    @Mock
    UserActivityRepo userActivityRepo;

    @InjectMocks
    UserActivityService userActivityService;

    private String usersName = "wojtek";
    private Users testUsers = new Users("wojtek", "1234", "ROLE_ADMIN");
    private UserActivity userActivity = new UserActivity(1L, "SIGN_IN");

    @Before
    public void init() {

        BDDMockito.given(userSrvice.findUserByUsername(usersName)).willReturn(prepareMockDate());
        BDDMockito.given(userActivityRepo.save(userActivity)).willReturn(prepareMockDate2());
    }

    @Test
    public void saveActivity() {
        Users newUsers = userSrvice.findUserByUsername(usersName);
        UserActivity usersSave = userActivityRepo.save(userActivity);

        Assert.assertEquals(newUsers, testUsers);
        Assert.assertEquals(usersSave.getId(),userActivity.getId());

    }

    private Users prepareMockDate() {
        return testUsers;
    }
    private UserActivity prepareMockDate2() {
        return new UserActivity(1L, "SIGN_IN");
    }


}
