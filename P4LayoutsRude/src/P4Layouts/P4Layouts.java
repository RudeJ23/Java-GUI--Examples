/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package P4Layouts;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

/**
 *
 * @author Joe Rude
 * Date: 9/25/17
 * Description: Project 4, Multiple Layouts
 */
public class P4Layouts extends Application {
    BorderPane bPane;
    TilePane tPane;
    GridPane gPane;
    HBox hbox;
    Button btnAdd, btnSub, btnMul, btnDiv;
    Label lblNumber1, lblNumber2, lblAnswer;
    TextField txtNumber1, txtNumber2, txtAnswer;
    float num1, num2;
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        bPane = new BorderPane();
        createLeft();
        createCenter();
        createBottom();
        bPane.setLeft(tPane);
        bPane.setCenter(gPane);
        bPane.setBottom(hbox);
        Scene scene = new Scene(bPane, 400, 400);
        primaryStage.setTitle("P4 Layouts:");
        primaryStage.setScene(scene);
        primaryStage.show();
        txtNumber1.requestFocus();
    }
    
    public void createLeft() {
        tPane = new TilePane();
        tPane.setOrientation(Orientation.VERTICAL);
        tPane.setTileAlignment(Pos.TOP_LEFT);
        
        btnAdd = new Button("Add"); btnSub = new Button("Sub");
        btnMul = new Button("Mul"); btnDiv = new Button("Div");
        
        tPane.getChildren().addAll(btnAdd,btnSub,btnMul,btnDiv);
        tPane.setPadding(new Insets(0, 0, 0, 5));       
        
        btnAdd.setOnAction((ActionEvent e)-> {
            num1 = Float.parseFloat(txtNumber1.getText());
            num2 = Float.parseFloat(txtNumber2.getText());
            txtAnswer.setText(num1 + " + " + num2 + " = " + (num1+num2));                                                                     
            txtNumber1.setText(""); txtNumber2.setText("");
            txtNumber1.requestFocus();      
        });
        
        btnSub.setOnAction((ActionEvent e)-> {
            num1 = Float.parseFloat(txtNumber1.getText());
            num2 = Float.parseFloat(txtNumber2.getText());
            txtAnswer.setText(num1 + " - " + num2 + " = " + (num1-num2));                                                                     
            txtNumber1.setText(""); txtNumber2.setText("");
            txtNumber1.requestFocus();      
        });
        
        btnMul.setOnAction((ActionEvent e)-> {
            num1 = Float.parseFloat(txtNumber1.getText());
            num2 = Float.parseFloat(txtNumber2.getText());
            txtAnswer.setText(num1 + " * " + num2 + " = " + (num1*num2));                                                                     
            txtNumber1.setText(""); txtNumber2.setText("");
            txtNumber1.requestFocus();      
        });
        
        btnDiv.setOnAction((ActionEvent e)-> {
            num1 = Float.parseFloat(txtNumber1.getText());
            num2 = Float.parseFloat(txtNumber2.getText());
            if (num2 != 0) 
            {
                txtAnswer.setText(num1 + " / " + num2 + " = " + (num1/num2)); 
            }
            else 
            {
                txtAnswer.setText("Can't divide by 0");
                
            }  
            txtNumber1.setText(""); txtNumber2.setText("");
            txtNumber1.requestFocus();
        });
        
    }
    
    public void createCenter() {
        gPane = new GridPane();
        gPane.setHgap(10);
        gPane.setVgap(10);
        gPane.setPadding(new Insets(20,20,20,20));
        lblNumber1 = new Label("Number 1"); lblNumber2 = new Label("Number 2");
        txtNumber1 = new TextField(); txtNumber2 = new TextField();
        gPane.add(lblNumber1,0,0); gPane.add(txtNumber1,1,0);
        gPane.add(lblNumber2,0,1); gPane.add(txtNumber2,1,1);
        txtNumber1.requestFocus();
    }
    
    public void createBottom() {
        hbox = new HBox();
        lblAnswer = new Label("Answer: "); txtAnswer = new TextField();
        txtAnswer.setEditable(false);
        //txtAnswer.setDisable(true);   disables box but grays it out
        //txtAnswer.setFont(Font.font("Verdana", FontWeight.BOLD, 12)); font change
        hbox.getChildren().addAll(lblAnswer,txtAnswer);
        hbox.setPadding(new Insets(0,0,0,5));      
    }
}
