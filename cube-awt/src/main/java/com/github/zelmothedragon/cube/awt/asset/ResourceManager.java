package com.github.zelmothedragon.cube.awt.asset;

import com.github.zelmothedragon.cube.core.asset.AssetManager;
import com.github.zelmothedragon.cube.core.entity.image.AnimatedImage;
import com.github.zelmothedragon.cube.core.entity.image.FontImage;
import com.github.zelmothedragon.cube.core.entity.image.Image;
import com.github.zelmothedragon.cube.core.entity.image.ImageMap;
import com.github.zelmothedragon.cube.pixel.entity.PixelArrayAnimatedImage;
import com.github.zelmothedragon.cube.pixel.entity.PixelArrayFontImage;
import com.github.zelmothedragon.cube.pixel.entity.PixelArrayImage;
import com.github.zelmothedragon.cube.pixel.entity.PixelArrayImageMap;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * Implémentation référence du gestionnaire des ressouces numériques. Basé sur
 * la bibliothèque Java AWT.
 *
 * @author MOSELLE MaximeAssetManager.
 */
public final class ResourceManager implements AssetManager<int[]> {

    /**
     * Constructeur. Construit le gestionnaire des ressources numériques.
     */
    public ResourceManager() {
        // RAS
    }

    @Override
    public Image<int[]> loadImage(final int w, final int h) {
        return new PixelArrayImage(w, h);
    }

    @Override
    public Image<int[]> loadImage(final String imagePath) {
        PixelArrayImage image;
        try (var stream = AssetManager.loadResource(imagePath)) {
            var bufferedImage = ImageIO.read(stream);
            var width = bufferedImage.getWidth();
            var height = bufferedImage.getHeight();
            var buffer = bufferedImage.getRGB(0, 0, width, height, null, 0, width);
            image = new PixelArrayImage(buffer, width, height);
        } catch (IOException ex) {
            ex.printStackTrace();
            image = null;
        }
        return image;
    }

    @Override
    public AnimatedImage<int[]> loadAnimatedImage(final String imagePath, final int w, final int h, final int duration, final int count) {
        PixelArrayImage sheet = (PixelArrayImage) loadImage(imagePath);
        return new PixelArrayAnimatedImage(sheet, w, h, duration, count);
    }

    @Override
    public FontImage<int[]> loadFontImagge(final String imagePath, final String mapPath, final int w, final int h) {
        var sheet = (PixelArrayImage) loadImage(imagePath);
        var fontMap = AssetManager.loadFontMap(mapPath);
        return new PixelArrayFontImage(sheet, fontMap, w, h);
    }

    @Override
    public ImageMap<int[]> loadImageMap(final String imagePath, final String mapPath, final int w, final int h) {
        var sheet = (PixelArrayImage) loadImage(imagePath);
        var imageMap = AssetManager.loadMap(mapPath);
        return new PixelArrayImageMap(sheet, imageMap, w, h);
    }

}
