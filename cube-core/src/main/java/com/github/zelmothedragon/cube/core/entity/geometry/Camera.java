package com.github.zelmothedragon.cube.core.entity.geometry;

import com.github.zelmothedragon.cube.core.entity.Component;

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
     * Position en abcisse.
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

    /**
     * Suivre un point avec cette caméra.
     *
     * @param point Un point à suivre
     */
    public void follow(final Point point) {
        this.xp = point.getXp();
        this.yp = point.getYp();
    }

    /**
     * Suivre un rectangle avec cette caméra.
     *
     * @param rectangle Un rectangle
     */
    public void follow(final Rectangle rectangle) {
        // Centrer la caméra
        this.xp = rectangle.getXp() + rectangle.getWidth() / 2;
        this.yp = rectangle.getYp() + rectangle.getHeight() / 2;
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
     * Accesseur, obtenir la position en ordonnée.
     *
     * @return La position en ordonnée
     */
    public int getYp() {
        return yp;
    }

}
