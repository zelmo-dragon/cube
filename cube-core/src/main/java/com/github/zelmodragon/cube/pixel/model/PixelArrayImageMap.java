package com.github.zelmodragon.cube.pixel.model;

import com.github.zelmodragon.cube.core.model.Image;
import com.github.zelmodragon.cube.core.model.ImageMap;
import com.github.zelmodragon.cube.core.util.lang.Equal;
import com.github.zelmodragon.cube.core.util.lang.ToString;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Implémentation interne d'une image pour la génération de carte basée sur la
 * manipulation de tableau de pixels.
 *
 * @author MOSELLE Maxime
 */
public class PixelArrayImageMap implements ImageMap<int[]> {

    private final Map<Integer, PixelArrayImage> cache;

    private final PixelArrayImage sheet;

    private final Map<Integer, int[][]> maps;

    private final int imageWidth;

    private final int imageHeight;

    public PixelArrayImageMap(
            final PixelArrayImage sheet,
            final Map<Integer, int[][]> maps,
            final int imageWidth,
            final int imageHeight) {

        this.cache = new HashMap<>();
        this.sheet = sheet;
        this.maps = maps;
        this.imageWidth = imageWidth;
        this.imageHeight = imageHeight;
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                imageWidth,
                imageHeight,
                getMapWidth(),
                getImageHeight()
        );
    }

    @Override
    public boolean equals(final Object obj) {
        return Equal
                .with(PixelArrayImageMap::getImageWidth)
                .thenWith(PixelArrayImageMap::getImageHeight)
                .thenWith(PixelArrayImageMap::getMapWidth)
                .thenWith(PixelArrayImageMap::getMapHeight)
                .apply(this, obj);
    }

    @Override
    public String toString() {
        return ToString
                .with("imageWidth", PixelArrayImageMap::getImageWidth)
                .thenWith("imageHeight", PixelArrayImageMap::getImageHeight)
                .thenWith("mapHeight", PixelArrayImageMap::getMapWidth)
                .thenWith("mapWidth", PixelArrayImageMap::getMapHeight)
                .apply(this);
    }

    @Override
    public Image<int[]> getImage(int xIndex, int yIndex, int layout) {

        PixelArrayImage image;

        if (xIndex >= 0 && xIndex <= getMapWidth() && yIndex >= 0 && yIndex <= getMapHeight()) {

            var index = maps.get(layout)[yIndex][xIndex];
            if (cache.containsKey(index)) {
                image = cache.get(index);
            } else if (index == Image.EMPTY_INDEX) {
                image = new PixelArrayImage(imageWidth, imageHeight);
                cache.put(index, image);
            } else {
                var colunms = sheet.getWidth() / imageWidth;
                var xp = (index % colunms) * imageWidth;
                var yp = (index / colunms) * imageHeight;
                var pixels = sheet.getPixel(xp, yp, imageWidth, imageHeight);
                image = new PixelArrayImage(pixels, imageWidth, imageHeight, index);
                cache.put(index, image);
            }
        } else {
            image = new PixelArrayImage(imageWidth, imageHeight);
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

    @Override
    public int getMapWidth() {
        return maps.get(0)[0].length;
    }

    @Override
    public int getMapHeight() {
        return maps.get(0).length;
    }

    @Override
    public int getLayoutCount() {
        return maps.size();
    }

}
