package com.github.zelmothedragon.cube.pixel.entity;

import com.github.zelmothedragon.cube.core.entity.image.AnimatedImage;
import com.github.zelmothedragon.cube.core.entity.image.Image;
import java.util.HashMap;
import java.util.Map;

public class PixelArrayAnimatedImage implements AnimatedImage<int[]> {

    private final Map<Integer, PixelArrayImage> cache;

    private final PixelArrayImage sheet;
    
    private final int imageWidth;

    private final int imageHeight;

    private final int duration;

    private final int count;

    private int xOffset;

    private int yOffset;

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
        this.sheet = sheet;
        this.imageWidth = imageWidth;
        this.imageHeight = imageHeight;
        this.duration = duration;
        this.count = count;
        this.frame = 0;
        this.currentImageIndex = 0;
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

        // Calculer l'index et la position de la prochaine image.
        var frac = frame * count / duration;
        var index = Math.min((int) Math.floor(frac), count - 1);
        var columns = sheet.getWidth() / imageWidth;
        var xp = (index % columns) * imageWidth + xOffset;
        var yp = (index / columns) * imageHeight + yOffset;

        currentImageIndex = xp + yp * count;
        if (!cache.containsKey(currentImageIndex)) {

            // Extraire de la feuille d'image
            // l'image de l'animation courante.
            var image = sheet.extract(xp, yp, imageWidth, imageHeight);
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
        return cache.get(currentImageIndex);
    }

    @Override
    public int getDuration() {
        return duration;
    }

    @Override
    public int getCount() {
        return count;
    }

    @Override
    public void setOffset(final int xOffset, final int yOffset) {
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }

}
