package com.github.zelmothedragon.cube.pixel.asset;

import com.github.zelmothedragon.cube.core.asset.AssetManager;
import com.github.zelmothedragon.cube.core.component.AnimatedImage;
import com.github.zelmothedragon.cube.core.component.FontImage;
import com.github.zelmothedragon.cube.core.component.Image;
import com.github.zelmothedragon.cube.core.component.ImageMap;
import com.github.zelmothedragon.cube.pixel.component.PixelArrayAnimatedImage;
import com.github.zelmothedragon.cube.pixel.component.PixelArrayFontImage;
import com.github.zelmothedragon.cube.pixel.component.PixelArrayImage;
import com.github.zelmothedragon.cube.pixel.component.PixelArrayImageMap;

/**
 * Implémentation interne du gestionnaire des ressouces numériques. Basé sur la
 * manipulation d'image sous forme de tableau de pixels.
 *
 * @author MOSELLE Maxime
 */
public abstract class PixelArrayAssetManager implements AssetManager<int[]> {

    /**
     * Constructeur. Construit le gestionnaire des ressources numériques.
     */
    protected PixelArrayAssetManager() {
        // RAS
    }

    @Override
    public Image<int[]> loadImage(final int w, final int h) {
        return new PixelArrayImage(w, h);
    }

    @Override
    public AnimatedImage<int[]> loadAnimatedImage(final String imagePath, final int w, final int h, final int duration, final int count) {
        var sheet = (PixelArrayImage) loadImage(imagePath);
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
