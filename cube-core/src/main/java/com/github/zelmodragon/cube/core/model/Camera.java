package com.github.zelmodragon.cube.core.model;

import com.github.zelmodragon.cube.core.util.lang.Equal;
import com.github.zelmodragon.cube.core.util.lang.ToString;
import java.util.Objects;

/**
 * Caméra.
 *
 * @author MOSELLE Maxime
 */
public final class Camera implements Component {

    /**
     * Instance unique.
     */
    public static final Camera INSTANCE = new Camera();

    /**
     * Position en abscisse.
     */
    private int xp;

    /**
     * Position en ordonnée.
     */
    private int yp;

    /**
     * Constructeur interne, pas d'instanciation.
     */
    private Camera() {
        this.xp = 0;
        this.yp = 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(xp, yp);
    }

    @Override
    public boolean equals(final Object obj) {
        return Equal
                .with(Camera::getXp)
                .thenWith(Camera::getYp)
                .apply(this, obj);
    }

    @Override
    public String toString() {
        return ToString
                .with("xp", Camera::getXp)
                .thenWith("yp", Camera::getYp)
                .apply(this);
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

}
