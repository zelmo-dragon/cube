package com.github.zelmothedragon.cube.core.system;

import com.github.zelmothedragon.cube.core.GameContainer;
import com.github.zelmothedragon.cube.core.entity.behavior.Controllable;
import com.github.zelmothedragon.cube.core.entity.geometry.Vector;
import com.github.zelmothedragon.cube.core.graphic.Render;
import com.github.zelmothedragon.cube.core.input.GamePad;
import java.util.UUID;

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
                .forEach(this::doInputDirectionnal);

        gc
                .getEntities()
                .get(Controllable.class)
                .forEach(this::doInputAction);
    }

    @Override
    public void draw(final Render g2d) {
        // RAS
    }

    private void doInputDirectionnal(
            final UUID id,
            final Controllable controllable) {

        var vector = gc
                .getEntities()
                .get(id, Vector.class);

        if (gc.getInputs().isKeyPressed(GamePad.LEFT)) {
            vector.set(-1, 0);
        } else if (gc.getInputs().isKeyPressed(GamePad.RIGHT)) {
            vector.set(1, 0);
        } else if (gc.getInputs().isKeyPressed(GamePad.UP)) {
            vector.set(0, -1);
        } else if (gc.getInputs().isKeyPressed(GamePad.DOWN)) {
            vector.set(0, 1);
        } else {
            vector.reset();
        }
    }

    private void doInputAction(
            final UUID id,
            final Controllable controllable) {

        if (gc.getInputs().isKeyPressed(GamePad.ACTION)) {

        }
        if (gc.getInputs().isKeyPressed(GamePad.OPTION)) {

        }
        if (gc.getInputs().isKeyPressed(GamePad.BACK)) {

        }
        if (gc.getInputs().isKeyPressed(GamePad.BACK)) {

        }
        if (gc.getInputs().isKeyPressed(GamePad.START)) {

        }
        if (gc.getInputs().isKeyPressed(GamePad.SELECT)) {

        }
        if (gc.getInputs().isKeyPressed(GamePad.DEBUG)) {

        }
    }

}
