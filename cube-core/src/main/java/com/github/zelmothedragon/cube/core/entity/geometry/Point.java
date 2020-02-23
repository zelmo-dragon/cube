package com.github.zelmothedragon.cube.core.entity.geometry;

import com.github.zelmothedragon.cube.core.entity.Component;
import com.github.zelmothedragon.cube.core.util.lang.Equal;
import com.github.zelmothedragon.cube.core.util.lang.ToString;
import java.util.Objects;

/**
 * Point 2D.
 *
 * @author MOSELLE Maxime
 */
public final class Point implements Component {

    /**
     * Position en abcisse.
     */
    private int xp;

    /**
     * Position en ordonnée.
     */
    private int yp;

    /**
     * Constructeur par défaut.
     */
    public Point() {
        this.xp = 0;
        this.yp = 0;
    }

    /**
     * Constructeur. Construit un point à une position.
     *
     * @param xp
     * @param yp
     */
    public Point(final int xp, final int yp) {
        this.xp = xp;
        this.yp = yp;
    }

    @Override
    public int hashCode() {
        return Objects.hash(xp, yp);
    }

    @Override
    public boolean equals(final Object obj) {
        return Equal
                .with(Point::getXp)
                .thenWith(Point::getYp)
                .apply(this, obj);
    }

    @Override
    public String toString() {

        return ToString
                .with("xp", Point::getXp)
                .thenWith("yp", Point::getYp)
                .apply(this);
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
     * Superposer deux points.
     *
     * @param point Un autre point
     */
    public void setPont(final Point point) {
        this.xp = point.xp;
        this.yp = point.yp;
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

}
