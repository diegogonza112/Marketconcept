package loginTest;

import delete_gateways.DeleteUserGateway;
import login.SaveUserGateway;
import user.User;
import login.GetUserGateway;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class SaveUserGatewayTest {

    SaveUserGateway saveUserGateway = new SaveUserGateway();
    DeleteUserGateway deleteUserGateway = new DeleteUserGateway();
    GetUserGateway getUserGateway = new GetUserGateway();

    @Test
    public void allowSignUpBasicTest() throws IOException, ClassNotFoundException {
        User newUser = new User("Person");
        saveUserGateway.saveUser("Person", newUser);

        User expectedUser = getUserGateway.getUser("Person");
        assertEquals(newUser.getUsername(), expectedUser.getUsername());
        deleteUserGateway.deleteUser("Person");

    }

}
