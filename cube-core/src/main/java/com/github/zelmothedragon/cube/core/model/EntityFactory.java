package com.github.zelmothedragon.cube.core.model;

import com.github.zelmothedragon.cube.core.asset.AssetManager;
import com.github.zelmothedragon.cube.core.util.geometry.Dimension;
import com.github.zelmothedragon.cube.core.util.geometry.Rectangle;
import java.util.Map;

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
    private final AssetManager<?> assets;

    /**
     * Constructeur.Construit une fabrique d'entités. Pour le bon fonctionnement
     * du programme cette classe doit être instanciée une seul fois.
     *
     * @param entities Gestionnaire d'entités
     * @param assets Gestionnaire des ressources numériques
     */
    public EntityFactory(
            final EntityManager entities,
            final AssetManager<?> assets) {

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
        var box = new BoundedBox();
        var font = assets.loadFontImagge(
                AssetManager.DEBUG_8X8_TEXT_SHADOW,
                AssetManager.DEBUG_8X8_TEXT_MAP,
                8,
                8
        );

        var entity = new Entity(Family.DEBUG);
        entity.addComponent(clock);
        entity.addComponent(box);
        entity.addComponent(FontImage.class, font);
        entities.add(entity);
        return entity;
    }

    public Entity createDebugPlayer() {

        var w = 16;
        var h = 32;
        var vector = new Vector();
        var animation = assets.loadAnimatedImage(AssetManager.DEBUG_PLAYER_IMAGE_SET,
                w,
                h,
                50,
                4
        );

        animation.addOffset(Orientation.DOWN, new Rectangle(0, 0, w, h));
        animation.addOffset(Orientation.RIGHT, new Rectangle(0, 32, w, h));
        animation.addOffset(Orientation.UP, new Rectangle(0, 64, w, h));
        animation.addOffset(Orientation.LEFT, new Rectangle(0, 96, w, h));
        animation.setOrientation(Orientation.DOWN);

        var box = new BoundedBox(
                new Rectangle(0, 0, w, h),
                new Rectangle(0, 8, 16, 18),
                Block.SOLID
        );

        var entity = new Entity(Family.PLAYER);
        entity.addComponent(Controllable.INSTANCE);
        entity.addComponent(Movable.INSTANCE);
        entity.addComponent(Camera.INSTANCE);
        entity.addComponent(vector);
        entity.addComponent(box);
        entity.addComponent(AnimatedImage.class, animation);
        entities.add(entity);
        return entity;
    }

    public Entity createMapDebug() {

        var box = new BoundedBox();
        var map = Map.of(
                0,
                AssetManager.DEBUG_BACKGROUND_MAP_LAYER_0,
                1,
                AssetManager.DEBUG_BACKGROUND_MAP_LAYER_1,
                2,
                AssetManager.DEBUG_BACKGROUND_MAP_LAYER_2,
                3,
                AssetManager.DEBUG_BACKGROUND_MAP_LAYER_3,
                4,
                AssetManager.DEBUG_BACKGROUND_MAP_LAYER_4
        );

        var image = assets.loadImageMap(
                AssetManager.DEBUG_BACKGROUND_IMAGE_SET,
                map,
                16,
                16
        );

        var entity = new Entity(Family.MAP_DEBUG);
        entity.addComponent(box);
        entity.addComponent(ImageMap.class, image);
        entities.add(entity);
        return entity;
    }

    public Entity createDebugBlock() {

        var size = 16;
        var image = assets.loadImage(AssetManager.DEBUG_WOOD_IMAGE);
        var box = new BoundedBox(
                new Rectangle(64, 64, size, size),
                new Rectangle(64, 64, size, size),
                Block.SOLID
        );

        var entity = new Entity(Family.EMPTY);
        entity.addComponent(box);
        entity.addComponent(Image.class, image);
        entities.add(entity);
        return entity;
    }

    public Entity createMandelbrot() {

        var w = 800;
        var h = w / 16 * 9;
        var box = new BoundedBox(new Dimension(w, h));
        var image = assets.loadImage(w, h);
        var mandelbrot = new Mandelbrot(10, 10);

        var entity = new Entity(Family.MANDELBROT);
        entity.addComponent(Controllable.INSTANCE);
        entity.addComponent(box);
        entity.addComponent(mandelbrot);
        entity.addComponent(Image.class, image);
        entities.add(entity);
        return entity;
    }

}
