package com.github.zelmothedragon.cube.core.graphic;

import java.util.Arrays;

/**
 * Image sous forme de tableau de pixels.
 *
 * @author MOSELLE Maxime
 */
public final class Sprite {

    /**
     * Largeur de l'image.
     */
    final int width;

    /**
     * Hauteur de l'image.
     */
    final int height;

    /**
     * Image sous forme de tableau de pixels. Ce tampon sera ensuite envoyer
     * vers l'image de destination afin d'être afficher à l'écran.
     */
    final int[] pixels;

    /**
     * Constructeur. Construit une image.
     *
     * @param width Largeur de l'image
     * @param height Hauteur de l'image
     * @param pixels Image sous forme de tableau de pixels
     */
    public Sprite(
            final int width,
            final int height,
            final int[] pixels) {

        this.width = width;
        this.height = height;
        this.pixels = pixels;
    }

    /**
     * Constructeur. Construit une image vide.
     *
     * @param width Largeur de l'image
     * @param height Hauteur de l'image
     */
    public Sprite(
            final int width,
            final int height) {

        this.width = width;
        this.height = height;
        this.pixels = new int[width * height];
    }

    public int getPixel(final int xp, final int yp) {
        int pixel;
        if (Pixel.isInBound(xp, width) && Pixel.isInBound(yp, height)) {
            pixel = pixels[xp + yp * width];
        } else {
            pixel = 0;
        }
        return pixel;
    }

    /**
     * Muttateur, écrire un pixel sur l'image.
     *
     * @param xp Position en abcisse
     * @param yp Position en ordonnée
     * @param pixel Pixel
     */
    public void setPixel(
            final int xp,
            final int yp,
            final int pixel) {

        var alpha = Pixel.getAlpha(pixel);
        if (xp >= 0 && xp < width && yp >= 0 && yp < height && alpha > 0) {
            pixels[xp + yp * width] = pixel;
        }
    }

    /**
     * Accesseur, obtenir la largeur de l'image.
     *
     * @return La largeur
     */
    public int getWidth() {
        return width;
    }

    /**
     * Accesseur, obtenir la hauteur de l'image.
     *
     * @return La hauteur
     */
    public int getHeight() {
        return height;
    }

    /**
     * Accesseur, obtenir l'image sous forme de tableau de pixels.
     *
     * @return L'image sous forme de tableau de pixels
     */
    public int[] getPixels() {
        return Arrays.copyOf(pixels, pixels.length);
    }

}
