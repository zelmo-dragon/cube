package com.github.zelmothedragon.cube.core.system;

import com.github.zelmothedragon.cube.core.GameContainer;
import com.github.zelmothedragon.cube.core.entity.debug.Clock;
import com.github.zelmothedragon.cube.core.entity.geometry.Rectangle;
import com.github.zelmothedragon.cube.core.graphic.AnimatedSprite;
import com.github.zelmothedragon.cube.core.graphic.FontSprite;
import com.github.zelmothedragon.cube.core.graphic.Render;
import com.github.zelmothedragon.cube.core.graphic.Sprite;
import java.util.UUID;

/**
 * Système de déboggage.
 *
 * @author MOSELLE Maxime
 */
public final class DebugSystem extends AbstractSystem {

    /**
     * Couleur des zones de collision. Rouge par défaut
     */
    private static final int HIT_BOX_COLOR = 0xFFFF0000;

    /**
     * Couleur des zones des textures. Verte par défaut.
     */
    private static final int SPRITE_BOX_COLOR = 0xFF00FF00;

    /**
     * Couleur des zones d'événement. Bleue par défaut.
     */
    private static final int EVENT_BOX_COLOR = 0xFF0000FF;

    /**
     * Entité de déboggage.
     */
    private final UUID debug;

    public DebugSystem(final GameContainer gc, final int priority) {
        super(gc, priority);
        this.debug = gc.getFactory().createDebugInformation();
    }

    @Override
    public void update() {
        var clock = gc.getEntities().get(debug, Clock.class);
        clock.update();
    }

    @Override
    public void draw(final Render g2d) {

        gc
                .getEntities()
                .get(AnimatedSprite.class)
                .forEach((k, v) -> doRenderSpriteBox(k, v, g2d));

        gc
                .getEntities()
                .get(Sprite.class)
                .forEach((k, v) -> doRenderSpriteBox(k, v, g2d));

        gc
                .getEntities()
                .get(Rectangle.class)
                .forEach((k, v) -> doRenderRectangleBox(k, v, g2d));

        doRenderDebugInformation(g2d);
    }

    private void doRenderRectangleBox(
            final UUID id,
            final Rectangle rectangle,
            final Render g2d) {

        g2d.drawRect(
                rectangle.getXp(),
                rectangle.getYp(),
                rectangle.getWidth(),
                rectangle.getHeight(),
                HIT_BOX_COLOR
        );
    }

    private void doRenderDebugInformation(final Render g2d) {
        var rect = gc.getEntities().get(debug, Rectangle.class);
        var font = gc.getEntities().get(debug, FontSprite.class);
        var clock = gc.getEntities().get(debug, Clock.class);
        clock.render();

        g2d.drawImage(
                rect.getXp(),
                rect.getYp(),
                font,
                clock.getMessage()
        );
    }

    private void doRenderSpriteBox(
            final UUID id,
            final Sprite sprite,
            final Render g2d) {

        var rectangle = gc.getEntities().get(id, Rectangle.class);

        g2d.drawRect(
                rectangle.getXp(),
                rectangle.getYp(),
                sprite.getWidth(),
                sprite.getHeight(),
                SPRITE_BOX_COLOR
        );
    }

    private void doRenderSpriteBox(
            final UUID id,
            final AnimatedSprite sprite,
            final Render g2d) {

        var rectangle = gc.getEntities().get(id, Rectangle.class);

        g2d.drawRect(
                rectangle.getXp(),
                rectangle.getYp(),
                sprite.getCurrentSprite().getWidth(),
                sprite.getCurrentSprite().getHeight(),
                SPRITE_BOX_COLOR
        );
    }

}
