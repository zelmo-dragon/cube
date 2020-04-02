package com.github.zelmothedragon.cube.core.system;

import com.github.zelmothedragon.cube.core.GameManager;
import com.github.zelmothedragon.cube.core.entity.Entity;
import com.github.zelmothedragon.cube.core.entity.debug.Clock;
import com.github.zelmothedragon.cube.core.entity.geometry.Point;
import com.github.zelmothedragon.cube.core.entity.geometry.Rectangle;
import com.github.zelmothedragon.cube.core.graphic.AnimatedSprite;
import com.github.zelmothedragon.cube.core.graphic.FontSprite;
import com.github.zelmothedragon.cube.core.graphic.Pixel;
import com.github.zelmothedragon.cube.core.graphic.Renderer;

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

    /**
     * Constructeur. Constuire un système, une seule instance est nécessaire
     * pour le fonctionnemenr global de l'application. Le système doit être
     * instancier dans le gestionnaire de système.
     *
     * @param manager Gestionnaire du jeu
     * @param priority Priorié d'exécuter du système
     */
    DebugSystem(final GameManager manager, final int priority) {
        super(manager, priority);
        this.debug = manager.getFactory().createDebugInformation();
    }

    @Override
    public void update() {
        var clock = debug.getComponent(Clock.class);
        clock.update();
    }

    @Override
    public void draw(final Renderer g2d) {

        manager
                .getEntities()
                .filter(AnimatedSprite.class)
                .stream()
                .map(e -> e.getComponent(AnimatedSprite.class))
                .map(DebugSystem::toRectangle)
                .forEach(r -> g2d.drawRect(r, Pixel.GREEN));
        
        manager
                .getEntities()
                .filter(Rectangle.class)
                .stream()
                .map(e -> e.getComponent(Rectangle.class))
                .forEach(r -> g2d.drawRect(r, Pixel.RED));

        var clock = debug.getComponent(Clock.class);
        var point = debug.getComponent(Point.class);
        var font = debug.getComponent(FontSprite.class);

        g2d.resetOffset();
        g2d.drawImage(point, font, clock.getMessage());
        clock.render();
    }

    private static Rectangle toRectangle(AnimatedSprite sprite) {

        return new Rectangle(
                sprite.getRectangle().getPoint(),
                sprite.getCurrentSprite().getRectangle().getDimension()
        );
    }

}
