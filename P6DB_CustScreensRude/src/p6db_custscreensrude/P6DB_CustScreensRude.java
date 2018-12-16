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
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
/**
 *
 * @author Joe
 */
public class P6DB_CustScreensRude extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
  
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
        
     
        
    }      
        /*
        Class.forName("com.mysql.jdbc.Driver");       
        //#OFF_CAMPUS connection:
        //Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/rudejo_myDB","rudejo","********");
            //PreparedStatement st = con.prepareStatement("select * from rudejo_Customer");

        //#ON_CAMPUS connection:
        Connection con = DriverManager.getConnection("jdbc:mysql://puff:3306/rudejo_myDB","rudejo","********");
            PreparedStatement st = con.prepareStatement("select * from rudejo_Customer");
        
        ResultSet rs = st.executeQuery();
            while (rs.next()){
                      System.out.println(rs.getString(2));	
            }//end while
        }//end main
        */
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
