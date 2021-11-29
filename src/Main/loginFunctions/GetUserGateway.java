package loginFunctions;
import inputOutputFunctions.SystemInOut;
import serializationFunctions.DictionaryReadWriter;
import userFunctions.User;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

/**
 *
 */
// TODO strategy design pattern to extract common code in gateways -
public class GetUserGateway implements  SignInGatewayInterface {

    /**
     * Return a user, either a new one with the given username, or an old user, that was previously saved in our system
     * @param username The username inputted from the user
     * @return A newly created or old user
     */
    public User getUser(String username) throws IOException, ClassNotFoundException {
        // TODO: fix coupling
        SystemInOut inOut = new SystemInOut();
        File file = new File("src/Main/user.ser");
        if (file.length() == 0){
            inOut.sendOutput("No user exists yet, please sign up. You are being redirected to the welcome page.");
            WelcomePageController welcome = new WelcomePageController();
            welcome.userLoginDecision(inOut);
        }
        // access the serialized file for this user.
        DictionaryReadWriter rw = new DictionaryReadWriter();
        PasswordPresenter passwordPresenter = new PasswordPresenter();
        HashMap<String, Object> usersSavedDict = rw.readFromFile("src/Main/user.ser");
        if (usersSavedDict.containsKey(username)){
            passwordPresenter.promptPasswordSignIn((User)usersSavedDict.get(username));
            return (User)usersSavedDict.get(username);
        }
        // if the user does not exist, return a user with an empty username, which the empty username is unaccepted
        // username anyways
        // TODO: fix this
        return new User("");
    }
}