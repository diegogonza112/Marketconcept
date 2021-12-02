package userFunctions;
import browseFunctions.GetUserDictGateway;
import browseFunctions.GetUserDictGatewayInterface;
import browseFunctions.SaveUserDictGateway;
import browseFunctions.SaveUserDictGatewayInterface;
import postFunctions.Post;
import productFunctions.GetProductGateway;
import productFunctions.GetProductGatewayInterface;
import productFunctions.Product;
import productFunctions.ProductUseCase;
import loginFunctions.SaveUserGatewayInterface;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * A use case class that adds items to the user's cart
 */
public class CartManager {
    SaveProductGatewayInterface saveProductChangesInterface;
    SaveUserGatewayInterface saveUserGatewayInterface;
    GetUserDictGatewayInterface getUserDictGatewayInterface;
    SaveUserDictGatewayInterface saveUserDictGatewayInterface;

    public CartManager(SaveProductGatewayInterface saveProductChangesInterface, GetUserDictGatewayInterface getUserDictGatewayInterface, SaveUserDictGatewayInterface saveUserDictGatewayInterface){

        this.saveProductChangesInterface = saveProductChangesInterface;
        this.saveUserDictGatewayInterface = saveUserDictGatewayInterface;
        this.getUserDictGatewayInterface = getUserDictGatewayInterface;
    }

    public CartManager(SaveUserGatewayInterface saveUserGatewayInterface ){
        this.saveUserGatewayInterface = saveUserGatewayInterface;
    }

    public CartManager(){};

    /**
     *
     * @param item The product that we want to add to the cart
     * @param user The user whom we want to add the product to their cart
     * @return true or false if the product was or was not added to the user's cart
     */

    public boolean addToCart(Product item, User user){
        List<Product> userCart = user.getShoppingCart();
        if (item.getQuantity() > 0){
            userCart.add(item);
            return true;
        }
        return false;
    }

    /**
     * Updates the quanitity of the cart when the user buys
     * @param user the user whom we want to update their cart
     */

    public void updateProductQuantity(User user) throws IOException, ClassNotFoundException {
        List<Product> userCart = user.getShoppingCart();
        HashMap <String, User> userDict = getUserDictGatewayInterface.getUserDict();
        for (Product prod: userCart){
            GetProductGatewayInterface getProductGatewayInterface = new GetProductGateway();
            ProductUseCase prodUseCase = new ProductUseCase(getProductGatewayInterface);
            prodUseCase.decreaseQuantity(prod, 1);
            // Todo: delete the product with the old information from the file
            saveProductChangesInterface.save(prod.getId(),prod);
            //updating product in post serilzation in the userDict
            User productUser = userDict.get(prod.getUsername());
            List postList = user.getListPosts();
            for (int c = 0; c < postList.size(); c++){
                Post post = user.getListPosts().get(c);
                if (prod.getId().equals(post.getProduct().getId())){
                    Product productPost = post.getProduct();
                    prodUseCase.decreaseQuantity(productPost, 1);
                    post.setProduct(productPost);
                    postList.set(c, post);
                }
            }
            productUser.setListPosts(postList);
            userDict.put(prod.getUsername(),productUser);
        }
        saveUserDictGatewayInterface.saveUserDict(userDict);
    }

    /**
     *
     * @param user The user whom we will empty their cart
     * @throws IOException
     * @throws ClassNotFoundException
     */

    public void emptyCart(User user) throws IOException, ClassNotFoundException {
        List<Product> userCart = user.getShoppingCart();
        userCart.clear();
        user.setShoppingCart(userCart);
        saveUserGatewayInterface.saveUser(user.getUsername(), user);
    }
}



