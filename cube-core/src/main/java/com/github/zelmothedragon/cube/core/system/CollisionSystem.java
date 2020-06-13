package com.github.zelmothedragon.cube.core.system;

import com.github.zelmothedragon.cube.core.GameManager;
import com.github.zelmothedragon.cube.core.model.Block;
import com.github.zelmothedragon.cube.core.model.BoundedBox;
import com.github.zelmothedragon.cube.core.model.Movable;
import com.github.zelmothedragon.cube.core.model.Entity;
import com.github.zelmothedragon.cube.core.graphic.Renderer;
import com.github.zelmothedragon.cube.core.util.geometry.Rectangle;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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
        var solidBlocks = manager
                .getEntities()
                .filter(BoundedBox.class)
                .stream()
                .map(e -> e.getComponent(BoundedBox.class))
                .filter(e -> Objects.equals(e.getBlock(), Block.SOLID))
                .collect(Collectors.toList());

        manager
                .getEntities()
                .filter(Movable.class)
                .forEach(e -> checkCollision(solidBlocks, e));
    }

    @Override
    public void draw(final Renderer<?> renderer) {
    }

    private static void checkCollision(final List<BoundedBox> solidBlocks, final Entity entity) {

        var box = entity.getComponent(BoundedBox.class);
        var aabb = box.getCollision();

        solidBlocks
                .stream()
                .filter(b -> !Objects.equals(b, box))
                .map(b -> b.getCollision())
                .filter(b -> b.intersects(aabb))
                .map(b -> b.createIntersection(aabb))
                .forEach(r -> adjustOffset(box, r));
    }

    private static void adjustOffset(final BoundedBox box, final Rectangle offset) {
        var xOffset = offset.getWidth() * -box.getVector().getDx();
        var yOffset = offset.getHeight() * -box.getVector().getDy();

        box.getVector().set(xOffset, yOffset);
        box.move();
    }

}
