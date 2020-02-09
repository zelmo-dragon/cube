package com.github.zelmothedragon.cube.core;

import com.github.zelmothedragon.cube.core.asset.AssetManager;
import com.github.zelmothedragon.cube.core.entity.EntityFactory;
import com.github.zelmothedragon.cube.core.entity.EntityManager;
import com.github.zelmothedragon.cube.core.input.InputManager;
import com.github.zelmothedragon.cube.core.system.SystemManager;

/**
 * Conteneur du contexte du jeu. Il rassemble les principaux gestionnaires.
 *
 * @author MOSELLE Maxime
 */
public final class GameManager {

    /**
     * Gestionnaire des ressources numériques unique.
     */
    private final AssetManager assets;

    /**
     * Gestionnaire de système unique.
     */
    private final SystemManager systems;

    /**
     * Gestionnaire des entrées unique.
     */
    private final InputManager inputs;

    /**
     * Gestionnaire d'entités unique.
     */
    private final EntityManager entities;

    /**
     * Fabrique d'entité unique.
     */
    private final EntityFactory factory;

    /**
     * Constructeur.Construit le conteneur principale du jeu, donne accès aux
     * principaux gestionnaires.
     *
     * @param assets Gestionnaire des ressources numériques
     */
    public GameManager(final AssetManager assets) {
        this.assets = assets;
        this.inputs = new InputManager();
        this.entities = new EntityManager();
        this.factory = new EntityFactory(entities, assets);

        // Doit être initialisé à la fin
        // Car il s'agit de dépendance bidirectionnelle
        this.systems = new SystemManager(this);
    }

    /**
     * Accesseur, obtenit le gestionnaire de ressources numériques unique.
     *
     * @return Le gestionnaire des ressources numériques unique.
     */
    public AssetManager getAssets() {
        return assets;
    }

    /**
     * Accesseur, obtenir le gestionnaire de système unique.
     *
     * @return Le gestionnaire de systèmes
     */
    public SystemManager getSystems() {
        return systems;
    }

    /**
     * Accesseur, obtenir le gestionnaire des entrées du joueur unique.
     *
     * @return Le gestionnaire des entrées
     */
    public InputManager getInputs() {
        return inputs;
    }

    /**
     * Accesseur, obtenir le gestionnaire d'entités unique.
     *
     * @return Le gestionnaire d'entités
     */
    public EntityManager getEntities() {
        return entities;
    }

    /**
     * Accesseur, obtenir la fabrique d'entités unique.
     *
     * @return La fabrique d'entités
     */
    public EntityFactory getFactory() {
        return factory;
    }

}