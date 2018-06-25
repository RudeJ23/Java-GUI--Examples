/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p6db_custscreensrude;

/**
 *
 * @author Joe
 */
public class Order {
    private int orderID;
    private String productName;
    private String customerName;
    private int quantity;
    private double totalCost;
    
    Order(int tmpOrderID, String tmpProductName, String tmpCustomerName, int tmpQuantity,
            double tmpTotalCost)
    {
        this.orderID = tmpOrderID;
        this.productName = tmpProductName; this.customerName = tmpCustomerName;
        this.quantity = tmpQuantity; this.totalCost = tmpTotalCost;
    }
    
    public String toString() {
        return "ID: " + getOrderID() + " " + "Product: " + getProductName() + " " + "Customer: " +
                getCustomerName() + " " + "Quantity: " + Integer.toString(getQuantity()) + " " +
                "Total Cost: $" + Double.toString(getTotalCost());
    }

    /**
     * @return the productName
     */
    public String getProductName() {
        return productName;
    }

    /**
     * @param productName the productName to set
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     * @return the customerName
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * @param customerName the customerName to set
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /**
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * @return the totalCost
     */
    public double getTotalCost() {
        return totalCost;
    }

    /**
     * @param totalCost the totalCost to set
     */
    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    /**
     * @return the orderID
     */
    public int getOrderID() {
        return orderID;
    }

}
