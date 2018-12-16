/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p6db_custscreensrude;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Joe
 */
public class ProductQueries {
    private Connection con;
    private PreparedStatement selectAllProducts;
    private PreparedStatement selectProductByCategory;
    private PreparedStatement selectProductByQuantity;
    private PreparedStatement selectProductByUnitCost;
    private PreparedStatement insertNewProduct;
    
    
    public ProductQueries() {
        try {
            con = DriverManager.getConnection("jdbc:mysql://puff:3306/rudejo_myDB","rudejo","********");
            
            selectAllProducts = con.prepareStatement("SELECT * FROM rudejo_Product");
            
            selectProductByCategory = con.prepareStatement(
                "SELECT * FROM rudejo_Product WHERE category LIKE ? " +
                "ORDER BY category DESC");
            
            selectProductByQuantity = con.prepareStatement(
                "SELECT * FROM rudejo_Product WHERE quantityOnHand <= ? " +       
                "ORDER BY quantityOnHand DESC");
            
            selectProductByUnitCost = con.prepareStatement(
                "SELECT * FROM rudejo_Product WHERE unitCost <= ? " +    
                "ORDER BY unitCost DESC");
            
            insertNewProduct = con.prepareStatement(
                "INSERT INTO rudejo_Product " +
                "(prodID, description, category, quantityOnHand, unitCost, sellingPrice) " +
                "VALUES (?, ?, ?, ?, ?, ?)");
            
        }
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }
    
    //select all products
    public List<Product> getAllProducts() {
        try (ResultSet resultSet = selectAllProducts.executeQuery()) {
            List<Product> results = new ArrayList<Product>();
            
            while (resultSet.next()) {
                results.add(new Product(
                    resultSet.getInt("prodID"),
                    resultSet.getString("description"),
                    resultSet.getString("category"),
                    resultSet.getInt("quantityOnHand"),
                    resultSet.getDouble("unitCost"),
                    resultSet.getDouble("sellingPrice")));
            }
            
            return results;
        }
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        
        return null;
    }
    
    //get products by category
    public List<Product> getProductsByCategory(String category) {
        try {
            selectProductByCategory.setString(1, category);  /////// what does the "1" mean ////////
        }
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return null;
        }
        
        //executeQuery returns ResultSet containing matching entries
        try (ResultSet resultSet = selectProductByCategory.executeQuery()) {
            List<Product> results = new ArrayList<Product>();
            
            while (resultSet.next()) {
                results.add(new Product(
                    resultSet.getInt("prodID"),
                    resultSet.getString("description"),
                    resultSet.getString("category"),
                    resultSet.getInt("quantityOnHand"),
                    resultSet.getDouble("unitCost"),
                    resultSet.getDouble("sellingPrice")));
            }
            
            return results;
        }
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return null;
        }             
    }
    
    //get products by quantity
    //get products by category
    public List<Product> getProductsByQuantity(String quantityOnHand) {
        try {
            selectProductByQuantity.setString(1, quantityOnHand);
        }
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return null;
        }
        
        //executeQuery returns ResultSet containing matching entries
        try (ResultSet resultSet = selectProductByQuantity.executeQuery()) {
            List<Product> results = new ArrayList<Product>();
            
            while (resultSet.next()) {
                results.add(new Product(
                    resultSet.getInt("prodID"),
                    resultSet.getString("description"),
                    resultSet.getString("category"),
                    resultSet.getInt("quantityOnHand"),
                    resultSet.getDouble("unitCost"),
                    resultSet.getDouble("sellingPrice")));
            }
            
            return results;
        }
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return null;
        }             
    }
    
    //get products by unit cost
    public List<Product> getProductsByUnitCost(String unitCost) {
        try {
            selectProductByUnitCost.setString(1, unitCost);
        }
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return null;
        }
        
        //executeQuery returns ResultSet containing matching entries
        try (ResultSet resultSet = selectProductByUnitCost.executeQuery()) {
            List<Product> results = new ArrayList<Product>();
            
            while (resultSet.next()) {
                results.add(new Product(
                    resultSet.getInt("prodID"),
                    resultSet.getString("description"),
                    resultSet.getString("category"),
                    resultSet.getInt("quantityOnHand"),
                    resultSet.getDouble("unitCost"),
                    resultSet.getDouble("sellingPrice")));
            }
            
            return results;
        }
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return null;
        }             
    }
       
    //insert new product
    public int addProduct(String description, String category, int quantityOnHand,
            double unitCost, double sellingPrice) {
        
        //insert new entry, returns # of rows updated
        try {
            insertNewProduct.setString(2, description);
            insertNewProduct.setString(3, category);
            insertNewProduct.setInt(4, quantityOnHand);
            insertNewProduct.setDouble(5, unitCost);
            insertNewProduct.setDouble(6, sellingPrice);
            
            return insertNewProduct.executeUpdate();
        }
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return 0;
        }
    }
    
    public void close() {
        try {
            con.close();
        }
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }
    
}
