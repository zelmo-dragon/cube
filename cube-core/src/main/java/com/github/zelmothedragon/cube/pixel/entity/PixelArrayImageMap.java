package com.github.zelmothedragon.cube.pixel.entity;

import com.github.zelmothedragon.cube.core.entity.image.Image;
import com.github.zelmothedragon.cube.core.entity.image.ImageMap;
import java.util.HashMap;
import java.util.Map;

public class PixelArrayImageMap implements ImageMap<int[]> {

    private final Map<Integer, PixelArrayImage> cache;

    private final PixelArrayImage sheet;

    private final int[][] map;

    private final int imageWidth;

    private final int imageHeight;

    public PixelArrayImageMap(
            final PixelArrayImage sheet,
            final int[][] map,
            final int imageWidth,
            final int imageHeight) {

        this.cache = new HashMap<>();
        this.sheet = sheet;
        this.map = map;
        this.imageWidth = imageWidth;
        this.imageHeight = imageHeight;
    }

    @Override
    public Image<int[]> getImage(int xIndex, int yIndex) {

        PixelArrayImage image;

        if (xIndex >= 0 && xIndex <= getMapWidth() && yIndex >= 0 && yIndex <= getMapHeight()) {

            var index = map[yIndex][xIndex];
            if (cache.containsKey(index)) {
                image = cache.get(index);
            } else if (index == Image.EMPTY_INDEX) {
                image = new PixelArrayImage(imageWidth, imageHeight);
                cache.put(index, image);
            } else {
                var colunms = sheet.getWidth() / imageWidth;
                var xp = (index % colunms) * imageWidth;
                var yp = (index / colunms) * imageHeight;
                image = sheet.extract(xp, yp, imageWidth, imageHeight);
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
        return map[0].length;
    }

    @Override
    public int getMapHeight() {
        return map.length;
    }

}
