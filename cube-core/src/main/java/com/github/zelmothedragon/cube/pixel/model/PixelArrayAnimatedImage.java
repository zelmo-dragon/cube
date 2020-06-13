package com.github.zelmothedragon.cube.pixel.model;

import com.github.zelmothedragon.cube.core.model.AnimatedImage;
import com.github.zelmothedragon.cube.core.model.Image;
import com.github.zelmothedragon.cube.core.model.Orientation;
import com.github.zelmothedragon.cube.core.util.geometry.Rectangle;
import com.github.zelmothedragon.cube.core.util.lang.Equal;
import com.github.zelmothedragon.cube.core.util.lang.ToString;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Implémentation interne d'une image animée basé sur la manipulation de tableau
 * de pixels.
 *
 * @author MOSELLE Maxime
 */
public class PixelArrayAnimatedImage implements AnimatedImage<int[]> {

    private final Map<Integer, PixelArrayImage> cache;

    private final Map<Orientation, Rectangle> orientations;

    private final PixelArrayImage sheet;

    private final PixelArrayImage empty;

    private final int imageWidth;

    private final int imageHeight;

    private final int duration;

    private final int count;

    private Orientation orientation;

    private Rectangle offset;

    private int frame;

    private int currentImageIndex;

    private boolean playing;

    public PixelArrayAnimatedImage(
            final PixelArrayImage sheet,
            final int imageWidth,
            final int imageHeight,
            final int duration,
            final int count) {

        this.cache = new HashMap<>();
        this.orientations = new EnumMap<>(Orientation.class);
        this.sheet = sheet;
        this.imageWidth = imageWidth;
        this.imageHeight = imageHeight;
        this.duration = duration;
        this.count = count;
        this.frame = 0;
        this.currentImageIndex = 0;
        this.empty = new PixelArrayImage(imageWidth, imageHeight);
    }

    @Override
    public int hashCode() {
        return Objects.hash(imageWidth, imageHeight, duration, count);
    }

    @Override
    public boolean equals(final Object obj) {
        return Equal
                .with(PixelArrayAnimatedImage::getImageWidth)
                .thenWith(PixelArrayAnimatedImage::getImageHeight)
                .thenWith(PixelArrayAnimatedImage::getDuration)
                .thenWith(PixelArrayAnimatedImage::getCount)
                .apply(this, obj);
    }

    @Override
    public String toString() {
        return ToString
                .with("imageWidth", PixelArrayAnimatedImage::getImageWidth)
                .thenWith("imageHeight", PixelArrayAnimatedImage::getImageHeight)
                .thenWith("duration", PixelArrayAnimatedImage::getDuration)
                .thenWith("count", PixelArrayAnimatedImage::getCount)
                .apply(this);
    }

    @Override
    public void addOffset(final Orientation orientation, Rectangle rectangle) {
        this.orientations.put(orientation, rectangle);
    }

    @Override
    public Rectangle getOffset(final Orientation orientation) {
        return orientations.get(orientation);
    }

    @Override
    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
        this.offset = orientations.getOrDefault(orientation, offset);
    }

    @Override
    public Orientation getOrientation() {
        return orientation;
    }

    @Override
    public Rectangle getCurrentOffset() {
        return offset;
    }

    @Override
    public void update() {
        // Réinitialiser l'index de l'animation lorsque qu'un cycle complet
        // de l'animation est exécuter.
        if (playing) {
            frame++;
            if (frame > duration) {
                frame = 0;
            }
        }
        if (Objects.equals(orientation, Orientation.EMPTY)) {
            stop();
        } else {
            play();
        }

        // Calculer l'index et la position de la prochaine image.
        var frac = frame * count / duration;
        var index = Math.min(frac, count - 1);
        var columns = sheet.getWidth() / imageWidth;
        var xp = (index % columns) * imageWidth + offset.getXp();
        var yp = (index / columns) * imageHeight + offset.getYp();

        currentImageIndex = xp + yp * imageWidth;
        if (!cache.containsKey(currentImageIndex)) {
            // Extraire de la feuille d'image
            // l'image de l'animation courante.
            var pixels = sheet.getPixel(xp, yp, imageWidth, imageHeight);
            var image = new PixelArrayImage(pixels, imageWidth, imageHeight, currentImageIndex);
            cache.put(currentImageIndex, image);
        }
    }

    @Override
    public void play() {
        this.playing = true;
    }

    @Override
    public void pause() {
        this.playing = false;
    }

    @Override
    public void stop() {
        this.playing = false;
        this.frame = 0;
    }

    @Override
    public boolean isPlaying() {
        return playing;
    }

    @Override
    public Image<int[]> getCurrentImage() {
        return cache.getOrDefault(currentImageIndex, empty);
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
    public int getDuration() {
        return duration;
    }

    @Override
    public int getCount() {
        return count;
    }
}
