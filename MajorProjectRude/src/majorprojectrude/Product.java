/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package majorprojectrude;

/**
 *
 * @author Joe
 */
public class Product {
    private String productName;
    private double productPrice;
    
    Product(String tmpName, double tmpPrice) {
        this.productName = tmpName;
        this.productPrice = tmpPrice;
    }
    
    public String toString() {
        return "Product Name: " + getProductName() + " " + "Price: " + 
                getProductPrice() +'\n';
    }

    /**
     * @return the productName
     */
    public String getProductName() {
        return productName;
    }

    /**
     * @return the productPrice
     */
    public double getProductPrice() {
        return productPrice;
    }
}
