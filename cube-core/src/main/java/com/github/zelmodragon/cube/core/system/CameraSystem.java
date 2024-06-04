package com.github.zelmodragon.cube.core.system;

import com.github.zelmodragon.cube.core.graphic.Renderer;
import com.github.zelmodragon.cube.core.GameManager;
import com.github.zelmodragon.cube.core.model.BoundedBox;
import com.github.zelmodragon.cube.core.model.Entity;
import com.github.zelmodragon.cube.core.model.Camera;

/**
 * Gestion de la caméra.
 *
 * @author MOSELLE Maxime
 */
public final class CameraSystem extends AbstractSystem {

    /**
     * Constructeur. Construire un système, une seule instance est nécessaire
     * pour le fonctionnement global de l'application. Le système doit être
     * instancié dans le gestionnaire de système.
     *
     * @param manager Gestionnaire du jeu
     * @param priority Priorité d'exécuter du système
     */
    CameraSystem(final GameManager manager, final int priority) {
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
    public void draw(final Renderer<?> renderer) {

        // Appliquer la caméra pour le rendu.
        renderer.setOffset(
                Camera.INSTANCE.getXp(),
                Camera.INSTANCE.getYp()
        );

    }

    /**
     * Faire suivre la caméra par rapport à une entité.
     *
     * @param entity Une entité possédant l'instance de la caméra
     */
    private static void updateCamera(final Entity entity) {

        var camera = Camera.INSTANCE;
        if (entity.hasComponent(BoundedBox.class)) {
            var box = entity.getComponent(BoundedBox.class);
            camera.setXp(box.getBound().getXp());
            camera.setYp(box.getBound().getYp());
        }
    }

}
