package com.github.zelmothedragon.cube.core.system;

import com.github.zelmothedragon.cube.core.GameManager;
import com.github.zelmothedragon.cube.core.model.AnimatedImage;
import com.github.zelmothedragon.cube.core.model.BoundedBox;
import com.github.zelmothedragon.cube.core.model.Entity;
import com.github.zelmothedragon.cube.core.graphic.Renderer;

/**
 * Système de gestion des images animées.
 *
 * @author MOSELLE Maxime
 */
public final class AnimatedImageSystem extends AbstractSystem {

    /**
     * Constructeur. Constuire un système, une seule instance est nécessaire
     * pour le fonctionnemenr global de l'application. Le système doit être
     * instancier dans le gestionnaire de système.
     *
     * @param manager Gestionnaire du jeu
     * @param priority Priorié d'exécuter du système
     */
    AnimatedImageSystem(final GameManager manager, final int priority) {
        super(manager, priority);
    }

    @Override
    public void update() {
        manager
                .getEntities()
                .filter(AnimatedImage.class)
                .stream()
                .map(e -> e.getComponent(AnimatedImage.class))
                .forEach(AnimatedImage::update);
    }

    @Override
    public void draw(final Renderer<?> renderer) {
        manager
                .getEntities()
                .filter(AnimatedImage.class)
                .forEach(e -> drawImage(renderer, e));
    }

    private static void drawImage(final Renderer<?> renderer, final Entity entity) {
        var box = entity.getComponent(BoundedBox.class);
        var image = entity.getComponent(AnimatedImage.class);
        renderer.drawImage(
                box.getBound().getXp(),
                box.getBound().getYp(),
                image
        );
    }

}
