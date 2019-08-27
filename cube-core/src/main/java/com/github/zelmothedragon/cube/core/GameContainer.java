package com.github.zelmothedragon.cube.core;

/**
 * Conteneur du contexte du jeu. Il rassemble les principaux gestionnaires.
 *
 * @author MOSELLE Maxime
 */
public final class GameContainer {

    /**
     * Gestionnaire de système unique.
     */
    private final SystemManager systemManager;

    public GameContainer() {
        this.systemManager = new SystemManager(this);
    }

    /**
     * Accesseur, récupère le gestionnaire de système unique.
     *
     * @return Le gestionnaire de système unique
     */
    public SystemManager getSystemManager() {
        return systemManager;
    }

}
