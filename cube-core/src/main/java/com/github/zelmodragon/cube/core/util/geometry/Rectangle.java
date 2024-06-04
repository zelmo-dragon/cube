package com.github.zelmodragon.cube.core.util.geometry;

import com.github.zelmodragon.cube.core.util.lang.Equal;
import com.github.zelmodragon.cube.core.util.lang.ToString;
import java.util.Objects;

/**
 * Rectangle 2D.
 *
 * @author MOSELLE Maxime
 */
public final class Rectangle {

    /**
     * Position en abscisse.
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
     * Constructeur. Construit un rectangle avec une taille.
     *
     * @param width Largeur
     * @param height Hauteur
     */
    public Rectangle(final int width, final int height) {
        this.xp = 0;
        this.yp = 0;
        this.width = width;
        this.height = height;
    }

    /**
     * Constructeur. Construit un rectangle avec une position et une taille.
     *
     * @param xp Position en abscisse
     * @param yp Position en ordonnée
     * @param width Largeur
     * @param height Hauteur
     */
    public Rectangle(final int xp, final int yp, final int width, final int height) {
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
     * Vérifier si un point est contenu dans ce rectangle.
     *
     * @param other Un point quelconque
     * @return La valeur <code>true</code> si le point est contenu dans le
     * rectangle, sinon la valeur <code>false</code>
     */
    public boolean contains(final Point other) {
        return other.getXp() >= xp
                && other.getXp() <= xp + width
                && other.getYp() >= yp
                && other.getYp() <= yp + height;
    }

    /**
     * Vérifier si un rectangle est entièrement compris dans ce rectangle.
     *
     * @param other Rectangle quelconque
     * @return La valeur <code>true</code> si le rectangle passé en paramètre
     * est entièrement contenu dans ce rectangle, sinon la valeur
     * <code>false</code>
     */
    public boolean contains(final Rectangle other) {
        boolean contain;
        if (Objects.isNull(other)) {
            contain = false;
        } else {
            contain = other.getXp() >= xp
                    && other.getYp() >= yp
                    && other.getXp() + other.getWidth() <= xp + width
                    && other.getYp() + other.getHeight() <= yp + height;
        }
        return contain;
    }

    /**
     * Vérifier si un rectangle est en intersection avec ce rectangle.
     *
     * @param other Rectangle quelconque
     * @return La valeur <code>true</code> si le rectangle passé en paramètre
     * est en intersection de ce rectangle, sinon la valeur <code>false</code>
     */
    public boolean intersects(final Rectangle other) {
        boolean intersect;
        if (Objects.isNull(other)) {
            intersect = false;
        } else {
            intersect = other.getXp() + other.getWidth() > xp
                    && other.getYp() + other.getHeight() > yp
                    && other.getXp() < xp + width
                    && other.getYp() < yp + height;
        }
        return intersect;
    }

    public Rectangle createIntersection(final Rectangle other) {
        var x0 = Math.max(xp, other.getXp());
        var y0 = Math.max(yp, other.getYp());
        var x1 = Math.min(xp + width, other.getXp() + other.getWidth());
        var y1 = Math.min(yp + height, other.getYp() + other.getHeight());
        return new Rectangle(x0, y0, x1 - x0, y1 - y0);
    }

    /**
     * Déplacer un point en fonction d'un vecteur.
     *
     * @param vector Vecteur de déplacement
     */
    public void move(final Vector vector) {
        this.xp += vector.getDx();
        this.yp += vector.getDy();
    }

    /**
     * Déplacer un point en fonction d'un vecteur.
     *
     * @param dx Vecteur de déplacement sur abcisse
     * @param dy Vecteur de déplacement sur ordonnée
     */
    public void move(final int dx, final int dy) {
        this.xp += dx;
        this.yp += dy;
    }

    /**
     * Accesseur, obtenir la position en abscisse.
     *
     * @return La position en abscisse
     */
    public int getXp() {
        return xp;
    }

    /**
     * Muttateur, modifier la position en abscisse.
     *
     * @param xp La position en abscisse
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
     * Mutateur, modifier la largeur.
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
     * Mutateur, modifier la hauteur.
     *
     * @param height La hauteur
     */
    public void setHeight(int height) {
        this.height = height;
    }

}
