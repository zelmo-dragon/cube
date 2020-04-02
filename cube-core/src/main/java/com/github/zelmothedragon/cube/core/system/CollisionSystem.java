package com.github.zelmothedragon.cube.core.system;

import com.github.zelmothedragon.cube.core.GameManager;
import com.github.zelmothedragon.cube.core.entity.geometry.Rectangle;
import com.github.zelmothedragon.cube.core.graphic.Renderer;

/**
 * Système de collision.
 *
 * @author MOSELLE Maxime
 */
public final class CollisionSystem extends AbstractSystem {

    public CollisionSystem(final GameManager manager, final int priority) {
        super(manager, priority);
    }

    @Override
    public void update() {

        var rectengles = manager
                .getEntities()
                .filter(Rectangle.class);

    }

    @Override
    public void draw(Renderer g2d) {
    }

}
