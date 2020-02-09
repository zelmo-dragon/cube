package com.github.zelmothedragon.cube.core.system;

import com.github.zelmothedragon.cube.core.GameManager;
import com.github.zelmothedragon.cube.core.entity.Entity;
import com.github.zelmothedragon.cube.core.entity.geometry.Camera;
import com.github.zelmothedragon.cube.core.entity.geometry.Point;
import com.github.zelmothedragon.cube.core.entity.geometry.Rectangle;
import com.github.zelmothedragon.cube.core.graphic.Render;

/**
 * Gestion de la caméra.
 *
 * @author MOSELLE Maxime
 */
public final class CameraSystem extends AbstractSystem {

    /**
     * Constructeur. Constuire un système, une seule instance est nécessaire
     * pour le fonctionnemenr global de l'application. Le système doit être
     * instancier dans le gestionnaire de système.
     *
     * @param manager Gestionnaire du jeu
     * @param priority Priorié d'exécuter du système
     */
    public CameraSystem(final GameManager manager, final int priority) {
        super(manager, priority);
    }

    @Override
    public void update() {

        manager
                .getEntities()
                .filter(Camera.class)
                .stream()
                .forEach(CameraSystem::updateCamera);

    }

    @Override
    public void draw(final Render g2d) {

        // Appliquer la caméra pour le rendu.
        manager
                .getEntities()
                .filter(Camera.class)
                .stream()
                .map(e -> e.getComponent(Camera.class))
                .forEach(g2d::setOffset);

    }

    /**
     * Faire suivre la caméra par rapport à une entité.
     *
     * @param entity Une entité possédant l'instance de la caméra
     */
    private static void updateCamera(final Entity entity) {

        var camera = entity.getComponent(Camera.class);
        if (entity.hasComponent(Point.class)) {
            var point = entity.getComponent(Point.class);
            camera.follow(point);
        } else if (entity.hasComponent(Rectangle.class)) {
            var rectangle = entity.getComponent(Rectangle.class);
            camera.follow(rectangle);
        } else {
            // RAS
        }
    }

}
