package com.github.zelmothedragon.cube.core.system;

import com.github.zelmothedragon.cube.core.GameContainer;
import com.github.zelmothedragon.cube.core.graphic.Render;
import com.github.zelmothedragon.cube.core.input.GamePad;

/**
 * Système de gestion des entrées.
 *
 * @author MOSELLE Maxime
 */
public final class InputSystem extends AbstractSystem {

    /**
     * Constructeur. Construit un système de gestion des entrées.
     *
     * @param gc Conteneur du jeu
     * @param priority Priorité d'exécution
     */
    public InputSystem(final GameContainer gc, final int priority) {
        super(gc, priority);
    }

    @Override
    public void update() {

        if (gc.getInputs().isKeyUp(GamePad.ACTION)) {

        }
        if (gc.getInputs().isKeyUp(GamePad.OPTION)) {

        }
        if (gc.getInputs().isKeyUp(GamePad.BACK)) {

        }
        if (gc.getInputs().isKeyUp(GamePad.BACK)) {

        }
        if (gc.getInputs().isKeyUp(GamePad.START)) {

        }
        if (gc.getInputs().isKeyUp(GamePad.SELECT)) {

        }
        if (gc.getInputs().isKeyUp(GamePad.DEBUG)) {

        }
        if (gc.getInputs().isKeyUp(GamePad.LEFT)) {

        }
        if (gc.getInputs().isKeyUp(GamePad.RIGHT)) {

        }
        if (gc.getInputs().isKeyUp(GamePad.UP)) {

        }
        if (gc.getInputs().isKeyUp(GamePad.DOWN)) {

        }
    }

    @Override
    public void draw(final Render g2d) {
        // RAS
    }

}
