package com.github.zelmothedragon.cube.pixel.entity;

import com.github.zelmothedragon.cube.core.entity.image.FontImage;
import com.github.zelmothedragon.cube.core.entity.image.Image;
import com.github.zelmothedragon.cube.core.util.lang.Equal;
import com.github.zelmothedragon.cube.core.util.lang.ToString;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

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
                .with("width", PixelArrayFontImage::getImageWidth)
                .thenWith("height", PixelArrayFontImage::getImageHeight)
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
