package Settings;

import InputAndOutput.SystemInOut;

import java.io.IOException;

public interface DeleteUserGatewayInterface {
    public boolean deleteUser(String username) throws IOException, ClassNotFoundException;
    }