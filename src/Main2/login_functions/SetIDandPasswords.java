package login_functions;

import serializationFunctions.DictionaryReadWriter;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class SetIDandPasswords {

    /**
     * Set username and password to the dictionary
     * @param username string username
     * @param password string password
     * @throws IOException
     * @throws ClassNotFoundException
     */

    public void setUsernamePasswordHash(String username, String password) throws IOException, ClassNotFoundException {
        File file = new File("src/Main2/username_password.ser");
        if (file.length() == 0){
            DictionaryReadWriter rw = new DictionaryReadWriter();
            HashMap<String, String> newHashMap = new HashMap<>();
            newHashMap.put(username, password);
            rw.saveToFile("src/Main2/username_password.ser", newHashMap);
        }
        else{
            // access the serialized file for this user.
            DictionaryReadWriter rw = new DictionaryReadWriter();
            HashMap<String, Object> usersPassSavedDict = rw.readFromFile("src/Main2/username_password.ser");
            usersPassSavedDict.put(username, password);
            rw.saveToFile("src/Main2/username_password.ser", usersPassSavedDict);
        }

    }
}
