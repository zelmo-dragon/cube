package com.github.zelmothedragon.cube.core.system;

import com.github.zelmothedragon.cube.core.GameManager;
import com.github.zelmothedragon.cube.core.entity.Entity;
import com.github.zelmothedragon.cube.core.entity.behavior.Controllable;
import com.github.zelmothedragon.cube.core.entity.geometry.Orientation;
import com.github.zelmothedragon.cube.core.entity.geometry.Rectangle;
import com.github.zelmothedragon.cube.core.entity.geometry.Vector;
import com.github.zelmothedragon.cube.core.entity.image.AnimatedImage;
import com.github.zelmothedragon.cube.core.entity.image.AnimatedImageMetaData;
import com.github.zelmothedragon.cube.core.graphic.Renderer;
import com.github.zelmothedragon.cube.core.input.GamePad;
import java.util.Objects;

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

        var metaData = player.getComponent(AnimatedImageMetaData.class);
        if (player.hasComponent(Controllable.class)) {
            var vector = player.getComponent(Vector.class);
            if (manager.getInputs().isKeyPressed(GamePad.LEFT)) {
                metaData.setOrientation(Orientation.LEFT);
                vector.set(-1, 0);
            } else if (manager.getInputs().isKeyPressed(GamePad.RIGHT)) {
                metaData.setOrientation(Orientation.RIGHT);
                vector.set(1, 0);
            } else if (manager.getInputs().isKeyPressed(GamePad.UP)) {
                metaData.setOrientation(Orientation.UP);
                vector.set(0, -1);
            } else if (manager.getInputs().isKeyPressed(GamePad.DOWN)) {
                metaData.setOrientation(Orientation.DOWN);
                vector.set(0, 1);
            } else {
                metaData.setOrientation(Orientation.EMPTY);
                vector.reset();
            }

            var rectangle = player.getComponent(Rectangle.class);
            rectangle.getPoint().move(vector);
        }

        var orientation = metaData.getOrientation();
        var image = player.getComponent(AnimatedImage.class);
                
        if (Objects.equals(orientation, Orientation.EMPTY)) {
            image.stop();
        } else {
            var offset = metaData.getCurrentOffset();
            image.setOffset(offset.getPoint().getXp(), offset.getPoint().getYp());
            image.play();
        }
        image.update();
    }

    @Override
    public void draw(final Renderer<?> renderer) {

        var rectangle = player.getComponent(Rectangle.class);
        var image = player.getComponent(AnimatedImage.class);
        renderer.drawImage(
                rectangle.getPoint().getXp(),
                rectangle.getPoint().getYp(),
                image
        );

    }

}
