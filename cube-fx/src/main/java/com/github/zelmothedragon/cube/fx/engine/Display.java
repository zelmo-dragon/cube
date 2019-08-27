package com.github.zelmothedragon.cube.fx.engine;

import javafx.application.Application;
import javafx.scene.CacheHint;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * Affichage principale. Configure également tout le moteur de l'application.
 *
 * @author MOSELLE Maxime
 */
public class Display extends Application {

    /**
     * Coéfficient de mise à l'échelle.
     */
    public static final int SCALE = 3;

    /**
     * Largeur initiale.
     */
    public static final int WIDTH = 320;

    /**
     * Hauteur initiale. Calculée avec un rapport 16/9.
     */
    public static final int HEIGHT = WIDTH / 16 * 9;

    /**
     * Constructeur par défaut.
     */
    public Display() {
        // RAS
    }

    @Override
    public void start(final Stage primaryStage) throws Exception {

        var canvas = new Canvas(SCALE * WIDTH, SCALE * HEIGHT);
        canvas.setCache(true);
        canvas.setCacheHint(CacheHint.SPEED);

        var group = new Group();
        group.getChildren().add(canvas);

        var scene = new Scene(group, Color.BLACK);

        primaryStage.setTitle("Cube");
        primaryStage.setScene(scene);
        primaryStage.sizeToScene();
        primaryStage.setResizable(true);
        primaryStage.show();
    }

}
