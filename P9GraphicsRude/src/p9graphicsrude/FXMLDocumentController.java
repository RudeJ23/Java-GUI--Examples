/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p9graphicsrude;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;

/**
 *
 * @author Joe Rude
 */
public class FXMLDocumentController implements Initializable {
    GraphicsContext gcBtns, gcDraw;
    
    @FXML
    private TextField txtUserText;
    @FXML
    private Label lblMouse;
    @FXML
    private Canvas canvasBtns, canvasDraw;
    @FXML
    private Button btnClear, btnDrawLine, btnDrawCircle, btnDrawSquare, btnDrawZ;
    
    @FXML
    private void drawLine(ActionEvent event) {
        gcBtns.strokeLine(80, 20, 20, 80);
    }
    @FXML
    private void drawCircle(ActionEvent event) {
        gcBtns.fillOval(25, 120, 75, 75);
    }
    @FXML
    private void drawSquare(ActionEvent event) {
        gcBtns.fillRoundRect(140, 15, 80, 80, 10, 10);
    }
    @FXML
    private void drawZ(ActionEvent event) {
        gcBtns.setLineWidth(10);
        gcBtns.strokePolyline(new double[]{160, 190, 160, 190},
                          new double[]{140, 140, 170, 170}, 4);
        gcBtns.setLineWidth(5);

    }
    @FXML
    private void clearCanvas(ActionEvent event) {
        gcBtns.setFill(Color.WHITE);
        gcBtns.fillRect(0, 0, canvasBtns.getWidth(), canvasBtns.getHeight());
        gcBtns.setFill(Color.GREEN);
        gcBtns.setStroke(Color.BLUE);
    }
    
    /*   ***These were the shapes from the tutorial, I used them to learn what 
            the parameters and methods were***
    @FXML
    private void drawShapes(GraphicsContext gc) {
        gc.setFill(Color.GREEN);
        gc.setStroke(Color.BLUE);
        gc.setLineWidth(5);
        gc.strokeLine(40, 10, 10, 40);
        gc.fillOval(10, 60, 30, 30);
        gc.strokeOval(60, 60, 30, 30);
        gc.fillRoundRect(110, 60, 30, 30, 10, 10);
        gc.strokeRoundRect(160, 60, 30, 30, 10, 10);
        gc.fillArc(10, 110, 30, 30, 45, 240, ArcType.OPEN);
        gc.fillArc(60, 110, 30, 30, 45, 240, ArcType.CHORD);
        gc.fillArc(110, 110, 30, 30, 45, 240, ArcType.ROUND);
        gc.strokeArc(10, 160, 30, 30, 45, 240, ArcType.OPEN);
        gc.strokeArc(60, 160, 30, 30, 45, 240, ArcType.CHORD);
        gc.strokeArc(110, 160, 30, 30, 45, 240, ArcType.ROUND);
        gc.fillPolygon(new double[]{10, 40, 10, 40},
                       new double[]{210, 210, 240, 240}, 4);
        gc.strokePolygon(new double[]{60, 90, 60, 90},
                         new double[]{210, 210, 240, 240}, 4);
        gc.strokePolyline(new double[]{110, 140, 110, 140},
                          new double[]{210, 210, 240, 240}, 4);
    }
    */
    @FXML
    private void reset(Canvas canvas, Color color) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(color);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //CanvasBtns will draw shapes as the user presses buttons.
        //CanvasDraw will allow the user to draw on a canvas with a mouse press.
        
        
        //setup shapes+btns canvas
        gcBtns = canvasBtns.getGraphicsContext2D();
        //start with a white canvas
        gcBtns.setFill(Color.WHITE);
        gcBtns.fillRect(0, 0, canvasBtns.getWidth(), canvasBtns.getHeight());
        //prepare drawing tools
        gcBtns.setFill(Color.GREEN);
        gcBtns.setStroke(Color.BLUE);
        gcBtns.setLineWidth(5);       
        
        //setup drawing canvas
        gcDraw = canvasDraw.getGraphicsContext2D();
        gcDraw.setFill(Color.BLACK);
        gcDraw.fillRect(0, 0, canvasDraw.getWidth(), canvasDraw.getHeight());
        
        // Clear away portions as the user drags the mouse
        canvasDraw.addEventHandler(MouseEvent.MOUSE_DRAGGED, 
        new EventHandler<MouseEvent>() {
           @Override
           public void handle(MouseEvent e) {
               gcDraw.clearRect(e.getX() - 2, e.getY() - 2, 5, 5);
           }
        });
        // Fill the Canvas with a Black rectangle when the user double-clicks
        canvasDraw.addEventHandler(MouseEvent.MOUSE_CLICKED, 
            new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent t) {            
                    if (t.getClickCount() >1) {
                        reset(canvasDraw, Color.BLACK);
                    }  
                }
            });
       
       
        // mouse enter/exit drawing canvas
        canvasDraw.setOnMouseEntered(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                lblMouse.setText("The mouse is on the drawing canvas!");
                }
        });

        canvasDraw.setOnMouseExited(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                lblMouse.setText("The mouse is not currently on the drawing canvas");
            }
        });
       
        
        // textbox for keyboard keys on press
        txtUserText.setOnKeyPressed(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent ke) {
                System.out.println("You Pressed: " + ke.getText());
            }
        });

        txtUserText.setOnKeyReleased(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent ke) {
                System.out.println("You Released: " + ke.getText());
            }
        });
       
    }    
}
