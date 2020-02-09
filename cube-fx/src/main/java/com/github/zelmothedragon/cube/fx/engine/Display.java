package com.github.zelmothedragon.cube.fx.engine;

import com.github.zelmothedragon.cube.core.GameManager;
import com.github.zelmothedragon.cube.core.input.GamePad;
import com.github.zelmothedragon.cube.core.input.InputManager;
import com.github.zelmothedragon.cube.fx.asset.ResourceManager;
import java.util.Objects;
import javafx.application.Application;
import javafx.scene.CacheHint;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * Affichage principale. Configure également tout le moteur de l'application.
 *
 * @author MOSELLE Maxime
 */
public final class Display extends Application {

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
        canvas.setCache(false);
        canvas.setCacheHint(CacheHint.SPEED);

        var g2d = canvas.getGraphicsContext2D();
        g2d.setImageSmoothing(false);

        var group = new Group();
        group.getChildren().add(canvas);

        var resourceManager = new ResourceManager();
        var manager = new GameManager(resourceManager);
        loadKeys(manager.getInputs());

        var engine = new Engine(g2d, manager);
        engine.start();

        var scene = new Scene(group, Color.BLACK);
        scene.setCursor(Cursor.NONE);

        scene.setOnKeyPressed(e -> {
            toggleScreen(primaryStage, e);
            manager.getInputs().keyPressed(e.getCode().getCode());
            e.consume();
        });

        scene.setOnKeyReleased(e -> {
            manager.getInputs().keyReleased(e.getCode().getCode());
            e.consume();
        });

        scene.setOnMouseMoved(e -> {
            // TODO: calculer la position exacte du curseur.
            var x = (e.getSceneX());
            var y = (e.getSceneY());
            manager.getInputs().cursorMoved(x, y);
            // System.out.printf("X:%s Y=%s%n", x, y);
        });

        primaryStage.widthProperty().addListener((e, o, n) -> {
            canvas.setWidth(n.doubleValue());
        });

        primaryStage.heightProperty().addListener((e, o, n) -> {
            canvas.setHeight(n.doubleValue());
        });

        primaryStage.setTitle("Cube");
        primaryStage.setScene(scene);
        primaryStage.sizeToScene();
        primaryStage.setResizable(true);
        primaryStage.show();
    }

    /**
     * Basculer l'affichage en plein écran ou en mode fenêtré. Par défaut le
     * basculement se fait avec la touche <code>F11</code>.
     *
     * @param stage Stage courant
     * @param event Événement du clavier
     */
    private static void toggleScreen(final Stage stage, final KeyEvent event) {
        if (Objects.equals(event.getCode(), KeyCode.F11)) {
            if (stage.isFullScreen()) {
                stage.setFullScreen(false);
            } else {
                stage.setFullScreen(true);
            }
        }
    }

    /**
     * Charger la configuration par défaut pour le gestionnaire des entrées.
     *
     * @param manager Gestionnaire des entrées
     */
    private static void loadKeys(final InputManager manager) {
        manager.assign(GamePad.UP, KeyCode.UP.getCode());
        manager.assign(GamePad.DOWN, KeyCode.DOWN.getCode());
        manager.assign(GamePad.LEFT, KeyCode.LEFT.getCode());
        manager.assign(GamePad.RIGHT, KeyCode.RIGHT.getCode());
        manager.assign(GamePad.ACTION, KeyCode.A.getCode());
        manager.assign(GamePad.OPTION, KeyCode.Q.getCode());
        manager.assign(GamePad.BACK, KeyCode.Z.getCode());
        manager.assign(GamePad.START, KeyCode.ENTER.getCode());
        manager.assign(GamePad.SELECT, KeyCode.BACK_SPACE.getCode());
    }

}
