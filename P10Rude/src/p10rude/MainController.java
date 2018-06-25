/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p10rude;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 *
 * @author Joe
 */
public class MainController implements Initializable {
    @FXML Tab1Controller tab1Controller;
    @FXML Tab2Controller tab2Controller;

    
   
    @FXML public void initialize(URL url, ResourceBundle rb) {
        // TODO
        tab1Controller.init(this);
        tab2Controller.init(this);
        tab1Controller.txtFirst.requestFocus();
    }    
    
    public void loadGrossPayFromTab1(String text) {
        //return tab1Controller.lbl1GrossPay.getText();
        tab2Controller.lbl2GrossPay.setText(text);
    }
    
    public void loadFullNameFromTab1(String text) {
        //return tab1Controller.txtFirst.getText() + " " + tab1Controller.txtLast.getText();
        tab2Controller.lblFullName.setText(text);
    }
    
}
