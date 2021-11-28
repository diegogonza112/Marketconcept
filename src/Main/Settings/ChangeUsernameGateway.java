package Settings;
import InputAndOutput.SystemInOut;
import OptionsPackage.EnglishOptionsPresenter;
import OptionsPackage.UserOptionsController;
import UserFunctions.User;
import UserFunctions.UserReadWriter;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

// TODO: change password
//TODO:  change Bio
// TODO profile picture
public class ChangeUsernameGateway {
    User user;

    public ChangeUsernameGateway(User user) {
        this.user = user;
    }

    public void changeUsername(String newUsername) throws IOException, ClassNotFoundException {
        SystemInOut input = new SystemInOut();
        File file = new File("src/Main/user.ser");
        if (!(file.length() == 0)) {
            // access the serialized file for this user.
            UserReadWriter rw = new UserReadWriter();
            HashMap<String, Object> usersSavedDict = rw.readFromFile("src/Main/user.ser");
            if (usersSavedDict.containsKey(user.getUsername())) {
                // remove previous username
                usersSavedDict.remove(user.getUsername());
                // add new username
                usersSavedDict.put(newUsername, user);
                rw.saveToFile("src/Main/user.ser", usersSavedDict);

            }

            input.sendOutput("This username does not exist, so it cannot be deleted ");
            UserOptionsController options = new UserOptionsController(user);
            EnglishOptionsPresenter engPresenter = new EnglishOptionsPresenter();
            options.getOption(input, engPresenter);

        }
        input.sendOutput("This username does not exist, so it cannot be deleted ");
        UserOptionsController options = new UserOptionsController(user);
        EnglishOptionsPresenter engPresenter = new EnglishOptionsPresenter();
        options.getOption(input, engPresenter);


    }
}
