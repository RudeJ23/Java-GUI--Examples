/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p6db_custscreensrude;

/**
 *
 * @author gc2804kk
 */
public class Product {
    private static int lastIdUsed = 1;
    private int productID = 1;
    private String description;
    private String category;
    private int quantityOnHand;
    private double unitCost;
    private double sellingPrice;
    
    Product(int tmpProdID, String tmpDescription, String tmpCategory, int tmpQuantity,
            double tmpUnitCost, double tmpSellingPrice) {
        this.description = tmpDescription;
        this.category = tmpCategory;
        this.quantityOnHand = tmpQuantity;
        this.unitCost = tmpUnitCost;
        this.sellingPrice = tmpSellingPrice;
        
        //this.productID = Product.lastIdUsed;
        this.productID = tmpProdID;
        //Product.lastIdUsed++;
    }
    
    public String toString() {
        return Integer.toString(getProductID());
        /*
        return "Product ID: " + Integer.toString(getProductID()) + " " + "Description: " + 
                getDescription() + " " + "Category: " + getCategory() + " " + 
                "Quantity On Hand: " + Integer.toString(getQuantityOnHand()) + " " + 
                "Unit Cost: " + Double.toString(getUnitCost()) + " " + "Selling Price: " +
                Double.toString(getSellingPrice()); // +"\n"
        */
    }


    /**
     * @return the lastIdUsed
     */
    public static int getLastIdUsed() {
        return lastIdUsed;
    }

    /**
     * @param aLastIdUsed the lastIdUsed to set
     */
    public static void setLastIdUsed(int aLastIdUsed) {
        lastIdUsed = aLastIdUsed;
    }

    /**
     * @return the productID
     */
    public int getProductID() {
        return productID;
    }

    /**
     * @param productID the productID to set
     */
    public void setProductID(int productID) {
        this.productID = productID;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the category
     */
    public String getCategory() {
        return category;
    }

    /**
     * @param category the category to set
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * @return the quantityOnHand
     */
    public int getQuantityOnHand() {
        return quantityOnHand;
    }

    /**
     * @param quantityOnHand the quantityOnHand to set
     */
    public void setQuantityOnHand(int quantityOnHand) {
        this.quantityOnHand = quantityOnHand;
    }

    /**
     * @return the unitCost
     */
    public double getUnitCost() {
        return unitCost;
    }

    /**
     * @param unitCost the unitCost to set
     */
    public void setUnitCost(double unitCost) {
        this.unitCost = unitCost;
    }

    /**
     * @return the sellingPrice
     */
    public double getSellingPrice() {
        return sellingPrice;
    }

    /**
     * @param sellingPrice the sellingPrice to set
     */
    public void setSellingPrice(double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }
}
