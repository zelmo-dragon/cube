package com.github.zelmothedragon.cube.core.entity.geometry;

import com.github.zelmothedragon.cube.core.entity.Component;
import java.util.Objects;

/**
 * Vecteur 2D.
 *
 * @author MOSELLE Maxime
 */
public final class Vector implements Component {

    /**
     * Ampleur du vecteur en abcisse.
     */
    private int dx;

    /**
     * Ampleur du vecteur en ordonnée.
     */
    private int dy;

    /**
     * Constructeur par défaut.
     */
    public Vector() {
        this.dx = 0;
        this.dy = 0;
    }

    /**
     * Constructeur. Construit un vecteur.
     *
     * @param dx Ampleur du vecteur en abcisse
     * @param dy Ampleur du vecteur en ordonnée
     */
    public Vector(final int dx, final int dy) {
        this.dx = dx;
        this.dy = dy;
    }

    @Override
    public int hashCode() {
        return Objects.hash(dx, dy);
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
                var other = (Vector) obj;
                eq = other.dx == dx && other.dy == dy;
            }
        }
        return eq;
    }

    @Override
    public String toString() {

        return new StringBuilder()
                .append("Vector{dx=")
                .append(dx)
                .append(", dy=")
                .append(dy)
                .append("}")
                .toString();
    }

    /**
     * Réinitialiser le vector.
     */
    public void reset() {
        this.dx = 0;
        this.dy = 0;
    }

    /**
     * Accesseur, obtenir l'ampleur du vecteur en abcisse.
     *
     * @return L'ampleur du vecteur en abcisse
     */
    public int getDx() {
        return dx;
    }

    /**
     * Muttateur, modifier l'ampleur du vecteur en abcisse
     *
     * @param dx L'ampleur du vecteur en abcisse
     */
    public void setDx(int dx) {
        this.dx = dx;
    }

    /**
     * Accesseur, obtenir l'ampleur du vecteur en ordonnée.
     *
     * @return L'ampleur du vecteur en ordonnée
     */
    public int getDy() {
        return dy;
    }

    /**
     * Muttateur, modifier l'ampleur du vecteur en ordonnée
     *
     * @param dy L'ampleur du vecteur en ordonnée
     */
    public void setDy(int dy) {
        this.dy = dy;
    }

}
