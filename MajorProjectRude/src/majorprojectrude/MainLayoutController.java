/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package majorprojectrude;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author Joe
 */
public class MainLayoutController implements Initializable {
    File file;
    Stage stage;
    ArrayList <String> fileArray = new ArrayList <String>();
    ArrayList <String> productArray = new ArrayList <String>();
    ArrayList <String> priceArray = new ArrayList <String>();
    ArrayList <String> orderArray = new ArrayList <String>();
    
    ArrayList <Product> productClassArray = new ArrayList<Product>();
    ArrayList <Order> orderClassArray = new ArrayList<Order>();
    ObservableList<String> obList = FXCollections.observableList(productArray);

    
    
    private static DecimalFormat df2 = new DecimalFormat(".##");
    String tmpQuantity;
    String tmpShipping;
    int ct = 0;
    int tmpPrice;
    int tmpPriceCents;
    ToggleGroup shippingGroup, quantityGroup;
    
    
    @FXML
    private TextField txtProductName, txtProductPrice, txtProductPriceCents,
            txtCustFirst, txtCustLast;
    @FXML
    private Button btnAddProduct, btnDisplayAllProducts, btnOrder, btnSave;
    @FXML
    private Label lblOrdersScreenTotal, lblOffers;
    @FXML
    private ChoiceBox choiceBoxProductList;
    @FXML
    private CheckBox checkBoxOffers;
    @FXML
    private RadioButton rdoStandardShip, rdoTwoDayShip, rdoQuantity1,
            rdoQuantity2, rdoQuantity3;
    @FXML
    private TableView tableSummary;
    @FXML
    private TableColumn colFirstName, colLastName, colProduct, colQuantity,
            colShipping, colTotal;
    
    @FXML
    public void exitApplication(ActionEvent event) {
        Platform.exit();
    }
    
    @FXML
    public void saveProducts(ActionEvent event) {                
        try {
            final FileChooser fileChooser = new FileChooser();
            file = fileChooser.showSaveDialog(stage);
            BufferedWriter bWriter = new BufferedWriter(new FileWriter(file));
            for (int i=0; i<fileArray.size(); i++) 
            {
                bWriter.write(fileArray.get(i).toString());
                bWriter.newLine();
            }
            bWriter.close();
        } catch (IOException ex) {
            Logger.getLogger(MainLayoutController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }  
    @FXML
    private void AddProduct(ActionEvent event) {
        //check validation     
        try {
            if (txtProductName.getText().equals("")) 
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Message");
                alert.setHeaderText("No Name Given");
                alert.setContentText("");
                alert.showAndWait();
            }
            else if (txtProductPriceCents.getLength() != 2) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Message");
                alert.setHeaderText("2 Digits required for Cents Field");
                alert.setContentText("");
                alert.showAndWait();
            }
            else {
                //add prices and names to each array
                tmpPrice = Integer.parseInt(txtProductPrice.getText());
                tmpPriceCents = Integer.parseInt(txtProductPriceCents.getText());
                String tmpFinalPrice = tmpPrice + "." + tmpPriceCents;
                fileArray.add(txtProductName.getText());
                fileArray.add(tmpFinalPrice);
                productArray.add(txtProductName.getText()+ "\n");
                priceArray.add(tmpFinalPrice+ "\n");
                System.out.println("added product");   
                
                productClassArray.add(new Product(txtProductName.getText().toString(),
                    Double.parseDouble(tmpFinalPrice)));
                
                /* ----trying to get choice box to work -----
                obList.addListener(new ListChangeListener() {
                    @Override
                    public void onChanged(ListChangeListener.Change change) {
                        System.out.println("Change has happened");
                        while (change.next()) {               
                            choiceBoxProductList.setItems(obList);
                        }//end while
                    }//end onChanged
                });//end listener
                //obList.add(txtProductName.getText()+ "\n");
                //choiceBoxProductList.getItems().clear();
                //ObservableList<String> obList2 = FXCollections.observableArrayList(productArray);
                //obList = FXCollections.observableList(productArray);        
                ObservableList<String> obList = FXCollections.observableList(productArray);
                choiceBoxProductList.setItems(obList);
                //ObservableList obList = FXCollections.observableList(productArray);
                //choiceBoxProductList = new ChoiceBox(FXCollections.observableArrayList(productArray));
                //choiceBoxProductList.setItems(obList);
                //choiceBoxProductList.setItems(FXCollections.observableArrayList(productArray));
                */
            }
        }
        //catch digits
        catch(NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Message");
            alert.setHeaderText("Digits Required for Price");
            alert.setContentText("");
            alert.showAndWait();
        }               
        //clear and set focus
        txtProductName.setText("");
        txtProductPrice.setText("");
        txtProductPriceCents.setText("");
        txtProductName.requestFocus();
    }
    
    @FXML
    private void DisplayAllProducts(ActionEvent event) {
        //System.out.println(fileArray);
        for (Product p: productClassArray) 
                {
                    System.out.println(p);        
                }
        txtProductName.setText("");
        txtProductPrice.setText("");
        txtProductPriceCents.setText("");
        txtProductName.requestFocus();
    }
    
    @FXML
    private void displayOffers(ActionEvent event) {
        if (checkBoxOffers != null)
        {
            lblOffers.setText("You will receive news about future deals!");
        }
        if (checkBoxOffers.isSelected() == false)
        {
            lblOffers.setText("Currently not receiving future special offers.");
        }
    }
    
    @FXML
    private void orderProduct(ActionEvent event) {
        String tmpFirstName = txtCustFirst.getText();
        String tmpLastName = txtCustLast.getText();
        String tmpProduct = choiceBoxProductList.getValue().toString();
        
        int tmpQuantity2 = Integer.parseInt(tmpQuantity);
        int tmpShipping2 = Integer.parseInt(tmpShipping);          
        int tmpProductIndex = productArray.indexOf(tmpProduct);
        String tmpPrice = (priceArray.get(tmpProductIndex));
        double tmpPrice2 = Double.parseDouble(tmpPrice);
        
        double tmpTotal = tmpPrice2 * tmpQuantity2 + tmpShipping2;
        df2.format(tmpTotal);
        lblOrdersScreenTotal.setText(Double.toString(tmpTotal));
        
        List<String> tmpOrderArray = new ArrayList<String>();
        tmpOrderArray.add(tmpFirstName);
        tmpOrderArray.add(tmpLastName);
        tmpOrderArray.add(tmpProduct);
        tmpOrderArray.add(tmpQuantity);
        tmpOrderArray.add(tmpShipping);     
        tmpOrderArray.add(Double.toString(tmpTotal));
        orderArray.addAll(tmpOrderArray);
        
        orderClassArray.add(new Order(tmpFirstName,tmpLastName,tmpProduct,
            tmpQuantity2,tmpShipping2,tmpTotal));
        
        txtCustFirst.requestFocus();
        txtCustFirst.setText("");
        txtCustLast.setText("");       
        checkBoxOffers.setSelected(false);
        shippingGroup.selectToggle(null);
        quantityGroup.selectToggle(null);
        btnOrder.setDisable(true);
        /*
        for (Order o: orderClassArray) 
        {
            System.out.println(o);        
        }
        System.out.println("order class printed");
        System.out.println(tmpOrderArray + " tmp order");
        System.out.println(orderArray + " order array");
        System.out.println("hi");       
        */
        
        ObservableList<Order> testingOrderData = FXCollections.observableArrayList(orderClassArray);
        tableSummary.setItems(testingOrderData);
        
    }
    
/* ---attempt to save on closing of application---
    //save method for closing
    @FXML
    public void stop(){
    System.out.println("Stage is closing");
        // Save file
        try {
            final FileChooser fileChooser = new FileChooser();
            file = fileChooser.showSaveDialog(stage);
            BufferedWriter bWriter = new BufferedWriter(new FileWriter(file));
            for (int i=0; i<fileArray.size(); i++) 
            {
                bWriter.write(fileArray.get(i).toString());
                bWriter.newLine();
            }
            bWriter.close();
        } catch (IOException ex) {
                Logger.getLogger(MainLayoutController.class.getName()).log(Level.SEVERE, null, ex);
          }
    }
*/   
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //opens file on startup
        final FileChooser fileChooser = new FileChooser();
        file = fileChooser.showOpenDialog(stage);
            if (file == null)
                System.out.println("no file");
            if (file != null)
            {
                try {
                    //open file and save to arraylist
                    System.out.println("attempting to read lines");
                    BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                    String line = bufferedReader.readLine();
                    while (line != null)
                    {
                        fileArray.add(line);
                        line = bufferedReader.readLine();

                    }
                    System.out.println(fileArray);
                    bufferedReader.close();
                }
                catch (FileNotFoundException ex) {
                    Logger.getLogger(MainLayoutController.class.getName()).log(Level.SEVERE, null, ex);
                }   
                catch (IOException ex) {
                    Logger.getLogger(MainLayoutController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            for (int i=0; i<fileArray.size(); )
            {
                productArray.add(fileArray.get(i));
                i++;
                i++;
            }
            for (int i=1; i<fileArray.size(); )
            {
                priceArray.add(fileArray.get(i));
                i++;
                i++;
            }
            
            for (int i=0; i<productArray.size(); )
            {
               productClassArray.add(new Product(productArray.get(i),
                    Double.parseDouble(priceArray.get(i))));
               i++;
            }
            
            System.out.println(productArray);
            System.out.println(priceArray);
            
            shippingGroup = new ToggleGroup();
            rdoStandardShip.setToggleGroup(shippingGroup);
            rdoTwoDayShip.setToggleGroup(shippingGroup);
            rdoStandardShip.setUserData("5");
            rdoTwoDayShip.setUserData("10");
            
            quantityGroup = new ToggleGroup();
            rdoQuantity1.setToggleGroup(quantityGroup);
            rdoQuantity2.setToggleGroup(quantityGroup);
            rdoQuantity3.setToggleGroup(quantityGroup);
            rdoQuantity1.setUserData("1");
            rdoQuantity2.setUserData("2");
            rdoQuantity3.setUserData("3");
            
            
            //list = FXCollections.observableList(productArray);
            choiceBoxProductList.setItems(obList);    
            //choiceBoxProductList = new ChoiceBox(FXCollections.observableArrayList(productArray));
            //choiceBoxProductList.setItems(FXCollections.observableArrayList(productArray));
            
            quantityGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            public void changed(ObservableValue<? extends Toggle> ov,
                    Toggle old_toggle, Toggle new_toggle) {
                try {
                tmpQuantity = quantityGroup.getSelectedToggle().getUserData().toString();
                }
                catch (NullPointerException e) {
                    tmpQuantity = "";
                }
                
                if (txtCustFirst != null && txtCustFirst.getText().equals("") == false &&
                    txtCustLast != null && txtCustLast.getText().equals("") == false &&
                    choiceBoxProductList.getValue() != null &&
                    tmpQuantity != null && tmpQuantity != "" && 
                        tmpShipping != null && tmpShipping != ""
                        && quantityGroup.getSelectedToggle().getUserData().toString() != null
                        && shippingGroup.getSelectedToggle().getUserData().toString() != null)
                {
                    btnOrder.setDisable(false);
                }
                else
                {
                    btnOrder.setDisable(true);
                }
    
            }
            });
            
            shippingGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            public void changed(ObservableValue<? extends Toggle> ov,
                    Toggle old_toggle, Toggle new_toggle) {
                try {
                tmpShipping = shippingGroup.getSelectedToggle().getUserData().toString();
                }
                catch (NullPointerException e) {
                    tmpShipping = "";
                }
                
                //disable order button until all fields are filled
                if (txtCustFirst != null && txtCustFirst.getText().equals("") == false &&
                    txtCustLast != null && txtCustLast.getText().equals("") == false &&
                    choiceBoxProductList.getValue() != null &&
                    tmpQuantity != null && tmpQuantity != "" 
                        && tmpShipping != null && tmpShipping != ""
                        && quantityGroup.getSelectedToggle().getUserData().toString() != null
                        && shippingGroup.getSelectedToggle().getUserData().toString() != null)
                {
                    btnOrder.setDisable(false);
                }
                else
                {
                    btnOrder.setDisable(true);
                }
            }
            });
            
            //label graphic for requirements
            ImageView imageLbl = new ImageView("sale.png");
            imageLbl.setFitHeight(100);
            imageLbl.setFitWidth(100);
            imageLbl.setPreserveRatio(true);           
            lblOffers.setGraphic(imageLbl);
            
            ImageView imageAddProduct = new ImageView("add.png");
            imageAddProduct.setFitHeight(100);
            imageAddProduct.setFitWidth(100);
            imageAddProduct.setPreserveRatio(true);
            btnAddProduct.setGraphic(imageAddProduct);
       
            colFirstName.setCellValueFactory(
                    new PropertyValueFactory<>("custFirstName"));
            colLastName.setCellValueFactory(
                    new PropertyValueFactory<>("custLastName"));
            colProduct.setCellValueFactory(
                    new PropertyValueFactory<>("productName"));
            colQuantity.setCellValueFactory(
                    new PropertyValueFactory<>("quantity"));
            colShipping.setCellValueFactory(
                    new PropertyValueFactory<>("shippingCost"));
            colTotal.setCellValueFactory(
                    new PropertyValueFactory<>("totalCost"));
            
    }    
    
}
//product list changing in choice box****
//decimal fixing*** on table
