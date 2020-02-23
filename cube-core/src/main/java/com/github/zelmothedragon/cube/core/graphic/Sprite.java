package com.github.zelmothedragon.cube.core.graphic;

import com.github.zelmothedragon.cube.core.entity.Component;
import com.github.zelmothedragon.cube.core.entity.geometry.Dimension;
import com.github.zelmothedragon.cube.core.entity.geometry.Rectangle;
import com.github.zelmothedragon.cube.core.util.lang.Equal;
import com.github.zelmothedragon.cube.core.util.lang.ToString;
import java.util.Arrays;
import java.util.Objects;

/**
 * Image sous forme de tableau de pixel.
 *
 * @author MOSELLE Maxime
 */
public class Sprite implements Component {

    /**
     * Rectangle représentant la position et la dimension.
     */
    protected final Rectangle rectangle;

    /**
     * Image sous forme de tableau de pixels. Ce tampon sera ensuite envoyer
     * vers l'image de destination afin d'être afficher à l'écran.
     */
    protected final int[] buffer;

    /**
     * Constructeur. Construit une image.
     *
     * @param dimension Dimension
     * @param buffer Image sous forme de tableau de buffer
     */
    public Sprite(
            final Dimension dimension,
            final int[] buffer) {

        this.rectangle = new Rectangle(dimension);
        this.buffer = buffer;
    }

    /**
     * Constructeur. Construit une image vide.
     *
     * @param dimension Dimension
     */
    public Sprite(final Dimension dimension) {

        this.rectangle = new Rectangle(dimension);
        this.buffer = new int[dimension.getWidth() * dimension.getHeight()];
    }

    @Override
    public int hashCode() {
        return Objects.hash(rectangle, buffer);
    }

    @Override
    public boolean equals(Object obj) {
        return Equal
                .with(Sprite::getRectangle)
                .thenWith(s -> s.buffer)
                .apply(this, obj);
    }

    @Override
    public String toString() {
        return ToString
                .with("rectangle", Sprite::getRectangle)
                .thenWith("buffer.length", Sprite::getBufferLength)
                .apply(this);
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
        if (Pixel.isInBound(xp, rectangle.getDimension().getWidth())
                && Pixel.isInBound(yp, rectangle.getDimension().getHeight())) {

            pixel = buffer[xp + yp * rectangle.getDimension().getWidth()];
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
        if (Pixel.isInBound(xp, rectangle.getDimension().getWidth())
                && Pixel.isInBound(yp, rectangle.getDimension().getHeight())
                && alpha != 0) {

            buffer[xp + yp * rectangle.getDimension().getWidth()] = pixel;
        }
    }

    public void setPixel(int index, int pixel) {
        if (index > 0 && index < buffer.length) {
            buffer[index] = pixel;
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
     * Accesseur, obtenir le rectangle représentant la position et la dimension.
     *
     * @return Le rectange
     */
    public Rectangle getRectangle() {
        return rectangle;
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
