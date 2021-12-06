package options.cart;

import options.buy.BuyController;
import product.Product;
import user.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * A use case class that adds items to the user's options.cart
 */
public class CartManager {
    public void buyCart(User user) throws Exception {
        BuyController buyController = new BuyController();
        List<String> cartIDs = new ArrayList<>();

        for (Product i:user.getShoppingCart()) {
            cartIDs.add(i.getId());
        }
        //buyController.allowBuy(inOut, user, cartIDs);
    }

    public void addToCart(User user, Product product){
        List<Product> productList = new ArrayList<>();
        productList.add(product);

        user.setShoppingCart(Stream.concat(user.getShoppingCart().stream(),
                productList.stream()).collect(Collectors.toList()));
    }
}

