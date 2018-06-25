/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package majorprojectrude;

import java.text.DecimalFormat;

/**
 *
 * @author Joe
 */
public class Order {
    private static DecimalFormat df2 = new DecimalFormat(".##");
    private String custFirstName;
    private String custLastName;
    private String productName;
    private int quantity;
    private int shippingCost;
    private double totalCost;
    
    Order(String tmpFirstName, String tmpLastName, String tmpProductName,
            int tmpQuantity, int tmpShippingCost, double tmpTotalCost) {
        this.custFirstName = tmpFirstName; this.custLastName = tmpLastName;
        this.productName = tmpProductName; this.quantity = tmpQuantity;
        this.shippingCost = tmpShippingCost; this.totalCost = tmpTotalCost;
    }
    
    public String toString() {
        return "First Name: " + getCustFirstName() + " " + "Last Name: " +
                getCustLastName() + " " + "Product: " + getProductName() + " " +
                "Quantity: " + Integer.toString(getQuantity()) + " " + "Shipping Cost: " 
                + Integer.toString(getShippingCost()) +
                " " + "Total Cost: " + Double.toString(getTotalCost());
    }

    /**
     * @return the custFirstName
     */
    public String getCustFirstName() {
        return custFirstName;
    }

    /**
     * @return the custLastName
     */
    public String getCustLastName() {
        return custLastName;
    }

    /**
     * @return the productName
     */
    public String getProductName() {
        return productName;
    }

    /**
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * @return the shippingCost
     */
    public int getShippingCost() {
        return shippingCost;
    }

    /**
     * @return the totalCost
     */
    public double getTotalCost() {
        return totalCost;
    }
}
