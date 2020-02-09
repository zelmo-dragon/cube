package com.github.zelmothedragon.cube.core.system;

import com.github.zelmothedragon.cube.core.GameManager;
import com.github.zelmothedragon.cube.core.graphic.Render;

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
    }

    @Override
    public void update() {
    }

    @Override
    public void draw(final Render g2d) {
    }

}
