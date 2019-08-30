package com.github.zelmothedragon.cube.fx.engine;

import com.github.zelmothedragon.cube.core.GameContainer;
import com.github.zelmothedragon.cube.fx.graphic.RenderFx;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;

/**
 * Coeur du moteur du jeu. Cadence la boucle principale du jeu.
 *
 * @author MOSELLE Maxime
 */
public final class Engine extends AnimationTimer {

    /**
     * Fréquence de pulsation de la boucle principale du jeu.
     */
    private static final double PULSE = 1e9 / 60.0;

    /**
     * Vérrouiller la mise à jour de l'affichage. Pour déboggage uniquement,
     * dans la pratique laisser la valeur <code>true</code>.
     */
    // TODO: Rendre dynamique
    private static final boolean LOCK_FPS = false;

    /**
     * Gestionnaire de rendu graphique.
     */
    private final RenderFx render;

    /**
     * Conteneur du contexte du jeu.
     */
    private final GameContainer gc;

    /**
     * Valeur d'interpolation. Utilisé pour ajuster les calculs en cas de
     * ralentissement du programme, comme par exemple les calculs de trajectoire
     * ou de positionnement. Généralement sa valeur est environ égale à 1.
     */
    private double deltaTime;

    /**
     * Temps en nanoseconde de la dernière exécution d'un tour de la boucle du
     * jeu.
     */
    private long lastTime;

    /**
     * Constructeur.Construit le moteur du jeu pour l'ordonnancement de tous les
     * traitements.
     *
     * @param g2d Contexte graphique 2D de JavaFX
     * @param gc Conteneur du contexte du jeu
     */
    public Engine(final GraphicsContext g2d, final GameContainer gc) {
        this.render = new RenderFx(Display.WIDTH, Display.HEIGHT, g2d);
        this.gc = gc;
        this.deltaTime = 0.0;
        this.lastTime = System.nanoTime();
    }

    @Override
    public void handle(final long now) {
        deltaTime += (now - lastTime) / PULSE;
        lastTime = now;
        while (deltaTime >= 1) {
            // Appelée x60 par seconde
            update();
            deltaTime--;
        }

        // Appelée autant de fois que possible 
        // après le traitement de la méthode "update"
        draw();

        if (LOCK_FPS) {
            // OU
            // Appelée x60 par seconde

            var sleepTime = (long) ((now - System.nanoTime() + PULSE) / 1e6);
            if (sleepTime < 0) {
                sleepTime = 1;
            }
            try {
                // Mettre en sommeil le processus pendant le temps superflu
                Thread.sleep(sleepTime);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * Mettre à jour la logique métier du jeu.
     */
    private void update() {
        gc.getInputs().update();
        gc.getSystems().update();
    }

    /**
     * Mettre à jour le rendu graphique du jeu.
     */
    private void draw() {
        render.clear();
        gc.getSystems().draw(render);
        render.draw();
    }

}
