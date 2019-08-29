package com.github.zelmothedragon.cube.core;

import com.github.zelmothedragon.cube.core.input.InputManager;
import com.github.zelmothedragon.cube.core.system.SystemManager;

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

    /**
     * Gestionnaire des entrées du joueur unique.
     */
    private final InputManager inputManager;

    /**
     * Constructeur. Construit le conteneur principale du jeu, donne accès aux
     * principaux gestionnaires.
     */
    public GameContainer() {
        this.systemManager = new SystemManager(this);
        this.inputManager = new InputManager();
    }

    /**
     * Accesseur, obtenir le gestionnaire de système unique.
     *
     * @return Le gestionnaire de système unique
     */
    public SystemManager getSystems() {
        return systemManager;
    }

    /**
     * Accesseur, obtenir le gestionnaire des entrées du joueur unique.
     *
     * @return Le gestionnaire des entrées du joueur unique
     */
    public InputManager getInputs() {
        return inputManager;
    }

}
