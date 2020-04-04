package com.github.zelmothedragon.cube.core.system;

import com.github.zelmothedragon.cube.core.GameManager;
import com.github.zelmothedragon.cube.core.entity.Entity;
import com.github.zelmothedragon.cube.core.entity.behavior.Controllable;
import com.github.zelmothedragon.cube.core.entity.geometry.Orientation;
import com.github.zelmothedragon.cube.core.entity.geometry.Rectangle;
import com.github.zelmothedragon.cube.core.entity.geometry.Vector;
import com.github.zelmothedragon.cube.core.entity.image.AnimatedImage;
import com.github.zelmothedragon.cube.core.graphic.Renderer;
import com.github.zelmothedragon.cube.core.input.GamePad;

/**
 * Système de gestion du personnage principal.
 *
 * @author MOSELLE Maxime
 */
public final class PlayerSystem extends AbstractSystem {

    /**
     * Entité du joueur par défaut.
     */
    private final Entity player;

    /**
     * Constructeur. Constuire un système, une seule instance est nécessaire
     * pour le fonctionnemenr global de l'application. Le système doit être
     * instancier dans le gestionnaire de système.
     *
     * @param manager Gestionnaire du jeu
     * @param priority Priorié d'exécuter du système
     */
    PlayerSystem(final GameManager manager, final int priority) {
        super(manager, priority);

        this.player = manager.getFactory().createDebugPlayer();
    }

    @Override
    public void update() {

        var image = player.getComponent(AnimatedImage.class);
        if (player.hasComponent(Controllable.class)) {
            var vector = player.getComponent(Vector.class);
            if (manager.getInputs().isKeyPressed(GamePad.LEFT)) {
                image.setOrientation(Orientation.LEFT);
                vector.set(-1, 0);
            } else if (manager.getInputs().isKeyPressed(GamePad.RIGHT)) {
                image.setOrientation(Orientation.RIGHT);
                vector.set(1, 0);
            } else if (manager.getInputs().isKeyPressed(GamePad.UP)) {
                image.setOrientation(Orientation.UP);
                vector.set(0, -1);
            } else if (manager.getInputs().isKeyPressed(GamePad.DOWN)) {
                image.setOrientation(Orientation.DOWN);
                vector.set(0, 1);
            } else {
                image.setOrientation(Orientation.EMPTY);
                vector.reset();
            }

            var rectangle = player.getComponent(Rectangle.class);
            rectangle.move(vector);
        }
        image.update();
    }

    @Override
    public void draw(final Renderer<?> renderer) {

        var rectangle = player.getComponent(Rectangle.class);
        var image = player.getComponent(AnimatedImage.class);
        renderer.drawImage(
                rectangle.getXp(),
                rectangle.getYp(),
                image
        );
    }

}
