package com.github.zelmothedragon.cube.core.entity.geometry;

import com.github.zelmothedragon.cube.core.entity.Component;

/**
 *
 * @author MOSELLE Maxime
 */
public final class Camera implements Component {

    public static final Camera INSTANCE = new Camera();

    private int xp;

    private int yp;

    private Camera() {
    }

    public void follow(final Point point) {
        this.xp = point.getXp();
        this.yp = point.getYp();
    }

    public void follow(final Rectangle rectangle) {
        this.xp = rectangle.getXp();
        this.yp = rectangle.getYp();
    }

    public int getXp() {
        return xp;
    }

    public int getYp() {
        return yp;
    }

}
