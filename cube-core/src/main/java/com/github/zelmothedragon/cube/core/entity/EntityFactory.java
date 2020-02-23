package com.github.zelmothedragon.cube.core.entity;

import com.github.zelmothedragon.cube.core.asset.AssetManager;
import com.github.zelmothedragon.cube.core.entity.behavior.Controllable;
import com.github.zelmothedragon.cube.core.entity.data.Mandelbrot;
import com.github.zelmothedragon.cube.core.entity.debug.Clock;
import com.github.zelmothedragon.cube.core.entity.geometry.Camera;
import com.github.zelmothedragon.cube.core.entity.geometry.Dimension;
import com.github.zelmothedragon.cube.core.entity.geometry.Orientation;
import com.github.zelmothedragon.cube.core.entity.geometry.Point;
import com.github.zelmothedragon.cube.core.entity.geometry.Rectangle;
import com.github.zelmothedragon.cube.core.entity.geometry.Vector;
import com.github.zelmothedragon.cube.core.graphic.AnimatedSprite;
import com.github.zelmothedragon.cube.core.graphic.AnimatedSpriteMetaData;
import com.github.zelmothedragon.cube.core.graphic.FontSprite;
import com.github.zelmothedragon.cube.core.graphic.Sprite;
import com.github.zelmothedragon.cube.core.graphic.TileMap;

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

        var size = new Dimension(8, 8);
        var clock = new Clock();
        var point = new Point();
        var font = new FontSprite(
                assets.loadSprite(AssetManager.DEBUG_8X8_TEXT_SHADOW),
                size,
                assets.loadFontMap(AssetManager.DEBUG_8X8_TEXT_MAP)
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
        var dimension = new Dimension(w, h);
        var vector = new Vector();
        var sprite = new AnimatedSprite(
                assets.loadSprite(AssetManager.DEBUG_PLAYER_IMAGE),
                dimension,
                75,
                4
        );

        var metadata = new AnimatedSpriteMetaData();
        metadata.addOffset(Orientation.LEFT, new Rectangle(new Point(0, 96), dimension));
        metadata.addOffset(Orientation.RIGHT, new Rectangle(new Point(0, 32), dimension));
        metadata.addOffset(Orientation.UP, new Rectangle(new Point(0, 64), dimension));
        metadata.addOffset(Orientation.DOWN, new Rectangle(new Point(0, 0), dimension));
        metadata.setOrientation(Orientation.DOWN);

        var camera = Camera.INSTANCE;
        var rectangle = new Rectangle(new Point(0, 16), new Dimension(16, 12));

        var entity = new Entity(Family.PLAYER);
        entity.addComponent(Controllable.INSTANCE);
        entity.addComponent(vector);
        entity.addComponent(sprite);
        entity.addComponent(metadata);
        entity.addComponent(camera);
        entity.addComponent(rectangle);
        entities.add(entity);
        return entity;
    }

    public Entity createMapDebug() {

        var size = new Dimension(16, 16);
        var tileMap = new TileMap(
                assets.loadSprite(AssetManager.DEBUG_BACKGROUND_IMAGE),
                size,
                assets.loadMap(AssetManager.DEBUG_BACKGROUND_MAP_LAYER_0)
        );

        var entity = new Entity(Family.MAP_DEBUG);
        entity.addComponent(tileMap);
        entities.add(entity);
        return entity;
    }

    public Entity createMandelbrot() {

        var w = 320;
        var h = w / 16 * 9;
        var dimension = new Dimension(w, h);
        var vector = new Vector();
        var sprite = new Sprite(dimension);
        var mandelbrot = new Mandelbrot(10, 10);

        var entity = new Entity(Family.MANDELBROT);
        entity.addComponent(Controllable.INSTANCE);
        entity.addComponent(sprite);
        entity.addComponent(vector);
        entity.addComponent(mandelbrot);
        entities.add(entity);
        return entity;
    }

}
