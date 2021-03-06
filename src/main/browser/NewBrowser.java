/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.browser;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Brandon
 */
public class NewBrowser extends Application {
    @FXML
    private Button minBtn;
    private double xOffset = 0;
    //private double yOffset = 0;
    
    @Override
    public void start(Stage stage) throws Exception {    
   
  
        Parent root = FXMLLoader.load(getClass().getResource("webfxml.fxml"));
        Scene scene = new Scene(root);
        
        scene.getStylesheets().add("/main/resources/css/main.css");
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment()
        .getDefaultScreenDevice();
        //gets resolution info
        int width = gd.getDisplayMode().getWidth();
        int height = gd.getDisplayMode().getHeight();
        
        System.out.println("Resolution: " + width + ", " + height);
        if(width < 720){
         Image fireIcon = new Image("/main/resources/icons/firebox_logo_32px.png");
        stage.getIcons().add(fireIcon);
        }
        else if(width >= 720|| width < 1000 ){
        Image fireIcon = new Image("/main/resources/icons/firebox_logo_64px.png");
        stage.getIcons().add(fireIcon);
        }
        else if(width >= 1000 || width < 1200 ){
        Image fireIcon = new Image("/main/resources/icons/firebox_logo_128px.png");
        stage.getIcons().add(fireIcon);
        }
        else if(width >= 1200|| width < 1900 ){
        Image fireIcon = new Image("/main/resources/icons/fireboxx_logo256px.png");
        stage.getIcons().add(fireIcon);
        }
        else if (width >= 1900){
        Image fireIcon = new Image("/main/resources/icons/firebox_logo_512px.png");
        stage.getIcons().add(fireIcon);
        }
        else{
        Image fireIcon = new Image("/main/resources/icons/firebox_logo256px.png");
        stage.getIcons().add(fireIcon);
        }
        stage.setMinHeight(height/2.5);
        stage.setMinWidth(width/3);        
        stage.initStyle(StageStyle.UNDECORATED);
        
        
        //moveable scene

        stage.setTitle("Fireybox");
        stage.setScene(scene);
        stage.show();  
        WindowStyle.stageDimension(xOffset, xOffset);
        ResizeHelper.addResizeListener(stage);        
       
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        System.setProperty("sun.net.http.allowRestrictedHeaders", "true");
        launch(args); 
    }
}
