package com.github.zelmothedragon.cube.core.system;

import com.github.zelmothedragon.cube.core.GameContainer;
import com.github.zelmothedragon.cube.core.entity.geometry.Rectangle;
import com.github.zelmothedragon.cube.core.entity.geometry.Vector;
import com.github.zelmothedragon.cube.core.graphic.Render;
import java.util.UUID;

/**
 * Système de mouvement des entités.
 *
 * @author MOSELLE Maxime
 */
public final class MoveSystem extends AbstractSystem {

    /**
     * Constructeur. Construit un système de mouvement.
     *
     * @param gc Conteneur du jeu
     * @param priority Priorité d'exécution
     */
    public MoveSystem(final GameContainer gc, final int priority) {
        super(gc, priority);
    }

    @Override
    public void update() {
        gc
                .getEntities()
                .get(Vector.class)
                .forEach(this::doMove);

    }

    @Override
    public void draw(final Render g2d) {
        // RAS
    }

    private void doMove(
            final UUID id,
            final Vector vector) {

        var rectangle = gc
                .getEntities()
                .get(id, Rectangle.class);
        
        rectangle.move(vector);
    }

}
