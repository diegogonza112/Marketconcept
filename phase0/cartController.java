import inputOutputFunctions.InOut;

import java.io.IOException;
import java.util.ArrayList;

/**
 * A controller class that adds items to the user's options.cart upon request.
 */
public class cartController{

    /**
     * Takes in user input to add desired items to a user's options.cart from the search option.
     * @param inOut an instance of the command line
     * @param user the profile of the user who is buying.
     */
    // should these methods return a boolean
    public void addToCartSearch(InOut inOut, User user, ArrayList<Product> productList) {
        inOut.sendOutput("What product would you like to buy? (Input index):");
        try {
            String stringIndex = inOut.getInput();
            try{
                // turn string into an int, exception is thrown when the string entered cannot be turned to an int
                int productIndex = Integer.parseInt(stringIndex);
                boolean validIndex = false;
                while(!validIndex){
                    // try and add the product at the index to the users options.cart
                    userManager userManager = new userManager(user);
                    if (userManager.addToShoppingCartSearches(productIndex, productList)){
                        validIndex = true;
                        inOut.sendOutput("Successfully added to options.cart.");
                    }
                    else{
                        inOut.sendOutput("Error, please enter a valid index or this product is out of stock.");
                        stringIndex = inOut.getInput();
                        productIndex = Integer.parseInt(stringIndex);
                    }
                }

            }
            catch(NumberFormatException e){
                // exception thrown when user inputs a string that is not a number
                inOut.sendOutput("An error occurred, please enter a number.");
                // process for buying an item should restart
            }

        } catch (IOException e) {
            inOut.sendOutput("An error occurred, try again.");
        }
    }

    /**
     * Takes in user input to add desired items to a user's options.cart from the user's feed.
     * @param inOut an instance of the command line
     * @param user the profile of the user who is buying.
     */
    public void addToCartFeed(InOut inOut, User user){
        inOut.sendOutput("What product would you like to buy? (Input index):");
        try {
            String stringIndex = inOut.getInput();
            try{
                // turn string into an int, exception is thrown when the string entered cannot be turned to an int
                int productIndex = Integer.parseInt(stringIndex);
                boolean validIndex = false;
                while(!validIndex){
                    // try and add the product at the index to the users options.cart
                    userManager userManager = new userManager(user);
                    if (userManager.addToShoppingCartFeed(productIndex)){
                        validIndex = true;
                        inOut.sendOutput("Successfully added to options.cart.");
                    }
                    else{
                        inOut.sendOutput("Error, please enter a valid index.");
                        stringIndex = inOut.getInput();
                        productIndex = Integer.parseInt(stringIndex);
                    }
                }

            }
            catch(NumberFormatException e){
                // exception thrown when user inputs a string that is not a number
                inOut.sendOutput("An error occurred, please enter a number.");
                // process for buying an item should restart
            }

        } catch (IOException e) {
            inOut.sendOutput("An error occurred, try again.");
        }
    }
}
