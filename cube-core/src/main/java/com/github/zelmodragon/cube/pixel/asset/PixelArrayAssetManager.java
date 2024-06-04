package com.github.zelmodragon.cube.pixel.asset;

import com.github.zelmodragon.cube.pixel.model.PixelArrayAnimatedImage;
import com.github.zelmodragon.cube.pixel.model.PixelArrayImageMap;
import com.github.zelmodragon.cube.core.asset.AssetManager;
import com.github.zelmodragon.cube.core.model.AnimatedImage;
import com.github.zelmodragon.cube.core.model.FontImage;
import com.github.zelmodragon.cube.core.model.Image;
import com.github.zelmodragon.cube.core.model.ImageMap;
import com.github.zelmodragon.cube.pixel.model.PixelArrayFontImage;
import com.github.zelmodragon.cube.pixel.model.PixelArrayImage;

import java.util.Map;

/**
 * Implémentation interne du gestionnaire des ressources numériques. Basé sur la
 * manipulation d'image sous forme de tableau de pixels.
 *
 * @author MOSELLE Maxime
 */
public class PixelArrayAssetManager implements AssetManager<int[]> {

    /**
     * Constructeur. Construit le gestionnaire des ressources numériques.
     */
    public PixelArrayAssetManager() {
        // RAS
    }

    @Override
    public Image<int[]> loadImage(String imagePath) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Image<int[]> loadImage(
            final int w,
            final int h) {

        return new PixelArrayImage(w, h);
    }

    @Override
    public Image<int[]> loadImage(int[] data, int w, int h) {
        return new PixelArrayImage(data, w, h);
    }

    @Override
    public AnimatedImage<int[]> loadAnimatedImage(
            final String imagePath,
            final int w,
            final int h,
            final int duration,
            final int count) {

        var sheet = (PixelArrayImage) loadImage(imagePath);
        return new PixelArrayAnimatedImage(sheet, w, h, duration, count);
    }

    @Override
    public FontImage<int[]> loadFontImagge(
            final String imagePath,
            final String mapPath,
            final int w,
            final int h) {

        var sheet = (PixelArrayImage) loadImage(imagePath);
        var fontMap = AssetManager.loadFontMap(mapPath);
        return new PixelArrayFontImage(sheet, fontMap, w, h);
    }

    @Override
    public ImageMap<int[]> loadImageMap(
            final String imagePath,
            final Map<Integer, String> mapPath,
            final int w,
            final int h) {

        var sheet = (PixelArrayImage) loadImage(imagePath);
        var imageMap = AssetManager.loadMap(mapPath);
        return new PixelArrayImageMap(sheet, imageMap, w, h);
    }

}
