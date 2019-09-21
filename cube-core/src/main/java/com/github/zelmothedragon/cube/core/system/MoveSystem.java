package com.github.zelmothedragon.cube.core.system;

import com.github.zelmothedragon.cube.core.GameContainer;
import com.github.zelmothedragon.cube.core.entity.geometry.Point;
import com.github.zelmothedragon.cube.core.entity.geometry.Rectangle;
import com.github.zelmothedragon.cube.core.entity.geometry.Vector;
import com.github.zelmothedragon.cube.core.graphic.Render;

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
                .getComponent(Vector.class)
                .forEach((k, v) -> {

                    gc
                            .getEntities()
                            .getComponent(k, Point.class)
                            .move(v);

                    gc
                            .getEntities()
                            .getComponent(k, Rectangle.class)
                            .move(v);

                    v.reset();
                });
    }

    @Override
    public void draw(final Render g2d) {
        // RAS
    }

}
