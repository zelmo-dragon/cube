package com.github.zelmothedragon.cube.fx.asset;

import com.github.zelmothedragon.cube.core.asset.AssetManager;
import com.github.zelmothedragon.cube.core.graphic.Sprite;
import javafx.scene.image.Image;
import javafx.scene.image.PixelFormat;

/**
 * Implémentation référence du gestionnaire des ressouces numériques. Basé sur
 * la bibliothèque JavaFX.
 *
 * @author MOSELLE Maxime
 */
public final class ResourceManager implements AssetManager {

    /**
     * Constructeur. Construit le gestionnaire des ressources numériques.
     */
    public ResourceManager() {
        // RAS
    }

    @Override
    public Sprite loadSprite(final String path) {

        var image = new Image(path);
        var width = (int) image.getWidth();
        var height = (int) image.getHeight();
        var pixels = new int[width * height];

        image
                .getPixelReader()
                .getPixels(
                        0,
                        0,
                        width,
                        height,
                        PixelFormat.getIntArgbPreInstance(),
                        pixels,
                        0,
                        width
                );

        return new Sprite(width, height, pixels);

    }

}
