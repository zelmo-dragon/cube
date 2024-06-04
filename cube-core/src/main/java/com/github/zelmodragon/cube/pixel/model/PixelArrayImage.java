package com.github.zelmodragon.cube.pixel.model;

import com.github.zelmodragon.cube.pixel.graphic.Pixels;
import com.github.zelmodragon.cube.core.model.Image;
import com.github.zelmodragon.cube.core.util.lang.Equal;
import com.github.zelmodragon.cube.core.util.lang.ToString;

import java.util.Arrays;
import java.util.Objects;

/**
 * Implémentation interne d'une image simple basée sur la manipulation de tableau
 * de pixels.
 *
 * @author MOSELLE Maxime
 */
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
                .thenWith("pixels", e -> e.buffer.length)
                .apply(this);
    }

    public void clear() {
        Arrays.fill(buffer, Pixels.COLOR_TRANSPARENT);
    }

    public int getPixel(final int x, final int y) {
        int color;
        if (Pixels.isInBound(x, y, width, height)) {
            var i = x + y * width;
            color = buffer[i];
        } else {
            color = Pixels.COLOR_TRANSPARENT;
        }
        return color;
    }

    public void setPixel(final int x, final int y, final int color) {
        if (Pixels.isInBound(x, y, width, height)) {
            var i = x + y * width;
            buffer[i] = color;
        }
    }

    public int[] getPixel(final int x, final int y, final int w, final int h) {
        int[] pixels = new int[w * h];
        for (var yp = 0; yp < h; yp++) {
            var ya = yp + y;

            for (var xp = 0; xp < w; xp++) {
                var xa = xp + x;
                pixels[xp + yp * w] = getPixel(xa, ya);
            }
        }
        return pixels;
    }

    public void setPixel(final int x, final int y, final int w, final int h, final int[] pixels) {
        for (var yp = 0; yp < h; yp++) {
            var ya = yp + y;

            for (var xp = 0; xp < w; xp++) {
                var xa = xp + x;
                var color = pixels[xp + yp * w];
                setPixel(xa, ya, color);
            }
        }
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
