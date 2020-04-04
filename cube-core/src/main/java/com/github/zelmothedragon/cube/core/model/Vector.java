package com.github.zelmothedragon.cube.core.model;

import com.github.zelmothedragon.cube.core.model.Component;
import com.github.zelmothedragon.cube.core.util.lang.Equal;
import com.github.zelmothedragon.cube.core.util.lang.ToString;
import java.util.Objects;

/**
 * Vecteur 2D.
 *
 * @author MOSELLE Maxime
 */
@Deprecated
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
    public boolean equals(final Object obj) {
        return Equal
                .with(Vector::getDx)
                .thenWith(Vector::getDy)
                .apply(this, obj);
    }

    @Override
    public String toString() {
        return ToString
                .with("dx", Vector::getDx)
                .thenWith("dy", Vector::getDy)
                .apply(this);
    }

    /**
     * Réinitialiser le vector.
     */
    public void reset() {
        this.dx = 0;
        this.dy = 0;
    }

    /**
     * Modifier l'ampleur du vecteur.
     *
     * @param dx L'ampleur du vecteur en abcisse
     * @param dy L'ampleur du vecteur en ordonnée
     */
    public void set(final int dx, final int dy) {
        this.dx = dx;
        this.dy = dy;
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
