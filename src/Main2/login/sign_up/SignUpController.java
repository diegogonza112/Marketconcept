package login.sign_up;


import login.GetIDandPasswordsGateway;
import login.SaveUserGateway;
import login.SetIDandPasswordsGateway;
import user.User;

import java.io.IOException;
import java.util.HashMap;

public class SignUpController {

    /** Checks wether the given userID and password is valid.
     *
     * @param userID the ID of the user
     * @param password the password of the user
     * @return wether the userID and password is valid
     */
    public boolean checkIdAndPass(String userID, String password) {
        return !(password.equals("") | userID.equals(""));
    }


    /**
     *
     * @return hashmap of username to passwords.
     * @throws IOException
     * @throws ClassNotFoundException
     */
    private HashMap<String,Object> getIdAndPass() throws IOException, ClassNotFoundException {
        GetIDandPasswordsGateway getIDandPasswordsGateway = new GetIDandPasswordsGateway();
        HashMap<String, Object> loginInfo = getIDandPasswordsGateway.getUsernamePasswordHash();
        return loginInfo;
    }


    /**
     *
     * @param userID the ID of the user
     * @return wether the user is in the program
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public boolean containsUsername(String userID) throws IOException, ClassNotFoundException {
        HashMap<String, Object> IdAndPass = this.getIdAndPass();
        if (IdAndPass.containsKey(userID)){
            return true;
        }return false;

    }


    /**
     * set the user to the hashmap
     * @param userID the ID of the user
     * @param password the password of the user
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static void setNewUser(String userID, String password) throws IOException, ClassNotFoundException {
        SetIDandPasswordsGateway setNewUser = new SetIDandPasswordsGateway();
        SaveUserGateway saveUser = new SaveUserGateway();
        setNewUser.setUsernamePasswordHash(userID, password);
        saveUser.saveUser(userID, new User(userID));
    }


}