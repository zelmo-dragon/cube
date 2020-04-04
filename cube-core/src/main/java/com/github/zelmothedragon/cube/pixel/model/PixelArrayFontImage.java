package com.github.zelmothedragon.cube.pixel.model;

import com.github.zelmothedragon.cube.core.model.FontImage;
import com.github.zelmothedragon.cube.core.model.Image;
import com.github.zelmothedragon.cube.core.util.lang.Equal;
import com.github.zelmothedragon.cube.core.util.lang.ToString;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Implémentation interne d'une police de caractère basé sur la manipulation de
 * tableau de pixels.
 *
 * @author MOSELLE Maxime
 */
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
    public int hashCode() {
        return Objects.hash(imageWidth, imageHeight, fontMap);
    }

    @Override
    public boolean equals(final Object obj) {
        return Equal
                .with(PixelArrayFontImage::getImageWidth)
                .thenWith(PixelArrayFontImage::getImageHeight)
                .thenWith(e -> e.fontMap)
                .apply(this, obj);
    }

    @Override
    public String toString() {
        return ToString
                .with("imageWidth", PixelArrayFontImage::getImageWidth)
                .thenWith("imageHeight", PixelArrayFontImage::getImageHeight)
                .thenWith("fontMap", e -> e.fontMap)
                .apply(this);
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
                var pixels = sheet.getPixel(xp, yp, imageWidth, imageHeight);
                image = new PixelArrayImage(pixels, imageWidth, imageHeight, index);
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
