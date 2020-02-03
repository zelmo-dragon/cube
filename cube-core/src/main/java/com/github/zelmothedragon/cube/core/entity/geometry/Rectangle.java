package com.github.zelmothedragon.cube.core.entity.geometry;

import com.github.zelmothedragon.cube.core.entity.Component;
import com.github.zelmothedragon.cube.core.util.lang.Equal;
import com.github.zelmothedragon.cube.core.util.lang.ToString;
import java.util.Objects;

/**
 * Rectangle 2D.
 *
 * @author MOSELLE Maxime
 */
public final class Rectangle implements Component {

    /**
     * Position en abcisse.
     */
    private int xp;

    /**
     * Position en ordonnée.
     */
    private int yp;

    /**
     * Largeur.
     */
    private int width;

    /**
     * Hauteur.
     */
    private int height;

    /**
     * Constructeur par défaut.
     */
    public Rectangle() {
        this.xp = 0;
        this.yp = 0;
        this.width = 0;
        this.height = 0;
    }

    /**
     * Constructeur. Construit un rectangle avec une taille.
     *
     * @param width Largeur
     * @param height Hauteur
     */
    public Rectangle(
            final int width,
            final int height) {

        this.xp = 0;
        this.yp = 0;
        this.width = width;
        this.height = height;
    }

    /**
     * Constructeur. Construit un rectangle avec une position et une taille.
     *
     * @param xp Position en abcisse
     * @param yp Position en ordonnée
     * @param width Largeur
     * @param height Hauteur
     */
    public Rectangle(
            final int xp,
            final int yp,
            final int width,
            final int height) {

        this.xp = xp;
        this.yp = yp;
        this.width = width;
        this.height = height;
    }

    @Override
    public int hashCode() {
        return Objects.hash(xp, yp, width, height);
    }

    @Override
    public boolean equals(final Object obj) {
        return Equal
                .with(Rectangle::getXp)
                .thenWith(Rectangle::getYp)
                .thenWith(Rectangle::getWidth)
                .thenWith(Rectangle::getHeight)
                .apply(this, obj);
    }

    @Override
    public String toString() {
        return ToString
                .with("xp", Rectangle::getXp)
                .thenWith("yp", Rectangle::getYp)
                .thenWith("width", Rectangle::getWidth)
                .thenWith("height", Rectangle::getHeight)
                .apply(this);
    }

    /**
     * Déplacer un rectangle en fonction d'un vecteur.
     *
     * @param vector Vecteur de déplacement
     */
    public void move(final Vector vector) {
        this.xp += vector.getDx();
        this.yp += vector.getDy();
    }

    /**
     * Vérifier si un point est contenu dans ce rectangle.
     *
     * @param x Position en abcisse
     * @param y Position en ordonnée
     * @return La valeur <code>true</code> si le point est contenu dans le
     * rectangle, sinon la valeur <code>false</code>
     */
    public boolean contains(final int x, final int y) {
        return x >= xp
                && x <= xp + width
                && y >= yp
                && y <= yp + height;
    }

    /**
     * Vérifier si un rectangle est entièrement compris dans ce rectangle.
     *
     * @param rectangle Rectangle quelconque
     * @return La valeur <code>true</code> si le rectangle passé en paramètre
     * est entièrement contenu dans ce rectangle, sinon la valeur
     * <code>false</code>
     */
    public boolean contains(final Rectangle rectangle) {
        boolean contain;
        if (Objects.isNull(rectangle)) {
            contain = false;
        } else {
            contain = rectangle.xp >= xp
                    && rectangle.yp >= yp
                    && rectangle.xp + rectangle.width <= xp + width
                    && rectangle.yp + rectangle.height <= yp + height;
        }
        return contain;
    }

    /**
     * Vérifier si un rectangle est en intersection avec ce rectangle.
     *
     * @param rectangle Rectangle quelconque
     * @return La valeur <code>true</code> si le rectangle passé en paramètre
     * est en intersection de ce rectangle, sinon la valeur <code>false</code>
     */
    public boolean intersects(final Rectangle rectangle) {
        boolean intersect;
        if (Objects.isNull(rectangle)) {
            intersect = false;
        } else {
            intersect = rectangle.xp + rectangle.width > xp
                    && rectangle.yp + rectangle.height > yp
                    && rectangle.xp < xp + width
                    && rectangle.yp < yp + height;
        }
        return intersect;
    }

    /**
     * Vérifier si un rectangle est en intersection avec ce rectangle.
     *
     * @param x Position en abcisse.
     * @param y Position en ordonnée
     * @param w Largeur
     * @param h Hauteur
     * @return La valeur <code>true</code> si le rectangle passé en paramètre
     * est en intersection de ce rectangle, sinon la valeur <code>false</code>
     */
    public boolean intersects(
            final int x,
            final int y,
            final int w,
            final int h) {

        return x < xp + width
                && y < yp + height
                && x + w > xp
                && y + h > yp;
    }

    /**
     * Accesseur, obtenir la position en abcisse.
     *
     * @return La position en abcisse
     */
    public int getXp() {
        return xp;
    }

    /**
     * Muttateur, modifier la position en abcisse.
     *
     * @param xp La position en abcisse
     */
    public void setXp(int xp) {
        this.xp = xp;
    }

    /**
     * Accesseur, obtenir la position en ordonnée.
     *
     * @return La position en ordonnée
     */
    public int getYp() {
        return yp;
    }

    /**
     * Muttateur, modifier la position en ordonnée.
     *
     * @param yp La position en ordonnée
     */
    public void setYp(int yp) {
        this.yp = yp;
    }

    /**
     * Accesseur, obtenir la largeur.
     *
     * @return La largeur
     */
    public int getWidth() {
        return width;
    }

    /**
     * Muttateur, modifier la largeur.
     *
     * @param width La largeur
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * Accesseur, obtenir la hauteur.
     *
     * @return La hauteur
     */
    public int getHeight() {
        return height;
    }

    /**
     * Muttateur, modifier la hauteur.
     *
     * @param height La hauteur
     */
    public void setHeight(int height) {
        this.height = height;
    }

}
