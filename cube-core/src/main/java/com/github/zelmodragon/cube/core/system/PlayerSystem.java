package com.github.zelmodragon.cube.core.system;

import com.github.zelmodragon.cube.core.graphic.Renderer;
import com.github.zelmodragon.cube.core.GameManager;
import com.github.zelmodragon.cube.core.input.GamePad;
import com.github.zelmodragon.cube.core.model.AnimatedImage;
import com.github.zelmodragon.cube.core.model.BoundedBox;
import com.github.zelmodragon.cube.core.model.Controllable;
import com.github.zelmodragon.cube.core.model.Entity;
import com.github.zelmodragon.cube.core.model.Orientation;

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
     * Constructeur. Construire un système, une seule instance est nécessaire
     * pour le fonctionnement global de l'application. Le système doit être
     * instancié dans le gestionnaire de système.
     *
     * @param manager Gestionnaire du jeu
     * @param priority Priorité d'exécuter du système
     */
    PlayerSystem(final GameManager manager, final int priority) {
        super(manager, priority);
        this.player = manager.getFactory().createDebugPlayer();
    }

    @Override
    public void update() {

        if (player.hasComponent(Controllable.class)) {
            var image = player.getComponent(AnimatedImage.class);
            var box = player.getComponent(BoundedBox.class);

            if (manager.getInputs().isKeyPressed(GamePad.LEFT)) {
                image.setOrientation(Orientation.LEFT);
                box.getVector().set(-1, 0);
            } else if (manager.getInputs().isKeyPressed(GamePad.RIGHT)) {
                image.setOrientation(Orientation.RIGHT);
                box.getVector().set(1, 0);
            } else if (manager.getInputs().isKeyPressed(GamePad.UP)) {
                image.setOrientation(Orientation.UP);
                box.getVector().set(0, -1);
            } else if (manager.getInputs().isKeyPressed(GamePad.DOWN)) {
                image.setOrientation(Orientation.DOWN);
                box.getVector().set(0, 1);
            } else {
                image.setOrientation(Orientation.EMPTY);
                box.getVector().reset();
            }
            box.move();
        }
    }

    @Override
    public void draw(final Renderer<?> renderer) {

    }

}
