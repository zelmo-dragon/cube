package com.github.zelmothedragon.cube.core.system;

import com.github.zelmothedragon.cube.core.GameManager;
import com.github.zelmothedragon.cube.core.entity.Entity;
import com.github.zelmothedragon.cube.core.entity.Family;
import com.github.zelmothedragon.cube.core.entity.debug.Clock;
import com.github.zelmothedragon.cube.core.entity.geometry.Point;
import com.github.zelmothedragon.cube.core.entity.geometry.Rectangle;
import com.github.zelmothedragon.cube.core.entity.image.FontImage;
import com.github.zelmothedragon.cube.core.graphic.Renderer;
import com.github.zelmothedragon.cube.pixel.graphic.Pixels;

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
    public void draw(final Renderer<?> renderer) {

        var entities = manager
                .getEntities()
                .get(Family.PLAYER);

        if (!entities.isEmpty()) {
            var play = entities.stream().findFirst().get();
            var r = play.getComponent(Rectangle.class);

            System.out.println("RECT: " + r);
            
            renderer.drawRectangle(
                    r.getXp(),
                    r.getYp(),
                    r.getWidth(),
                    r.getHeight(),
                    Pixels.COLOR_GREEN
            );
        }

        renderer.drawRectangle(0, 0, 50, 50, Pixels.COLOR_RED);
        renderer.drawFillRectangle(50, 0, 50, 50, Pixels.COLOR_RED);
        renderer.drawCircle(100, 0, 25, Pixels.COLOR_RED);
        renderer.drawFillCircle(150, 0, 25, Pixels.COLOR_RED);
        renderer.drawGradientCircle(200, 0, 25, Pixels.COLOR_RED);
        renderer.drawLine(250, 0, 300, 50, Pixels.COLOR_RED);

        var clock = debug.getComponent(Clock.class);
        var point = debug.getComponent(Point.class);
        var image = debug.getComponent(FontImage.class);

        renderer.resetOffset();
        renderer.drawImage(point.getXp(), point.getYp(), image, clock.getMessage());
        clock.render();
    }

}
