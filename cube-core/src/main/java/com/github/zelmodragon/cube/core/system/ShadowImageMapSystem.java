package com.github.zelmodragon.cube.core.system;

import com.github.zelmodragon.cube.core.graphic.Renderer;
import com.github.zelmodragon.cube.core.GameManager;
import com.github.zelmodragon.cube.core.model.BoundedBox;
import com.github.zelmodragon.cube.core.model.Entity;
import com.github.zelmodragon.cube.core.model.ImageMap;

/**
 * Système de gestion du décor des ombres.
 *
 * @author MOSELLE Maxime
 */
public class ShadowImageMapSystem extends AbstractSystem {

    /**
     * Indice de profondeur des ombres.
     */
    private static final int LAYOUT = 1;

    /**
     * Constructeur. Construire un système, une seule instance est nécessaire
     * pour le fonctionnement global de l'application. Le système doit être
     * instancié dans le gestionnaire de système.
     *
     * @param manager Gestionnaire du jeu
     * @param priority Priorité d'exécuter du système
     */
    ShadowImageMapSystem(final GameManager manager, final int priority) {
        super(manager, priority);
        manager.getFactory().createMapDebug();
    }

    @Override
    public void update() {
    }

    @Override
    public void draw(final Renderer<?> renderer) {
        manager
                .getEntities()
                .filter(ImageMap.class)
                .forEach(e -> drawImage(renderer, e));

    }

    private static void drawImage(final Renderer<?> renderer, final Entity entity) {
        var box = entity.getComponent(BoundedBox.class);
        var image = entity.getComponent(ImageMap.class);
        if (image.getLayoutCount() > LAYOUT) {
            renderer.drawImage(
                    box.getBound().getXp(),
                    box.getBound().getYp(),
                    image,
                    LAYOUT
            );
        }
    }

}
