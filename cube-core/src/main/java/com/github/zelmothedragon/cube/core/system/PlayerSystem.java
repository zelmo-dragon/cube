package com.github.zelmothedragon.cube.core.system;

import com.github.zelmothedragon.cube.core.GameManager;
import com.github.zelmothedragon.cube.core.graphic.Renderer;
import com.github.zelmothedragon.cube.core.input.GamePad;
import com.github.zelmothedragon.cube.core.model.AnimatedImage;
import com.github.zelmothedragon.cube.core.model.BoundedBox;
import com.github.zelmothedragon.cube.core.model.Controllable;
import com.github.zelmothedragon.cube.core.model.Entity;
import com.github.zelmothedragon.cube.core.model.Orientation;
import com.github.zelmothedragon.cube.core.model.Vector;

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

        if (player.hasComponent(Controllable.class)) {
            var image = player.getComponent(AnimatedImage.class);
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

            var box = player.getComponent(BoundedBox.class);
            box.move(vector.getDx(), vector.getDy());
        }
    }

    @Override
    public void draw(final Renderer<?> renderer) {

    }

}
