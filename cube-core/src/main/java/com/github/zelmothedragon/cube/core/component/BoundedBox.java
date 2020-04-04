package com.github.zelmothedragon.cube.core.component;

import com.github.zelmothedragon.cube.core.util.geometry.Dimension;
import com.github.zelmothedragon.cube.core.util.geometry.Point;
import com.github.zelmothedragon.cube.core.util.geometry.Rectangle;

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
     * Nature de la collision.
     */
    private Block block;

    /**
     * Constructeur. Construit une zone de délimitation d'une entité pour la
     * gestion des collisions.
     *
     * @param bound Zone de délimitation d'une entité
     * @param block Nature de la collision
     */
    public BoundedBox(final Rectangle bound, final Block block) {

        this.bound = bound;
        this.block = block;
    }

    public BoundedBox(final Dimension bound) {
        this.bound = new Rectangle(0, 0, bound.getWidth(), bound.getHeight());
        this.block = Block.VOID;
    }

    public BoundedBox(final Point bound) {
        this.bound = new Rectangle(bound.getXp(), bound.getYp(), 0, 0);
        this.block = Block.VOID;
    }

    public BoundedBox() {
        this.bound = new Rectangle(0, 0, 0, 0);
        this.block = Block.VOID;
    }

    public Rectangle getBound() {
        return bound;
    }

    public Block getBlock() {
        return block;
    }

    public void setBlock(Block block) {
        this.block = block;
    }

}
