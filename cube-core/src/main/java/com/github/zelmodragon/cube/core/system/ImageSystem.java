package com.github.zelmodragon.cube.core.system;

import com.github.zelmodragon.cube.core.graphic.Renderer;
import com.github.zelmodragon.cube.core.GameManager;
import com.github.zelmodragon.cube.core.model.BoundedBox;
import com.github.zelmodragon.cube.core.model.Image;
import com.github.zelmodragon.cube.core.model.Entity;

/**
 * Système de gestion des images.
 *
 * @author MOSELLE Maxime
 */
public final class ImageSystem extends AbstractSystem {

    /**
     * Constructeur. Construire un système, une seule instance est nécessaire
     * pour le fonctionnement global de l'application. Le système doit être
     * instancié dans le gestionnaire de système.
     *
     * @param manager Gestionnaire du jeu
     * @param priority Priorié d'exécuter du système
     */
    ImageSystem(final GameManager manager, final int priority) {
        super(manager, priority);
    }

    @Override
    public void update() {
    }

    @Override
    public void draw(final Renderer<?> renderer) {
        manager
                .getEntities()
                .filter(Image.class)
                .forEach(e -> drawImage(renderer, e));
    }

    private static void drawImage(final Renderer<?> renderer, final Entity entity) {
        var box = entity.getComponent(BoundedBox.class);
        var image = entity.getComponent(Image.class);
        renderer.drawImage(
                box.getBound().getXp(),
                box.getBound().getYp(),
                image
        );
    }

}
