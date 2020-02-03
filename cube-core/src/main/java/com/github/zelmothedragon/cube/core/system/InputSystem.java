package com.github.zelmothedragon.cube.core.system;

import com.github.zelmothedragon.cube.core.GameContainer;
import com.github.zelmothedragon.cube.core.entity.behavior.Controllable;
import com.github.zelmothedragon.cube.core.entity.geometry.Vector;
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
        gc
                .getEntities()
                .get(Controllable.class)
                .forEach((k, v) -> {
                    var vector = gc
                            .getEntities()
                            .get(k, Vector.class);

                    if (gc.getInputs().isKeyUp(GamePad.LEFT)) {
                        vector.set(-1, 0);
                    } else if (gc.getInputs().isKeyUp(GamePad.RIGHT)) {
                        vector.set(1, 0);
                    } else if (gc.getInputs().isKeyUp(GamePad.UP)) {
                        vector.set(0, -1);
                    } else if (gc.getInputs().isKeyUp(GamePad.DOWN)) {
                        vector.set(0, 1);
                    }else {
                        vector.reset();
                    }
                    
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
                });
    }

    @Override
    public void draw(final Render g2d) {
        // RAS
    }

}
