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
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author Joe
 */
public class Tab2Controller {
    private MainController main;
    @FXML public Label lblFullName, lbl2GrossPay, lblStateTax, lblNetPay;
    @FXML public Button btnCalculateNetPay;
    
    
    
    @FXML private void calculateNetPay (ActionEvent e) {
        // tab 1 has already sent data to tab 2    
        
        // calculate state tax and net pay
        double grossPay = Double.parseDouble(lbl2GrossPay.getText());
        double stateTax = grossPay * .07;
        lblStateTax.setText(Double.toString(stateTax));
        double netPay = grossPay - stateTax;
        lblNetPay.setText(Double.toString(netPay));
        
    }
    
    public void init(MainController aThis) {
        main = aThis;
    }

}
