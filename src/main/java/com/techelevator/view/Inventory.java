package com.techelevator.view;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


/**
 * The inventory class is responsible for organizing and maintaining its
 * inventory.
 */
public class Inventory {
    private final List<Product> productList = new ArrayList<>();

    /**
     * 3.Vending machine inventory is stocked via an input file.
     * sets all inventory products in the vendingmachine.csv file
     * to a productList of type Product.
     *
     * Open a file Object
     * Open Scanner Object to read file
     * loop through each product line
     * store each product line in string var
     * split lineInput at pipeline into String array
     * convert price string to a double data type
     * Create Product Object, use the values in String array to set product state.
     * add newly created product to productList
     */
    public void setProductList() {
        File vendingMachineFilePath = null;
        try {
            vendingMachineFilePath = getFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            if (vendingMachineFilePath != null) {
                try(Scanner vendingMachineFileInput = new Scanner(vendingMachineFilePath)){
                    while (vendingMachineFileInput.hasNext()){
                        String lineInput = vendingMachineFileInput.nextLine();
                        Product product = getProduct(lineInput);
                        productList.add(product);
                    }
                }
            }
        } catch (FileNotFoundException fileNotFoundException) {
            System.out.println(fileNotFoundException.getMessage());
        }
    }

    private File getFile() throws Exception {
            File vendingMachineFilePath = new File("vendingmachine.csv");
            if(!vendingMachineFilePath.isFile()){
                throw new FileNotFoundException(vendingMachineFilePath.getName() + " is not a file.");
            }else if(!vendingMachineFilePath.exists()) {
                throw new Exception(vendingMachineFilePath.getAbsolutePath() + " does not exist");
            }
            return vendingMachineFilePath;
    }

    private Product getProduct(String lineInput) {
        String[] productAttributes = lineInput.split("\\|");
        double productPrice = Double.parseDouble(productAttributes[2]);
        return new Product(productAttributes[0],productAttributes[1],productPrice,productAttributes[3],5);
    }

    public List<Product> getProductList() {
        return productList;
    }

    /**
     * 5. When the customer selects (1) Display Vending Machine Items they are
     * presented a list of all items in the vending machine with its quantity remaining.
     *
     * DisplaysInventory simply iterates over
     * every product in productList and prints it
     * to the console.
     */
    public void displayInventoryProducts(){
        for(Product product : productList) {
            System.out.println(product);
        }
    }
}
