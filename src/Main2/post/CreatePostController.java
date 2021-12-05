package post;
import login_functions.SaveUserGateway;
import login_functions.SaveUserGatewayInterface;
import postFunctions.Post;
import product.CreateProductController;
import productFunctions.Product;
import userFunctions.User;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Controller that takes care of all the actions needed to create a post.
 */

public class CreatePostController {

    User user;

    public CreatePostController(User user) {
        this.user = user;
    }

    /**
     * Method that takes in an arraylist of all the information needed to create
     * a post. Creates the post and adds it to this.user's listofPosts.
     * @param information a list of all the information needed to create a post
     */
    public void createPost(ArrayList<String> information) throws IOException, ClassNotFoundException {
        // creating the product to add to the post
        CreateProductController createProductController = new CreateProductController(this.user);
        Product product = createProductController.createProduct(information);

        SaveUserGatewayInterface saveUserGateway = new SaveUserGateway();
        PostManager postManager = new PostManager(saveUserGateway);
        // need to get this attributes
        Boolean canComment = Boolean.valueOf(information.get(7));
        Boolean canRate = Boolean.valueOf(information.get(6));
        Post newPost = postManager.createPost(product, information.get(4), canComment, canRate, this.user);
        // add post to user's list of posts
        postManager.savePost(newPost, this.user);
    }
}

