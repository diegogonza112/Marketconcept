package ProductFunctions;

import InputAndOutput.SystemInOut;

import java.io.IOException;

public class CreateProductController {

    public Product createNewProductFromInput(SystemInOut input) throws IOException, ClassNotFoundException {

        input.sendOutput("What is the name of the product?");
        String name = input.getInput();

        input.sendOutput("What is the ID of the product?");
        String ID = input.getInput();

        input.sendOutput("What is the price of this product?");
        String priceString = input.getInput();
        Double price = Double.parseDouble(priceString);

        input.sendOutput("What is the Category of this product?");
        String category = input.getInput();

        input.sendOutput("What is the size of this product? Press enter if this product does not have a size");
        String sizeInput = input.getInput();


        input.sendOutput("What is the quantity of this product? Please enter an integer");
        String quantityString = input.getInput();
        int quantity = Integer.parseInt(quantityString);

        if (! sizeInput.equals("")){
            ProductManager productManager = new ProductManager();
            return productManager.createProduct(name, ID, price, category, sizeInput, quantity);
        }else{
            ProductManager productManager = new ProductManager();
            return productManager.createProduct(name, ID, price, category, quantity);
        }

    }
}
