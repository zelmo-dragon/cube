package com.github.zelmothedragon.cube.core.model;

import com.github.zelmothedragon.cube.core.util.geometry.Dimension;
import com.github.zelmothedragon.cube.core.util.geometry.Point;
import com.github.zelmothedragon.cube.core.util.geometry.Rectangle;
import com.github.zelmothedragon.cube.core.util.lang.Equal;
import com.github.zelmothedragon.cube.core.util.lang.ToString;
import java.util.Objects;

/**
 * Rectangle 2D de délimitation d'une entité.
 *
 * @author MOSELLE Maxime
 */
public class BoundedBox implements Component {

    /**
     * Zone de délimitation d'une entité.
     */
    private final Rectangle bound;

    /**
     * Zone de collision.
     */
    private final Rectangle collision;

    /**
     * Nature de la collision.
     */
    private Block block;

    /**
     * Constructeur.Construit une zone de délimitation d'une entité pour la
     * gestion des collisions.
     *
     * @param bound Zone de délimitation d'une entité
     * @param collision Zone de collision
     * @param block Nature de la collision
     */
    public BoundedBox(
            final Rectangle bound,
            final Rectangle collision,
            final Block block) {

        this.bound = bound;
        this.collision = collision;
        this.block = block;
    }

    /**
     * Constructeur.Construit une zone de délimitation d'une entité pour la
     * gestion des collisions.
     *
     * @param bound Zone de délimitation d'une entité
     */
    public BoundedBox(final Dimension bound) {
        this.bound = new Rectangle(0, 0, bound.getWidth(), bound.getHeight());
        this.collision = new Rectangle(0, 0, bound.getWidth(), bound.getHeight());
        this.block = Block.VOID;
    }

    /**
     * Constructeur.Construit une zone de délimitation d'une entité pour la
     * gestion des collisions.
     *
     * @param bound Zone de délimitation d'une entité
     */
    public BoundedBox(final Point bound) {
        this.bound = new Rectangle(bound.getXp(), bound.getYp(), 0, 0);
        this.collision = new Rectangle(bound.getXp(), bound.getYp(), 0, 0);
        this.block = Block.VOID;
    }

    /**
     * Constructeur.Construit une zone de délimitation d'une entité pour la
     * gestion des collisions.
     *
     */
    public BoundedBox() {
        this.bound = new Rectangle(0, 0, 0, 0);
        this.collision = new Rectangle(0, 0, 0, 0);
        this.block = Block.VOID;
    }

    @Override
    public int hashCode() {
        return Objects.hash(bound, collision, block);
    }

    @Override
    public boolean equals(final Object obj) {
        return Equal
                .with(BoundedBox::getBound)
                .thenWith(BoundedBox::getCollision)
                .thenWith(BoundedBox::getBlock)
                .apply(this, obj);
    }

    @Override
    public String toString() {
        return ToString
                .with("bound", BoundedBox::getBound)
                .thenWith("collision", BoundedBox::getCollision)
                .thenWith("block", BoundedBox::getBlock)
                .apply(this);
    }

    /**
     * Déplacer cette zone en fonction d'un vecteur.
     *
     * @param dx Vecteur de déplacement en abcisse
     * @param dy Vecteur de déplacement en ordonnée
     */
    public void move(final int dx, final int dy) {
        this.bound.move(dx, dy);
        this.collision.move(dx, dy);
    }

    /**
     * Accesseur, obtenir la nature de la zone de collision.
     *
     * @return Le type de bloc
     */
    public Block getBlock() {
        return block;
    }

    /**
     * Muttateur, modifier la nature de la zone de collision.
     *
     * @param block Le type de bloc
     */
    public void setBlock(Block block) {
        this.block = block;
    }

    /**
     * Accesseur, obtenir la zone de délimitation.
     *
     * @return Un rectangle représentant la zone de délimitation
     */
    public Rectangle getBound() {
        return bound;
    }

    /**
     * Accesseur, obtenir la zone de collision.
     *
     * @return Un rectangle représentant la zone de collision
     */
    public Rectangle getCollision() {
        return collision;
    }

}
