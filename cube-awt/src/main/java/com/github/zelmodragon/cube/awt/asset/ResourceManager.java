package com.github.zelmodragon.cube.awt.asset;

import com.github.zelmodragon.cube.core.asset.AssetManager;
import com.github.zelmodragon.cube.core.model.Image;
import com.github.zelmodragon.cube.pixel.asset.PixelArrayAssetManager;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * Implémentation référence du gestionnaire des ressources numériques. Basé sur
 * la bibliothèque Java AWT.
 *
 * @author MOSELLE MaximeAssetManager.
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
            var bufferedImage = ImageIO.read(stream);
            var width = bufferedImage.getWidth();
            var height = bufferedImage.getHeight();
            var buffer = bufferedImage.getRGB(0, 0, width, height, null, 0, width);
            image = loadImage(buffer, width, height);
        } catch (IOException ex) {
            throw new IllegalArgumentException("Image not found: " + imagePath, ex);
        }
        return image;
    }

}
