package com.github.zelmothedragon.cube.core.component;

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

    public BoundedBox(final Dimension bound) {
        this.bound = new Rectangle(0, 0, bound.getWidth(), bound.getHeight());
        this.collision = new Rectangle(0, 0, bound.getWidth(), bound.getHeight());
        this.block = Block.VOID;
    }

    public BoundedBox(final Point bound) {
        this.bound = new Rectangle(bound.getXp(), bound.getYp(), 0, 0);
        this.collision = new Rectangle(bound.getXp(), bound.getYp(), 0, 0);
        this.block = Block.VOID;
    }

    public BoundedBox() {
        this.bound = new Rectangle(0, 0, 0, 0);
        this.collision = new Rectangle(0, 0, 0, 0);
        this.block = Block.VOID;
    }

    @Override
    public int hashCode() {
        return Objects.hash(bound, block);
    }

    @Override
    public boolean equals(final Object obj) {
        return Equal
                .with(BoundedBox::getBound)
                .thenWith(BoundedBox::getBlock)
                .apply(this, obj);
    }

    @Override
    public String toString() {
        return ToString
                .with("bound", BoundedBox::getBound)
                .thenWith("block", BoundedBox::getBlock)
                .apply(this);
    }

    public void move(final int dx, final int dy) {
        this.bound.move(dx, dy);
        this.collision.move(dx, dy);
    }

    public Rectangle getBound() {
        return bound;
    }

    public Rectangle getCollision() {
        return collision;
    }

    public Block getBlock() {
        return block;
    }

    public void setBlock(Block block) {
        this.block = block;
    }

}
