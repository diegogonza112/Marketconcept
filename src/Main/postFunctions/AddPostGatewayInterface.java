package postFunctions;
import userFunctions.User;
import java.io.IOException;

/**
 *Add the options.post to the feed of the user's followers list.
 */
public interface AddPostGatewayInterface {
    void addPost(Post post, User user) throws IOException, ClassNotFoundException;
}
