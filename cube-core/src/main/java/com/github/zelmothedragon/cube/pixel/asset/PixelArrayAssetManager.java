package com.github.zelmothedragon.cube.pixel.asset;

import com.github.zelmothedragon.cube.core.asset.AssetManager;
import com.github.zelmothedragon.cube.core.entity.image.AnimatedImage;
import com.github.zelmothedragon.cube.core.entity.image.FontImage;
import com.github.zelmothedragon.cube.core.entity.image.Image;
import com.github.zelmothedragon.cube.core.entity.image.ImageMap;
import com.github.zelmothedragon.cube.pixel.entity.PixelArrayAnimatedImage;
import com.github.zelmothedragon.cube.pixel.entity.PixelArrayFontImage;
import com.github.zelmothedragon.cube.pixel.entity.PixelArrayImage;
import com.github.zelmothedragon.cube.pixel.entity.PixelArrayImageMap;

public class PixelArrayAssetManager implements AssetManager<int[]> {

    public PixelArrayAssetManager() {
    }

    @Override
    public Image<int[]> loadImage(final int w, final int h) {
        return new PixelArrayImage(w, h);
    }

    @Override
    public Image<int[]> loadImage(String imagePath) {
        throw new UnsupportedOperationException("Not supported yet.");
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
