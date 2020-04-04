package com.github.zelmothedragon.cube.core.system;

import com.github.zelmothedragon.cube.core.GameManager;
import com.github.zelmothedragon.cube.core.component.Block;
import com.github.zelmothedragon.cube.core.component.BoundedBox;
import com.github.zelmothedragon.cube.core.component.Clock;
import com.github.zelmothedragon.cube.core.component.FontImage;
import com.github.zelmothedragon.cube.core.component.Movable;
import com.github.zelmothedragon.cube.core.entity.Entity;
import com.github.zelmothedragon.cube.core.graphic.Renderer;
import com.github.zelmothedragon.cube.pixel.graphic.Pixels;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Système de déboggage.
 *
 * @author MOSELLE Maxime
 */
public final class DebugSystem extends AbstractSystem {

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
        // Calculer la fréquence de la boucle de mise à jour
        var clock = debug.getComponent(Clock.class);
        clock.update();

    }

    @Override
    public void draw(final Renderer<?> renderer) {
        var entities = manager.getEntities().filter(BoundedBox.class);
        drawBox(renderer, entities);

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
                .forEach(e -> drawCollision(renderer, solidBlocks, e));

        renderer.resetOffset();
        //drawGrid(renderer);
        drawClock(debug, renderer);
    }

    private static void drawGrid(final Renderer<?> renderer) {
        var w = renderer.getWidth();
        var h = renderer.getHeight();
        var s = 16;

        for (var x = 0; x < h; x += s) {
            renderer.drawLine(0, x, w, x, Pixels.COLOR_BLUE);
        }
        for (var y = 0; y < w; y += s) {
            renderer.drawLine(y, 0, y, h, Pixels.COLOR_BLUE);
        }
    }

    private static void drawBox(final Renderer<?> renderer, final Set<Entity> entities) {

        entities
                .stream()
                .map(e -> e.getComponent(BoundedBox.class))
                .filter(b -> (Objects.equals(b.getBlock(), Block.SOLID)))
                .forEach(b -> drawBox(renderer, b));
    }

    private static void drawBox(final Renderer<?> renderer, final BoundedBox box) {
        renderer.drawRectangle(
                box.getBound().getXp(),
                box.getBound().getYp(),
                box.getBound().getWidth(),
                box.getBound().getHeight(),
                Pixels.COLOR_GREEN
        );
        renderer.drawRectangle(
                box.getCollision().getXp(),
                box.getCollision().getYp(),
                box.getCollision().getWidth(),
                box.getCollision().getHeight(),
                Pixels.COLOR_RED
        );
    }

    private static void drawCollision(final Renderer<?> renderer, final List<BoundedBox> solidBlocks, final Entity entity) {

        var box = entity.getComponent(BoundedBox.class);

        solidBlocks
                .stream()
                .filter(b -> !Objects.equals(b, box))
                .filter(b -> b.getBound().intersects(box.getBound()))
                .map(b -> b.getBound().createIntersection(box.getBound()))
                .forEach(b -> renderer.drawFillRectangle(b.getXp(), b.getYp(), b.getWidth(), b.getHeight(), Pixels.COLOR_GREEN));
        solidBlocks
                .stream()
                .filter(b -> !Objects.equals(b, box))
                .filter(b -> b.getCollision().intersects(box.getCollision()))
                .map(b -> b.getCollision().createIntersection(box.getCollision()))
                .forEach(b -> renderer.drawFillRectangle(b.getXp(), b.getYp(), b.getWidth(), b.getHeight(), Pixels.COLOR_RED));
    }

    private static void drawClock(final Entity debug, final Renderer<?> renderer) {
        var clock = debug.getComponent(Clock.class);
        var box = debug.getComponent(BoundedBox.class);
        var font = debug.getComponent(FontImage.class);
        renderer.drawImage(
                box.getBound().getXp(),
                box.getBound().getYp(),
                font,
                clock.getMessage());

        // Calculer la fréquence du rendu.
        clock.render();
    }

}
