package com.github.zelmothedragon.cube.pixel.entity;

import com.github.zelmothedragon.cube.core.entity.image.Image;
import com.github.zelmothedragon.cube.core.util.lang.Equal;
import com.github.zelmothedragon.cube.core.util.lang.ToString;
import com.github.zelmothedragon.cube.pixel.graphic.Pixels;
import java.util.Arrays;
import java.util.Objects;

public class PixelArrayImage implements Image<int[]> {

    private final int[] buffer;

    private final int width;

    private final int height;

    private final int index;

    public PixelArrayImage(
            final int[] buffer,
            final int width,
            final int height,
            final int index) {

        this.buffer = buffer;
        this.width = width;
        this.height = height;
        this.index = index;
    }

    public PixelArrayImage(
            final int[] buffer,
            final int width,
            final int height) {

        this(buffer, width, height, EMPTY_INDEX);
    }

    public PixelArrayImage(final int width, final int height) {
        this(new int[width * height], width, height, EMPTY_INDEX);
    }

    @Override
    public int hashCode() {
        return Objects.hash(width, height, index);
    }

    @Override
    public boolean equals(final Object obj) {
        return Equal
                .with(PixelArrayImage::getWidth)
                .thenWith(PixelArrayImage::getHeight)
                .thenWith(PixelArrayImage::getIndex)
                .apply(this, obj);
    }

    @Override
    public String toString() {
        return ToString
                .with("width", PixelArrayImage::getWidth)
                .thenWith("height", PixelArrayImage::getHeight)
                .thenWith("index", PixelArrayImage::getIndex)
                .apply(this);
    }

    public void clear() {
        Arrays.fill(buffer, Pixels.COLOR_TRANSPARENT);
    }

    public int getPixel(final int x, final int y) {
        int color;
        if (Pixels.isInBound(x, width) && Pixels.isInBound(y, height)) {
            var i = x + y * width;
            color = buffer[i];
        } else {
            color = Pixels.COLOR_TRANSPARENT;
        }
        return color;
    }

    public void setPixel(final int x, final int y, final int color) {
        if (Pixels.isInBound(x, width) && Pixels.isInBound(y, height)) {
            var i = x + y * width;
            buffer[i] = color;
        }
    }

    public PixelArrayImage extract(final int x, final int y, final int w, final int h) {
        var pixels = new int[w * h];

        for (var yp = 0; yp < h; yp++) {
            var ya = yp + y;

            for (var xp = 0; xp < w; xp++) {
                var xa = xp + x;
                pixels[xp + yp * w] = getPixel(xa, ya);
            }
        }
        return new PixelArrayImage(pixels, w, h);
    }

    @Override
    public int getIndex() {
        return index;
    }

    @Override
    public int[] getRawData() {
        return buffer;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

}
