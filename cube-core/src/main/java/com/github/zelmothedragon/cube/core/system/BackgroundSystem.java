package com.github.zelmothedragon.cube.core.system;

import com.github.zelmothedragon.cube.core.GameManager;
import com.github.zelmothedragon.cube.core.entity.Family;
import com.github.zelmothedragon.cube.core.entity.geometry.Point;
import com.github.zelmothedragon.cube.core.graphic.Render;
import com.github.zelmothedragon.cube.core.graphic.TileMap;

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
    public void draw(final Render g2d) {
        manager
                .getEntities()
                .get(Family.MAP_DEBUG)
                .stream()
                .map(e -> e.getComponent(TileMap.class))
                .forEach(t -> g2d.drawImage(new Point(), t));

    }

}
