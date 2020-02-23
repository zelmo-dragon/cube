package com.github.zelmothedragon.cube.core.entity.geometry;

import com.github.zelmothedragon.cube.core.entity.Component;
import com.github.zelmothedragon.cube.core.util.lang.Equal;
import com.github.zelmothedragon.cube.core.util.lang.ToString;
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
     * Position.
     */
    private final Point point;

    /**
     * Constructeur interne, pas d'instanciation.
     */
    private Camera() {
        this.point = new Point();
    }

    @Override
    public int hashCode() {
        return Objects.hash(point);
    }

    @Override
    public boolean equals(final Object obj) {
        return Equal
                .with(Camera::getPoint)
                .apply(this, obj);
    }

    @Override
    public String toString() {
        return ToString
                .with("point", Camera::getPoint)
                .apply(this);
    }

    /**
     * Suivre un point avec cette caméra.
     *
     * @param point Un point à suivre
     */
    public void follow(final Point point) {
        this.point.setPont(point);
    }

    /**
     * Suivre un rectangle avec cette caméra.
     *
     * @param rectangle Un rectangle
     */
    public void follow(final Rectangle rectangle) {
        // Centrer la caméra
        var xp = rectangle.getPoint().getXp() + rectangle.getDimension().getWidth() / 2;
        var yp = rectangle.getPoint().getYp() + rectangle.getDimension().getHeight() / 2;
        point.setXp(xp);
        point.setYp(yp);
    }

    /**
     * Obtenir la position de cette caméra.
     *
     * @return La position
     */
    public Point getPoint() {
        return point;
    }

}
