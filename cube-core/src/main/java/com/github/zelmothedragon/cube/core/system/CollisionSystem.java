package com.github.zelmothedragon.cube.core.system;

import com.github.zelmothedragon.cube.core.GameManager;
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
    }

    @Override
    public void draw(Renderer<?> renderer) {
    }

}
