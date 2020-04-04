package com.github.zelmothedragon.cube.core.system;

import com.github.zelmothedragon.cube.core.GameManager;
import com.github.zelmothedragon.cube.core.component.BoundedBox;
import com.github.zelmothedragon.cube.core.component.ImageMap;
import com.github.zelmothedragon.cube.core.entity.Entity;
import com.github.zelmothedragon.cube.core.entity.Family;
import com.github.zelmothedragon.cube.core.graphic.Renderer;

/**
 * Système de gestion du décor en arrière plan.
 *
 * @author MOSELLE Maxime
 */
public class BackgroundSystem extends AbstractSystem {

    /**
     * Constructeur. Constuire un système, une seule instance est nécessaire
     * pour le fonctionnemenr global de l'application. Le système doit être
     * instancier dans le gestionnaire de système.
     *
     * @param manager Gestionnaire du jeu
     * @param priority Priorié d'exécuter du système
     */
    BackgroundSystem(final GameManager manager, final int priority) {
        super(manager, priority);
        manager.getFactory().createMapDebug();
    }

    @Override
    public void update() {
    }

    @Override
    public void draw(final Renderer<?> renderer) {
        var entities = manager
                .getEntities()
                .get(Family.MAP_DEBUG);

        entities.forEach(e -> drawImage(e, renderer));

    }

    private static void drawImage(final Entity entity, final Renderer<?> renderer) {
        var box = entity.getComponent(BoundedBox.class);
        var image = entity.getComponent(ImageMap.class);
        renderer.drawImage(
                box.getBound().getXp(),
                box.getBound().getYp(),
                image
        );
    }

}
