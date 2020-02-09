package com.github.zelmothedragon.cube.core.system;

import com.github.zelmothedragon.cube.core.GameContainer;
import com.github.zelmothedragon.cube.core.entity.Entity;
import com.github.zelmothedragon.cube.core.entity.behavior.Controllable;
import com.github.zelmothedragon.cube.core.entity.geometry.Orientation;
import com.github.zelmothedragon.cube.core.entity.geometry.Rectangle;
import com.github.zelmothedragon.cube.core.entity.geometry.Vector;
import com.github.zelmothedragon.cube.core.graphic.AnimatedSprite;
import com.github.zelmothedragon.cube.core.graphic.AnimatedSpriteMetaData;
import com.github.zelmothedragon.cube.core.graphic.Render;
import com.github.zelmothedragon.cube.core.input.GamePad;
import java.util.Objects;

/**
 * Système de gestion du personnage principal.
 *
 * @author MOSELLE Maxime
 */
public final class PlayerSystem extends AbstractSystem {

    private final Entity player;

    public PlayerSystem(final GameContainer gc, final int priority) {
        super(gc, priority);

        this.player = gc.getFactory().createDebugPlayer();
    }

    @Override
    public void update() {

        var metaData = player.getComponent(AnimatedSpriteMetaData.class);
        if (player.hasComponent(Controllable.class)) {
            var vector = player.getComponent(Vector.class);
            if (gc.getInputs().isKeyPressed(GamePad.LEFT)) {
                metaData.setOrientation(Orientation.LEFT);
                vector.set(-1, 0);
            } else if (gc.getInputs().isKeyPressed(GamePad.RIGHT)) {
                metaData.setOrientation(Orientation.RIGHT);
                vector.set(1, 0);
            } else if (gc.getInputs().isKeyPressed(GamePad.UP)) {
                metaData.setOrientation(Orientation.UP);
                vector.set(0, -1);
            } else if (gc.getInputs().isKeyPressed(GamePad.DOWN)) {
                metaData.setOrientation(Orientation.DOWN);
                vector.set(0, 1);
            } else {
                metaData.setOrientation(Orientation.EMPTY);
                vector.reset();
            }

            var rectangle = player.getComponent(Rectangle.class);
            rectangle.move(vector);
        }

        var orientation = metaData.getOrientation();
        var sprite = player.getComponent(AnimatedSprite.class);
        if (Objects.equals(orientation, Orientation.EMPTY)) {
            sprite.stop();
        } else {
            var offset = metaData.getCurrentOffset();
            sprite.setOffset(offset.getXp(), offset.getYp());
            sprite.play();
        }
        sprite.update();
    }

    @Override
    public void draw(final Render g2d) {

        var point = player.getComponent(Rectangle.class).getPoint();
        var sprite = player.getComponent(AnimatedSprite.class).getCurrentSprite();
        g2d.drawImage(point, sprite);

    }

}
