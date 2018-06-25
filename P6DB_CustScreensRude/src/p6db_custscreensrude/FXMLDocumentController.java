/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p6db_custscreensrude;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

/**
 *
 * @author Joe Rude, DB Project
 */
public class FXMLDocumentController implements Initializable {
    int custID, prodID, orderID = 1, tmpCustID, tmpProdID;
    ResultSet rs, rsProd;
    PreparedStatement st, stProd, stOrder;
    Connection con;
    CategoryAxis xAxis;
    NumberAxis yAxis;
    XYChart.Series chartSeries;
    final Label caption = new Label("");
    private final ProductQueries productQueries = new ProductQueries();
    private final ObservableList<Product> productList = FXCollections.observableArrayList();
    private final ObservableList<String> categoryList = FXCollections.observableArrayList();
    private final ObservableList<Product> listOfProducts = FXCollections.observableArrayList();
    ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
    ObservableList<PieChart.Data> pieChartDataObs = FXCollections.observableArrayList();
    ObservableList<Customer> custList = FXCollections.observableArrayList();
    ObservableList<Customer> custObsList = FXCollections.observableArrayList();
    ArrayList <Order> orderClassArray = new ArrayList<Order>();
    ArrayList <Product> productClassArray = new ArrayList<Product>();
    
    @FXML
    private ListView<Product> listView;
    @FXML
    private Label lblCustomer, lblProduct, lblOrderTotal;
    @FXML
    private Button btnNext, btnPrevious, btnSubmit,
            btnProductNext, btnProductPrev, btnAddProduct,
            btnAllProducts, btnProductsByCategory, btnProductsByQuantity,
            btnProductsByUnitCost, btnOrdersSubmit;
    @FXML
    private TextField txtFirstName, txtLastName, txtPhone,
        txtDescription, txtCategory, txtQuantityOnHand,
        txtUnitCost, txtSellingPrice,
            txtProductsUserQuantity, txtProductsUserCost, txtOrderQuantity;
    @FXML
    private ChoiceBox choiceProductsByCategory, choiceOrderCustomer, choiceOrderProduct;
    @FXML
    private TableView tblDisplay, tblDisplayOrders;
    @FXML
    private TableColumn colProdID, colDescription, colCategory, colQuantity,
            colUnitCost, colSellPrice,
            colOrderID, colOrderProduct, colOrderCustomer, colOrderQuantity, colOrderTotal;
    @FXML
    private PieChart pieChart;
    @FXML
    private BarChart barChart;
    @FXML
    private ComboBox comboCust;
    
    ///////// Customer Buttons //////////////
    @FXML
    private void btnNext(ActionEvent event) throws SQLException {        
        if (!rs.isLast()){
                rs.next();	
            }
        //setup tmp string for the label to display
        String tmpCustomer = "First: " + rs.getString(2) + "    " + 
                             "Last: " + rs.getString(3) + "    " +
                             "Phone: " + rs.getString(4);
        lblCustomer.setText(tmpCustomer);
        /*
        if (rs.isLast()) {
            btnNext.setDisable(true);
        }
        if (!rs.isFirst()) {
            btnPrevious.setDisable(false);
        }
        */
    }
    
    @FXML
    private void btnPrevious(ActionEvent event) throws SQLException {
        if (!rs.isFirst()){
                rs.previous();	
            }
        String tmpCustomer = "First: " + rs.getString(2) + "    " + 
                             "Last: " + rs.getString(3) + "    " +
                             "Phone: " + rs.getString(4);
        lblCustomer.setText(tmpCustomer);
        /*
        if (rs.isFirst()) {
            btnPrevious.setDisable(true);
        }
        if (!rs.isLast()) {
            btnNext.setDisable(false);
        }
                */
    }
    @FXML
    private void btnSubmit(ActionEvent event) throws SQLException {
        //error check
        if (txtFirstName.getLength() > 0 && txtLastName.getLength() > 0 &&
                txtPhone.getLength() > 0) {
            //setup tmp values     
            String tmpFirst = txtFirstName.getText().toString();
            String tmpLast = txtLastName.getText().toString();
            String tmpPhone = txtPhone.getText().toString();

            //statement to insert values from user
            st = con.prepareStatement("INSERT INTO rudejo_Customer " +
                                      "(custID,firstName,lastName,phone) " +
                                      "VALUES (?, ?, ?, ?)");
            //sets the values to insert
            st.setInt(1,custID);
            st.setString(2,tmpFirst);
            st.setString(3,tmpLast);
            st.setString(4,tmpPhone);
            st.executeUpdate();
                                    /*
                                    //update customer drop-down in orders table
                                    String tmpNewCustomer = tmpFirst + " " + tmpLast;
                                    listOfCustomers.add(tmpNewCustomer);
                                    choiceOrderCustomer.setItems(listOfCustomers);
                                    choiceOrderCustomer.setValue("Bob Jones");
                                    */
            // send through customers class
            custList.add(new Customer(custID,tmpFirst, tmpLast, tmpPhone));       
            custObsList.setAll(custList);
            comboCust.setItems(custObsList);
            comboCust.setValue(custList.get(0));  //show the first customer

            //update ID and reset the rs back to the entire table of data
            custID ++;

            st = con.prepareStatement("select * from rudejo_Customer;");
            rs = st.executeQuery();

            //reset fields for user
            txtFirstName.setText("");
            txtLastName.setText("");
            txtPhone.setText("");
            txtFirstName.requestFocus();
            }
        else {
            displayAlert(AlertType.INFORMATION, "Invalid Entry in fields",
                        "Enter a First Name, Last Name, and Phone");
                txtFirstName.requestFocus();
        }
    }
    ///////// END Customer Buttons //////////////
    
    ///////// Product Buttons //////////////
    @FXML
    private void btnProductNext(ActionEvent event) throws SQLException {        
        if (!rsProd.isLast()){
                rsProd.next();	
            }
        //setup tmp string for the label to display
        String tmpProduct = "ProdID: " + rsProd.getInt(1) + "   " +
                    "Description: " + rsProd.getString(2) + "   " +
                    "Category: " + rsProd.getString(3) + "   " + 
                    "QuantityOnHand: " + rsProd.getInt(4) + "   " +
                    "UnitCost: $" + rsProd.getDouble(5) + "   " +
                    "SellingPrice: $" + rsProd.getDouble(6);
        lblProduct.setText(tmpProduct);
    }
    
    @FXML
    private void btnProductPrevious(ActionEvent event) throws SQLException {
        if (!rsProd.isFirst()){
                rsProd.previous();	
            }
        String tmpProduct = "ProdID: " + rsProd.getInt(1) + "   " +
                    "Description: " + rsProd.getString(2) + "   " +
                    "Category: " + rsProd.getString(3) + "   " + 
                    "QuantityOnHand: " + rsProd.getInt(4) + "   " +
                    "UnitCost: $" + rsProd.getDouble(5) + "   " +
                    "SellingPrice: $" + rsProd.getDouble(6);
        lblProduct.setText(tmpProduct);
    }
    @FXML
    private void AddProduct(ActionEvent event) throws SQLException {
        if (txtDescription.getLength() > 0 && txtCategory.getLength() > 0) {
            try {
            //setup tmp values
            String tmpDescription = txtDescription.getText().toString();
            String tmpCategory = txtCategory.getText().toString();
            int tmpQuantity = Integer.parseInt(txtQuantityOnHand.getText().toString());
            double tmpUnitCost = Double.parseDouble(txtUnitCost.getText());
            double tmpSellingPrice = Double.parseDouble(txtSellingPrice.getText());

            //statement to insert values from user
            stProd = con.prepareStatement("INSERT INTO rudejo_Product " +
                                      "(prodID,description,category,quantityOnHand,unitCost,sellingPrice) " +
                                      "VALUES (?, ?, ?, ?, ?, ?)");
            //sets the values to insert
            stProd.setInt(1,prodID);
            stProd.setString(2,tmpDescription);
            stProd.setString(3,tmpCategory);
            stProd.setInt(4,tmpQuantity);
            stProd.setDouble(5,tmpUnitCost);
            stProd.setDouble(6,tmpSellingPrice);
            stProd.executeUpdate();

            //update product list for order page
            String tmpNewProduct = txtDescription.getText();
            productClassArray.add(new Product(prodID,tmpDescription,tmpCategory,
                    tmpQuantity,tmpUnitCost,tmpSellingPrice));
            listOfProducts.setAll(productClassArray);
            choiceOrderProduct.setItems(listOfProducts);
            choiceOrderProduct.setValue(productClassArray.get(0));

            //update ID and reset the rs back to the entire table of data
            prodID ++;

            stProd = con.prepareStatement("select * from rudejo_Product;");
            rsProd = stProd.executeQuery();

            //update category choice box
            String tmpNewCategory = txtCategory.getText();
            categoryList.add(tmpNewCategory); 
            choiceProductsByCategory.setItems(categoryList);                     
            choiceProductsByCategory.setValue("Hardware");

            //reset fields for user       
            txtDescription.setText("");
            txtCategory.setText("");
            txtQuantityOnHand.setText("");
            txtUnitCost.setText("");
            txtSellingPrice.setText("");
            txtDescription.requestFocus();

            System.out.println("Product Added");
            }
            catch (NumberFormatException e) {
                displayAlert(AlertType.INFORMATION, "Invalid Entry in fields",
                        "Enter a valid Quantity as an integer, Unit Cost as a double, and"
                                + "Selling Price as a double");             
                txtQuantityOnHand.requestFocus();
            }
        } else {
            displayAlert(AlertType.INFORMATION, "Invalid Entry in fields",
                        "Enter a Description and Category for the product");
                txtDescription.requestFocus();
        }
    }      
    
    @FXML
    private void getAllProducts() {
        productList.setAll(productQueries.getAllProducts());
        tblDisplay.setItems(productList);
    }

    @FXML
    void productsByCategory(ActionEvent event) {
        productList.setAll(productQueries.getProductsByCategory(choiceProductsByCategory.getValue().toString() + "%"));
        if (productList.size() > 0) {
            tblDisplay.setItems(productList);
        }
        else {
            displayAlert(AlertType.INFORMATION, "No products in category",
                    "Zero products in that category");
        }
    }
    
    @FXML
    void productsByQuantity(ActionEvent event) {
        productList.setAll(productQueries.getProductsByQuantity(txtProductsUserQuantity.getText() + "%"));
        if (productList.size() > 0) {
            tblDisplay.setItems(productList);
        }
        else {
            displayAlert(AlertType.INFORMATION, "No products in quantity range",
                    "Zero products equal or less than provided quantity");
        }
        txtProductsUserQuantity.setText("");
    }
    @FXML
    void productsByUnitCost(ActionEvent event) {
        productList.setAll(productQueries.getProductsByUnitCost(txtProductsUserCost.getText() + "%"));
        if (productList.size() > 0) {
            tblDisplay.setItems(productList);
        }
        else {
            displayAlert(AlertType.INFORMATION, "No products in unit cost range",
                    "Zero products equal or less than provided unit cost");
        }
        txtProductsUserCost.setText("");
    }
    
    
    ///////// END Product  Buttons //////////////
    
    ///////// Order Buttons /////////////////////
    @FXML
    void submitOrder(ActionEvent event) throws SQLException {      
        //error check quantity
        try{
            //grab user product, customer, quantity, and set total cost
            //grab quantity
            int tmpQuantity = Integer.parseInt(txtOrderQuantity.getText());
            if ((listOfProducts.get(tmpProdID-1)).getQuantityOnHand() - tmpQuantity >= 0)
            {
            //grab selling price
            double tmpUnitCost = (listOfProducts.get(tmpProdID-1)).getSellingPrice();       

            //convert to toal
            double tmpTotalCost = tmpQuantity * tmpUnitCost; 
            System.out.println("testing total cost: " + tmpTotalCost);   

            //insert into db table
            stOrder = con.prepareStatement("INSERT INTO rudejo_Order " +
                                      "(OrderID,CustID,ProdID,Qty,OrderTotal) " +
                                      "VALUES (?, ?, ?, ?, ?)");
            stOrder.setInt(1,orderID);
            stOrder.setInt(2,tmpCustID);
            stOrder.setInt(3,tmpProdID);
            stOrder.setInt(4,tmpQuantity);
            stOrder.setDouble(5,tmpTotalCost);
            stOrder.executeUpdate();

            //update new quantities in DB
            int tmpNewQuantity = (listOfProducts.get(tmpProdID-1)).getQuantityOnHand() - tmpQuantity;
            stOrder = con.prepareStatement("UPDATE rudejo_Product " +
                       "SET quantityOnHand = ? " +
                       "WHERE prodID = ?");
            stOrder.setInt(1,tmpNewQuantity);
            stOrder.setInt(2,tmpProdID);
            stOrder.executeUpdate();
            listOfProducts.get(tmpProdID-1).setQuantityOnHand(tmpNewQuantity);

            //return to normal rs
            stProd = con.prepareStatement("select * from rudejo_Product;");
            rsProd = stProd.executeQuery();

            //send through order class
            orderClassArray.add(new Order(orderID,listOfProducts.get(tmpProdID-1).getDescription(),
                    custList.get(tmpCustID-1).getFirstName() + " " + custList.get(tmpCustID-1).getLastName(),
                    tmpQuantity,tmpTotalCost));

            // add to bar chart as orderID quantity sold?
            chartSeries.getData().add(new XYChart.Data(Integer.toString(orderID),tmpQuantity));
            barChart.getData().clear();
            barChart.getData().add(chartSeries);
            //barChart.setData(chartSeries); chartSeries.getData().add(new XYChart.Data("Monitor",5));

        /*
            // add to pie chart as product quantity sold?
            pieChartData.add(new PieChart.Data(listOfProducts.get(tmpProdID-1).getDescription(),tmpQuantity));
                pieChartDataObs.addAll(pieChartData);           
                pieChart.setData(pieChartDataObs);
        */
            //update orderID
            orderID++;

            //update order table
            ObservableList<Order> OrderData = FXCollections.observableArrayList(orderClassArray);
            tblDisplayOrders.setItems(OrderData);

            //populate order total label
            lblOrderTotal.setText(Double.toString(tmpTotalCost));

            //clear fields
            //choiceOrderProduct.setValue(productClassArray.get(0)); // show first prod
            //comboCust.setValue(custList.get(0));  //show the first customer
            txtOrderQuantity.setText("");
            System.out.println("testing tmpProdID: " + tmpProdID);
            System.out.println("testing tmpCustID: " + tmpCustID);
            }
            else {
                displayAlert(AlertType.INFORMATION, "Quantity Exceeded",
                    "Can't order that many products");
            txtOrderQuantity.setText("");
        }
        } 
        catch(NumberFormatException e) {
            displayAlert(AlertType.INFORMATION, "Invalid Quantity",
                    "Enter a valid quantity as an integer");
            txtOrderQuantity.setText("");
        }

    }
    
    ///////// END Order Buttons ////////////////
    
    private void displayAlert(AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       try {
            Class.forName("com.mysql.jdbc.Driver");
            //#OFF_CAMPUS connection:
            //con = DriverManager.getConnection("jdbc:mysql://localhost:3306/rudejo_myDB","rudejo","gc2804kkmysql");
            //st = con.prepareStatement("select * from rudejo_Customer");
            
            //#ON_CAMPUS connection:
            con = DriverManager.getConnection("jdbc:mysql://puff:3306/rudejo_myDB","rudejo","gc2804kkmysql");
            st = con.prepareStatement("select * from rudejo_Customer;");
            rs = st.executeQuery();         
            
            //get last row to setup custID number in order to get correct primaryKeys
            rs.last();
            custID = rs.getRow();
            
            //update ID
            custID ++;
            
            //setup first label for customer
            rs.first();
            String tmpCustomer = "First: " + rs.getString(2) + "    " + 
                             "Last: " + rs.getString(3) + "    " +
                             "Phone: " + rs.getString(4);
            lblCustomer.setText(tmpCustomer);
            txtFirstName.requestFocus();
            
            //setup first label for product
            stProd = con.prepareStatement("select * from rudejo_Product;");
            rsProd = stProd.executeQuery();
            rsProd.last();
            prodID = rsProd.getRow();
            prodID ++;
            rsProd.first();
            String tmpProduct = "ProdID: " + rsProd.getInt(1) + "    " +
                    "Description: " + rsProd.getString(2) + "    " +
                    "Category: " + rsProd.getString(3) + "    " + 
                    "QuantityOnHand: " + rsProd.getInt(4) + "    " +
                    "UnitCost: $" + rsProd.getDouble(5) + "    " +
                    "SellingPrice: $" + rsProd.getDouble(6);
            lblProduct.setText(tmpProduct);

            //create drop-down menu for category. set value to hardware so there is no null
            categoryList.addAll("Hardware","Software");           
            choiceProductsByCategory.setItems(categoryList);
            choiceProductsByCategory.setValue("Hardware");
            
            //create drop-down for product box in order page 
            productClassArray.add(new Product(1,"Monitor","Hardware",5,150,200));
            productClassArray.add(new Product(2,"Desktop","Hardware",10,450,500));
            productClassArray.add(new Product(3,"M.S. Office","Software",1,40,60));
            listOfProducts.setAll(productClassArray);
            choiceOrderProduct.setValue(productClassArray.get(0)); //show first product ID
            tmpProdID = 1;                                         //set first ID
            choiceOrderProduct.setItems(listOfProducts);
            //listen to selected product for order
            choiceOrderProduct.valueProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue obs, Object oldItem, Object newItem) {
                String selectedProd = choiceOrderProduct.getValue().toString();
                System.out.println("Selected Product: " + selectedProd + "**");
                //tmpProdID = Integer.valueOf(selectedProd);   
                tmpProdID = Integer.parseInt(selectedProd);
            }
            });
            
            //create drop-down for customer box for orders. hardcode starting customers
            custList.add(new Customer(1,"Bob", "Jones", "123-123-1234"));
            custList.add(new Customer(2,"Jim", "Anderson", "321-321-3214"));
            //initialize the ObserveableList for customers
            custList.forEach((Customer cust) -> {
                custObsList.add(cust);
            });
            comboCust.setValue(custList.get(0));  //show the first customer
            tmpCustID = 1;                        //set value to first customer
            comboCust.setItems(custObsList);
            //listen to selected customer for order
            comboCust.valueProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue obs, Object oldItem, Object newItem) {
                String selectedCust = comboCust.getValue().toString();
                System.out.println("Selected Customer: " + selectedCust + "**");
                String tmpList[ ] = selectedCust.split(" ");
                tmpCustID = Integer.valueOf(tmpList[0]);
                String custFName = tmpList[1];
                String custLName = tmpList[2];
                String custPh = tmpList[3];
                System.out.println("Customer Fields:\n" + tmpCustID + "\n" 
                        + custFName + "\n" +  custLName
                        + "\n" + custPh + "\n***");
            }
            });
                       
            //product table fields 
            colProdID.setCellValueFactory(
                    new PropertyValueFactory<>("productID"));
            colDescription.setCellValueFactory(
                    new PropertyValueFactory<>("description"));
            colCategory.setCellValueFactory(
                    new PropertyValueFactory<>("category"));
            colQuantity.setCellValueFactory(
                    new PropertyValueFactory<>("quantityOnHand"));
            colUnitCost.setCellValueFactory(
                    new PropertyValueFactory<>("unitCost"));
            colSellPrice.setCellValueFactory(
                    new PropertyValueFactory<>("sellingPrice"));
            
            //order table fields    
            colOrderID.setCellValueFactory(
                    new PropertyValueFactory<>("orderID"));
            colOrderProduct.setCellValueFactory(
                    new PropertyValueFactory<>("productName"));
            colOrderCustomer.setCellValueFactory(
                    new PropertyValueFactory<>("customerName"));
            colOrderQuantity.setCellValueFactory(
                    new PropertyValueFactory<>("quantity"));
            colOrderTotal.setCellValueFactory(
                    new PropertyValueFactory<>("totalCost"));
            
            //pie chart, populate data from products and quantities
            productClassArray.forEach((Product prod) -> {
                pieChartData.add(new PieChart.Data(prod.getDescription(),prod.getQuantityOnHand()));
            });
            pieChartDataObs.addAll(pieChartData);           
            pieChart.setData(pieChartDataObs);
            //attempt to add mouse listener
            caption.setTextFill(Color.DARKORANGE);
            caption.setStyle("-fx-font: 24 arial;");
            for (final PieChart.Data data : pieChart.getData()) {
            data.getNode().addEventHandler(MouseEvent.MOUSE_PRESSED,
                new EventHandler<MouseEvent>() {
                    @Override public void handle(MouseEvent e) {
                        caption.setTranslateX(e.getSceneX());
                        caption.setTranslateY(e.getSceneY());
                        caption.setText(String.valueOf(data.getPieValue()));
                     }
                });
            }
            
            //bar chart
            xAxis = new CategoryAxis();
            yAxis = new NumberAxis();           
            //barChart = new BarChart<String,Number>(xAxis,yAxis);
            chartSeries = new XYChart.Series();
            barChart.getXAxis().setLabel("Order ID");
            barChart.getYAxis().setLabel("Quantity Sold");
            //chartSeries.getData().add(new XYChart.Data("Monitor",5));
            //chartSeries.getData().add(new XYChart.Data("Desktop",2));
            //chartSeries.getData().add(new XYChart.Data("M.S. Office",0));
           // barChart.getData().add(chartSeries);
                    
        } //end main
        catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("caught");
        }
    }   
}       

//bar chart
//check if they are trying to order more than what is available

