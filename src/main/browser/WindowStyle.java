/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.browser;


import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 *
 * @author Brandon
 */
public class WindowStyle extends NewBrowser{
    private static final javafx.geometry.Rectangle2D SCREEN_BOUNDS= Screen.getPrimary()
            .getVisualBounds();
    private static double[] pref_WH, offset_XY;
    private static String styleSheet;
    public static boolean full = false;

    private WindowStyle(String css) {
        styleSheet= getClass().getResource(css).toString();
    }

    //Sets the default stage prefered width and height.
    protected static void stageDimension(Double width, Double height) {
        pref_WH= new double[]{width, height};
    }

    protected static void fullScreen(Stage stage) {
        stage.setX(SCREEN_BOUNDS.getMinX());
        stage.setY(SCREEN_BOUNDS.getMinY());
        stage.setWidth(SCREEN_BOUNDS.getWidth());
        stage.setHeight(SCREEN_BOUNDS.getHeight());
    }

    protected static void restoreScreen(Stage stage) {
        stage.setX((SCREEN_BOUNDS.getMaxX() - pref_WH[0])/2);
        stage.setY((SCREEN_BOUNDS.getMaxY() - pref_WH[1])/2);
        stage.setWidth(pref_WH[0]);
        stage.setHeight(pref_WH[1]);
    }
}