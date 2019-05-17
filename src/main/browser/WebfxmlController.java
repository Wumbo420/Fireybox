/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.browser;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import java.util.ArrayList;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.web.WebHistory;


/**
 * FXML Controller class
 *
 * @author Brandon
 */
public class WebfxmlController implements Initializable {
    @FXML
    private TextField txt;
    @FXML
    private Button bt;
    @FXML
    private Button refresh;
    @FXML
    private AnchorPane anc;
    @FXML
    private WebView webView;
    @FXML
    private TabPane tabPane;
    @FXML
    private Button closeBtn;
 
    private String toolbar;
    private String http;
    private String ww;
    private ArrayList search;
    private ArrayList history;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        WebEngine we = webView.getEngine();
        we.setJavaScriptEnabled(true);
        we.load( "https://firebox.netlify.com/");
        //arraylist for now make a text input stack later
        search = new ArrayList();
        
        Image closed = new Image(getClass().getResourceAsStream("/main/resources/images/xit.png"));
        closeBtn.setGraphic(new ImageView(closed));
        closeBtn.setScaleX(.4);
        closeBtn.setScaleY(.4);
        closeBtn.maxHeight(10);
        closeBtn.maxWidth(10);        
        //EventHandler for search and url input
        EventHandler<ActionEvent> enter = (ActionEvent event) -> {
        toolbar = txt.getText();
        http = toolbar.substring(0, toolbar.length());
            search.add(0, http);
            //search all sites to be opened directly by domain
            if (toolbar.length() > 2) { 
                ww = toolbar.substring(0, 3);
                //routes towards a search engine is domain not provided
                if ("www".equals(ww)) {
                    we.load(txt.getText().startsWith("http://") ? txt.getText()
                            : "http://" + txt.getText());
                    if (toolbar.length() >= 6 && !"www"
                            .equals(http.substring(0, 3))) {
                        if ("http://".equals(http.substring(0, 6))) {
                            we.load(txt.getText().startsWith("http://")
                                    ? txt.getText() : txt.getText());
                        }
                    } else {
                        we.load(txt.getText().startsWith("http://") ? txt.getText()
                                : "https://duckduckgo.com/?q=" + txt.getText());}
                } else {
                    if (txt.getText().length() < 26) {
                        we.load(txt.getText().startsWith("http://") ? txt.getText()
                                : "https://duckduckgo.com/?q=" + search.get(0));
                    } else { //duckduckgo search
                        if ("https://duckduckgo.com/?q=".equals(http.substring(0, 26))) {
                            we.load(txt.getText().startsWith("http://")
                                    ? txt.getText() : "https://duckduckgo.com/?q="
                                    + http.substring(26, (search.get(0)
                                            .toString().length())));
                        } else { //http://searches
                            if ("http://".equals(http.substring(0, 6))) {
                                we.load(txt.getText().startsWith("http://")
                                        ? txt.getText() : txt.getText());
                            } else { //https://searches
                                if ("https://".equals(http.substring(0, 7))) {
                                    we.load(txt.getText().startsWith("https://")
                                            ? txt.getText() : txt.getText());
                                } else {
                                    we.load(txt.getText().startsWith("http://")
                                            ? txt.getText() : "https://duckduckgo.com/?q="
                                            + search.get(0));
                                }}}}}
            } else {
                we.load(txt.getText().startsWith("http://") ? txt.getText()
                        : "https://duckduckgo.com/?q=" + search.get(0));}};        
        txt.setOnAction(enter);
        bt.setOnAction(enter);
        //listener for search button
        we.locationProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
        we.setJavaScriptEnabled(false);
            txt.setText(newValue);
            // System.out.println(oldValue);
            System.out.println(newValue);
        });
        
        //EventHandler for refresh button
        EventHandler<ActionEvent> reload = (ActionEvent event) -> {
        we.setJavaScriptEnabled(false);
         if(!search.isEmpty())
           we.reload();
           
        };
         refresh.setOnAction(reload);         
        //closes application 
         closeBtn.setOnAction((ActionEvent actionEvent) -> {
             Platform.exit();
        });
        
         
    }
    
    @FXML
    private void handle(ActionEvent event) {
        
    }
    
    public ArrayList getSearch() {
        return search;
    }

    public void setSearch(ArrayList search) {
        this.search = search;
    }

}
