package com.github.zelmothedragon.cube.core.entity;

import com.github.zelmothedragon.cube.core.GameContainer;
import com.github.zelmothedragon.cube.core.asset.AssetManager;
import com.github.zelmothedragon.cube.core.entity.behavior.Controllable;
import com.github.zelmothedragon.cube.core.entity.data.Mandelbrot;
import com.github.zelmothedragon.cube.core.entity.debug.Clock;
import com.github.zelmothedragon.cube.core.entity.geometry.Orientation;
import com.github.zelmothedragon.cube.core.entity.geometry.Point;
import com.github.zelmothedragon.cube.core.entity.geometry.Rectangle;
import com.github.zelmothedragon.cube.core.entity.geometry.Vector;
import com.github.zelmothedragon.cube.core.graphic.AnimatedSprite;
import com.github.zelmothedragon.cube.core.graphic.AnimatedSpriteMetaData;
import com.github.zelmothedragon.cube.core.graphic.FontSprite;
import com.github.zelmothedragon.cube.core.graphic.Sprite;

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
    public Entity createDebugInformation() {

        var clock = new Clock();
        var point = new Point();
        var font = new FontSprite(
                assets.loadSprite(AssetManager.DEBUG_8X8_TEXT_SHADOW),
                assets.loadFontMap(AssetManager.DEBUG_8X8_TEXT_MAP),
                8,
                8
        );

        var entity = new Entity(Family.DEBUG);
        entity.addComponent(clock);
        entity.addComponent(point);
        entity.addComponent(font);
        entities.add(entity);
        return entity;
    }

    public Entity createDebugPlayer() {

        var w = 16;
        var h = 32;
        var rectangle = new Rectangle(0, 0, w, h);
        var vector = new Vector();
        var sprite = new AnimatedSprite(
                assets.loadSprite(AssetManager.DEBUG_PLAYER_IMAGE),
                75,
                4,
                w,
                h
        );

        var metadata = new AnimatedSpriteMetaData();
        metadata.addOffset(Orientation.LEFT, new Rectangle(0, 96, w, h));
        metadata.addOffset(Orientation.RIGHT, new Rectangle(0, 32, w, h));
        metadata.addOffset(Orientation.UP, new Rectangle(0, 64, w, h));
        metadata.addOffset(Orientation.DOWN, new Rectangle(0, 0, w, h));
        metadata.setOrientation(Orientation.DOWN);

        var entity = new Entity(Family.PLAYER);
        entity.addComponent(Controllable.INSTANCE);
        entity.addComponent(rectangle);
        entity.addComponent(vector);
        entity.addComponent(sprite);
        entity.addComponent(metadata);
        entities.add(entity);
        return entity;
    }

    public Entity createMandelbrot() {

        var w = 320;
        var h = w / 16 * 9;

        var vector = new Vector();
        var sprite = new Sprite(w, h);
        var rectangle = new Rectangle(64, 32);
        var mandelbrot = new Mandelbrot(10, 10);

        var entity = new Entity(Family.MANDELBROT);
        entity.addComponent(Controllable.INSTANCE);
        entity.addComponent(sprite);
        entity.addComponent(rectangle);
        entity.addComponent(vector);
        entity.addComponent(mandelbrot);
        entities.add(entity);
        return entity;
    }

}
