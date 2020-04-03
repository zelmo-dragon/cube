package com.github.zelmothedragon.cube.pixel.entity;

import com.github.zelmothedragon.cube.core.entity.image.FontImage;
import com.github.zelmothedragon.cube.core.entity.image.Image;
import java.util.HashMap;
import java.util.Map;

public class PixelArrayFontImage implements FontImage<int[]> {

    private final Map<Integer, PixelArrayImage> cache;

    private final PixelArrayImage sheet;

    private final String fontMap;

    private final int imageWidth;

    private final int imageHeight;

    public PixelArrayFontImage(
            final PixelArrayImage sheet,
            final String fontMap,
            final int imageWidth,
            final int imageHeight) {

        this.cache = new HashMap<>();
        this.sheet = sheet;
        this.fontMap = fontMap;
        this.imageWidth = imageWidth;
        this.imageHeight = imageHeight;
    }

    @Override
    public Image<int[]> getImage(final String character) {

        PixelArrayImage image;
        var index = fontMap.indexOf(character);
        if (cache.containsKey(index)) {
            image = cache.get(index);
        } else {
            if (index != Image.EMPTY_INDEX) {
                var columns = sheet.getWidth() / imageWidth;
                var xp = (index % columns) * imageWidth;
                var yp = (index / columns) * imageHeight;
                image = sheet.extract(xp, yp, imageWidth, imageHeight);
            } else {
                image = new PixelArrayImage(imageWidth, imageHeight);
            }
            cache.put(index, image);
        }
        return image;
    }

    @Override
    public int getImageWidth() {
        return imageWidth;
    }

    @Override
    public int getImageHeight() {
        return imageHeight;
    }

}
