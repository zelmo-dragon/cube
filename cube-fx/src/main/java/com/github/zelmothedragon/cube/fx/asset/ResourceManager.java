package com.github.zelmothedragon.cube.fx.asset;

import com.github.zelmothedragon.cube.core.asset.AssetManager;
import com.github.zelmothedragon.cube.core.model.Image;
import com.github.zelmothedragon.cube.pixel.asset.PixelArrayAssetManager;
import java.io.IOException;
import javafx.scene.image.PixelFormat;

/**
 * Implémentation référence du gestionnaire des ressouces numériques. Basé sur
 * la bibliothèque JavaFX.
 *
 * @author MOSELLE Maxime
 */
public final class ResourceManager extends PixelArrayAssetManager {

    /**
     * Constructeur. Construit le gestionnaire des ressources numériques.
     */
    public ResourceManager() {
        // RAS
    }

    @Override
    public Image<int[]> loadImage(final String imagePath) {

        Image<int[]> image;
        try (var stream = AssetManager.loadResource(imagePath)) {

            var imageFX = new javafx.scene.image.Image(stream);
            var width = (int) imageFX.getWidth();
            var height = (int) imageFX.getHeight();
            var buffer = new int[width * height];

            imageFX
                    .getPixelReader()
                    .getPixels(
                            0,
                            0,
                            width,
                            height,
                            PixelFormat.getIntArgbPreInstance(),
                            buffer,
                            0,
                            width
                    );

            image = loadImage(buffer, width, height);

        } catch (IOException ex) {
            ex.printStackTrace();
            image = null;
        }
        return image;
    }

}
