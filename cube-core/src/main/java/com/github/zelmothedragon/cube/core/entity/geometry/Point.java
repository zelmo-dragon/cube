package com.github.zelmothedragon.cube.core.entity.geometry;

import com.github.zelmothedragon.cube.core.entity.Component;
import java.util.Objects;

/**
 * Point 2D.
 *
 * @author MOSELLE Maxime
 */
@Deprecated
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
    public boolean equals(Object obj) {
        boolean eq;
        if (this == obj) {
            eq = true;
        } else if (Objects.isNull(obj)) {
            eq = false;
        } else {
            if (!Objects.equals(getClass(), obj.getClass())) {
                eq = false;
            } else {
                var other = (Point) obj;
                eq = other.xp == xp
                        && other.yp == yp;
            }
        }
        return eq;
    }

    @Override
    public String toString() {

        return new StringBuilder()
                .append("Point{xp=")
                .append(xp)
                .append(", yp=")
                .append(yp)
                .append("}")
                .toString();
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
