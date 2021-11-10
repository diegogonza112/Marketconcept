package login;

import InputAndOutput.SystemInOut;
import UserFunctions.User;

import java.io.IOException;

public interface SignInGatewayInterface {
    public User allowSignIn(String username, SystemInOut inOut) throws IOException, ClassNotFoundException;
}