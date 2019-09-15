package com.github.zelmothedragon.cube.core;

import com.github.zelmothedragon.cube.core.asset.AssetManager;
import com.github.zelmothedragon.cube.core.input.InputManager;
import com.github.zelmothedragon.cube.core.entity.EntityManager;
import com.github.zelmothedragon.cube.core.system.SystemManager;

/**
 * Conteneur du contexte du jeu. Il rassemble les principaux gestionnaires.
 *
 * @author MOSELLE Maxime
 */
public final class GameContainer {

    /**
     * Gestionnaire des ressources numériques unique.
     */
    private final AssetManager assetManager;

    /**
     * Gestionnaire de système unique.
     */
    private final SystemManager systemManager;

    /**
     * Gestionnaire des entrées du joueur unique.
     */
    private final InputManager inputManager;

    /**
     * Gestionnaire d'entités unique.
     */
    private final EntityManager entityManager;

    /**
     * Constructeur.Construit le conteneur principale du jeu, donne accès aux
     * principaux gestionnaires.
     *
     * @param assetManager
     */
    public GameContainer(final AssetManager assetManager) {
        this.assetManager = assetManager;
        this.systemManager = new SystemManager(this);
        this.inputManager = new InputManager();
        this.entityManager = new EntityManager();
    }

    /**
     * Accesseur, obtenit le gestionnaire de ressources numériques unique.
     *
     * @return Le gestionnaire des ressources numériques unique.
     */
    public AssetManager getAssets() {
        return assetManager;
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

    /**
     * Accesseur, obtenir le gestionnaire d'entités unique.
     *
     * @return Le gestionnaire d'entité unique
     */
    public EntityManager getEntities() {
        return entityManager;
    }

}
