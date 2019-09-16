package com.github.zelmothedragon.cube.core.entity;

import com.github.zelmothedragon.cube.core.asset.AssetManager;
import com.github.zelmothedragon.cube.core.entity.debug.Clock;
import com.github.zelmothedragon.cube.core.entity.geometry.Point;
import com.github.zelmothedragon.cube.core.entity.geometry.Vector;
import com.github.zelmothedragon.cube.core.graphic.AnimatedSprite;
import com.github.zelmothedragon.cube.core.graphic.FontSprite;
import java.util.UUID;

/**
 * Fabrique d'entités. Une instance unique de cette classe est requise pour le
 * fonctionnement de l'application. Le gestionnaire doit être accessible depuis
 * le conteneur du jeu.
 *
 * @see GameContainer
 *
 * @author MOSELLE Maxime
 */
public final class EntityFactory {

    /**
     * Gestionnaire d'entités.
     */
    private final EntityManager entities;

    /**
     * Gestionnaire des ressources numériques.
     */
    private final AssetManager assets;

    /**
     * Constructeur.Construit une fabrique d'entités. Pour le bon fonctionnement
     * du programme cette classe doit être instanciée une seul fois.
     *
     * @param entities Gestionnaire d'entités
     * @param assets Gestionnaire des ressources numériques
     */
    public EntityFactory(
            final EntityManager entities,
            final AssetManager assets) {

        this.entities = entities;
        this.assets = assets;
    }

    /**
     * Fabriquer une entité pour les informations de débogage du jeu.
     *
     * @return L'entité de la famille <code>Family.DEBUG</code>
     */
    public UUID createDebugInformation() {

        var clock = new Clock();
        var point = new Point();
        var font = new FontSprite(
                assets.loadSprite(AssetManager.DEBUG_8X8_TEXT_SHADOW),
                assets.LoadFontMap(AssetManager.DEBUG_8X8_TEXT_MAP),
                8,
                8
        );

        var id = entities.newEntity(Family.DEBUG);
        entities.addComponent(id, clock);
        entities.addComponent(id, point);
        entities.addComponent(id, font);
        return id;
    }

    public UUID createDebugPlayer() {

        var point = new Point();
        var vector = new Vector();
        var sprite = new AnimatedSprite(
                assets.loadSprite(AssetManager.DEBUG_PLAYER_IMAGE),
                50,
                4,
                16,
                32
        );

        var id = entities.newEntity(Family.PLAYER);
        entities.addComponent(id, point);
        entities.addComponent(id, vector);
        entities.addComponent(id, sprite);
        return id;
    }

}
