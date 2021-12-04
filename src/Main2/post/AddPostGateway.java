package post;
import postFunctions.Post;
import serializationFunctions.DictionaryReadWriter;
import userFunctions.User;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

/**
 * Add post to the serialized file.
 */
public class AddPostGateway {

    /**
     * Adds a post to a users post list in the user.ser file
     * @param post the post added by the user
     * @param user the user that added the post
     */
    public void addPost(Post post, User user) throws IOException, ClassNotFoundException {
        File file = new File("src/Main2/user.ser");
        if (!(file.length() == 0)) {
            DictionaryReadWriter rw = new DictionaryReadWriter();
            HashMap<String, Object> usersSavedDict = rw.readFromFile("src/Main2/user.ser");
            ((User) usersSavedDict.get(user.getUsername())).addToPostList(post);
            rw.saveToFile("src/Main2/user.ser",usersSavedDict);
        }
    }
}