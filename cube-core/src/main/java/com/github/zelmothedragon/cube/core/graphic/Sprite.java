package com.github.zelmothedragon.cube.core.graphic;

import com.github.zelmothedragon.cube.core.entity.Component;
import com.github.zelmothedragon.cube.core.util.lang.ToString;
import java.util.Arrays;

/**
 * Image sous forme de tableau de pixel.
 *
 * @author MOSELLE Maxime
 */
public class Sprite implements Component {

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
    final int[] buffer;

    /**
     * Constructeur. Construit une image.
     *
     * @param width Largeur de l'image
     * @param height Hauteur de l'image
     * @param buffer Image sous forme de tableau de buffer
     */
    public Sprite(
            final int width,
            final int height,
            final int[] buffer) {

        this.width = width;
        this.height = height;
        this.buffer = buffer;
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
        this.buffer = new int[width * height];
    }

    @Override
    public String toString() {
        return ToString
                .of(this)
                .with("width", Sprite::getWidth)
                .with("height", Sprite::getHeight)
                .with("buffer.length", Sprite::getBufferLength)
                .get();
    }

    /**
     * Effacer tout le contenu de l'image.
     */
    public void clear() {
        Arrays.fill(buffer, 0);
    }

    /**
     * Accesseur, obtenir un pixel en fonction d'une coordonnée.
     *
     * @param xp Position en abcisse
     * @param yp Position en ordonnée
     * @return Le pixel à cet emplacement
     */
    public int getPixel(final int xp, final int yp) {
        int pixel;
        if (Pixel.isInBound(xp, width) && Pixel.isInBound(yp, height)) {
            pixel = buffer[xp + yp * width];
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
        if (Pixel.isInBound(xp, width)
                && Pixel.isInBound(yp, height)
                && alpha != 0) {

            buffer[xp + yp * width] = pixel;
        }
    }

    /**
     * Accesseur, obtenir le nombre de pixel contenu dans la mémoire tampon.
     *
     * @return Le nombre de pixel dans la mémoire tampon
     */
    protected int getBufferLength() {
        return buffer.length;
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
     * Accesseur, obtenir l'image sous forme de tableau de buffer.
     *
     * @return L'image sous forme de tableau de buffer
     */
    public int[] getBuffer() {
        // Permet l'immuabilité de cette classe
        return Arrays.copyOf(buffer, buffer.length);
    }

}
