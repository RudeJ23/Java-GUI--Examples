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
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Joe
 */
public class Tab1Controller {
    private MainController main;
    @FXML public Label lbl1GrossPay;
    @FXML public TextField txtFirst, txtLast, txtHours, txtPayrate;
    @FXML public Button btnCalculate;
    
    @FXML private void calculateGrossPay(ActionEvent e) {
        // calculate gross pay from hours * payrate
        // if hours > 40, then any hours over 40 are 1.5*
        double hours = Double.parseDouble(txtHours.getText());
        double payrate = Double.parseDouble(txtPayrate.getText());
        double normalPay = 0;
        double extraPay = 0;
        if (hours > 40) {
            double extraHours = hours - 40;
            extraPay = extraHours * 1.5 * payrate;
            normalPay = 40 * payrate;
        }
        if (hours <= 40) {
            normalPay = hours * payrate;
        }
        double grossPay = normalPay + extraPay;
        lbl1GrossPay.setText(Double.toString(grossPay));
        
        String tmpName = txtFirst.getText() + " " + txtLast.getText();
        
        // send full name and gross pay to tab 2
        main.loadFullNameFromTab1(tmpName);
        main.loadGrossPayFromTab1(lbl1GrossPay.getText());
        
        //reset fields 
        txtFirst.setText("");
        txtLast.setText("");
        txtHours.setText("");
        txtPayrate.setText("");
        txtFirst.requestFocus();
        
    }
    
    public void init(MainController aThis) {
        main = aThis;
        txtFirst.requestFocus();
    }
    
}
