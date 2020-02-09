package com.github.zelmothedragon.cube.core.system;

import com.github.zelmothedragon.cube.core.GameContainer;
import com.github.zelmothedragon.cube.core.entity.Entity;
import com.github.zelmothedragon.cube.core.entity.debug.Clock;
import com.github.zelmothedragon.cube.core.entity.geometry.Point;
import com.github.zelmothedragon.cube.core.graphic.FontSprite;
import com.github.zelmothedragon.cube.core.graphic.Render;

/**
 * Système de déboggage.
 *
 * @author MOSELLE Maxime
 */
public final class DebugSystem extends AbstractSystem {

    /**
     * Entité de déboggage.
     */
    private final Entity debug;

    public DebugSystem(final GameContainer gc, final int priority) {
        super(gc, priority);
        this.debug = gc.getFactory().createDebugInformation();
    }

    @Override
    public void update() {
        var clock = debug.getComponent(Clock.class);
        clock.update();
    }

    @Override
    public void draw(final Render g2d) {
        var clock = debug.getComponent(Clock.class);
        var point = debug.getComponent(Point.class);
        var font = debug.getComponent(FontSprite.class);

        g2d.drawImage(point, font, clock.getMessage());
        clock.render();
    }

}
