package com.github.zelmothedragon.cube.core.entity.geometry;

import com.github.zelmothedragon.cube.core.entity.Component;
import com.github.zelmothedragon.cube.core.util.lang.Equal;
import com.github.zelmothedragon.cube.core.util.lang.ToString;
import java.util.Objects;

/**
 * Dimension 2D.
 *
 * @author MOSELLE Maxime
 */
public final class Dimension implements Component {

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
    public Dimension() {
        this.width = 0;
        this.height = 0;
    }

    /**
     * Constructeur. Construit une dimension.
     *
     * @param width Largeur
     * @param height Hauteur
     */
    public Dimension(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public int hashCode() {
        return Objects.hash(width, height);
    }

    @Override
    public boolean equals(final Object obj) {
        return Equal
                .with(Dimension::getWidth)
                .thenWith(Dimension::getHeight)
                .apply(this, obj);
    }

    @Override
    public String toString() {
        return ToString
                .with("width", Dimension::getWidth)
                .thenWith("height", Dimension::getHeight)
                .apply(this);
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
