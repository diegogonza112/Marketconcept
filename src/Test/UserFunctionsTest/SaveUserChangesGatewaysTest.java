package UserFunctionsTest;

import Settings.DeleteUserGateway;
import UserFunctions.User;
import login.GetUserGateway;
import login.SignUpGateway;
import UserFunctions.SaveUserChangesGateways;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class SaveUserChangesGatewaysTest {
    SaveUserChangesGateways saveUserChangesGateways = new SaveUserChangesGateways();
    GetUserGateway getUserGateway = new GetUserGateway();
    DeleteUserGateway deleteUserGateway = new DeleteUserGateway();

    @Test
    public void setSaveUserChangesGatewaysTest() throws IOException, ClassNotFoundException {
        User newUser = new User("albert");
        String username = newUser.getUsername();
        saveUserChangesGateways.save(username, newUser);

        User expectedUser = getUserGateway.getUser("albert");
        assertEquals(newUser.getUsername(), expectedUser.getUsername());
        deleteUserGateway.deleteUser("albert");

    }
}
