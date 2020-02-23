package com.github.zelmothedragon.cube.core.entity.geometry;

import com.github.zelmothedragon.cube.core.entity.Component;
import com.github.zelmothedragon.cube.core.util.lang.Equal;
import com.github.zelmothedragon.cube.core.util.lang.ToString;
import java.util.Objects;

/**
 * Rectangle 2D.
 *
 * @author MOSELLE Maxime
 */
public final class Rectangle implements Component {

    /**
     * Position.
     */
    private final Point point;

    /**
     * Dimension.
     */
    private final Dimension dimension;

    /**
     * Constructeur par défaut.
     */
    public Rectangle() {
        this.point = new Point();
        this.dimension = new Dimension();
    }

    /**
     * Constructeur. Construit un rectangle avec une taille.
     *
     * @param dimension Dimension
     */
    public Rectangle(final Dimension dimension) {
        this.point = new Point();
        this.dimension = dimension;
    }

    /**
     * Constructeur. Construit un rectangle avec une position et une taille.
     *
     * @param point Position
     * @param dimension Dimension
     */
    public Rectangle(
            final Point point,
            final Dimension dimension) {

        this.point = point;
        this.dimension = dimension;
    }

    @Override
    public int hashCode() {
        return Objects.hash(point, dimension);
    }

    @Override
    public boolean equals(final Object obj) {
        return Equal
                .with(Rectangle::getPoint)
                .thenWith(Rectangle::getDimension)
                .apply(this, obj);
    }

    @Override
    public String toString() {
        return ToString
                .with("point", Rectangle::getPoint)
                .thenWith("dimension", Rectangle::getDimension)
                .apply(this);
    }

    /**
     * Déplacer un rectangle en fonction d'un vecteur.
     *
     * @param vector Vecteur de déplacement
     */
    public void move(final Vector vector) {
        this.point.move(vector);
    }

    /**
     * Vérifier si un point est contenu dans ce rectangle.
     *
     * @param other Un point quelconque
     * @return La valeur <code>true</code> si le point est contenu dans le
     * rectangle, sinon la valeur <code>false</code>
     */
    public boolean contains(final Point other) {
        return other.getXp() >= point.getXp()
                && other.getXp() <= point.getXp() + dimension.getWidth()
                && other.getYp() >= point.getYp()
                && other.getYp() <= point.getYp() + dimension.getHeight();
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
            contain = other.getPoint().getXp() >= point.getXp()
                    && other.getPoint().getYp() >= point.getYp()
                    && other.getPoint().getXp() + other.getDimension().getWidth() <= point.getXp() + dimension.getWidth()
                    && other.getPoint().getYp() + other.getDimension().getHeight() <= point.getYp() + dimension.getHeight();
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
            intersect = other.getPoint().getXp() + other.getDimension().getWidth() > point.getXp()
                    && other.getPoint().getYp() + other.getDimension().getHeight() > point.getYp()
                    && other.getPoint().getXp() < point.getXp() + dimension.getWidth()
                    && other.getPoint().getYp() < point.getYp() + dimension.getHeight();
        }
        return intersect;
    }

    /**
     * Obtenir la position de ce rectangle.
     *
     * @return La position
     */
    public Point getPoint() {
        return point;
    }

    /**
     * Obtenir la dimension de ce rectangle.
     *
     * @return La dimension
     */
    public Dimension getDimension() {
        return dimension;
    }

}
