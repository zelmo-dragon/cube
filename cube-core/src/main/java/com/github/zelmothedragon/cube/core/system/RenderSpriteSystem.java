package com.github.zelmothedragon.cube.core.system;

import com.github.zelmothedragon.cube.core.GameContainer;
import com.github.zelmothedragon.cube.core.entity.geometry.Orientation;
import com.github.zelmothedragon.cube.core.entity.geometry.Rectangle;
import com.github.zelmothedragon.cube.core.entity.geometry.Vector;
import com.github.zelmothedragon.cube.core.graphic.AnimatedSprite;
import com.github.zelmothedragon.cube.core.graphic.AnimatedSpriteMetaData;
import com.github.zelmothedragon.cube.core.graphic.Render;
import com.github.zelmothedragon.cube.core.graphic.Sprite;
import java.util.Objects;
import java.util.UUID;

/**
 * Système de rendu graphique pour les images.
 *
 * @author MOSELLE Maxime
 */
public final class RenderSpriteSystem extends AbstractSystem {

    /**
     * Constructeur. Construit un système de rendu graphique
     *
     * @param gc Conteneur du jeu
     * @param priority Priorité d'exécution
     */
    public RenderSpriteSystem(final GameContainer gc, final int priority) {
        super(gc, priority);
        gc.getFactory().createDebugPlayer();
    }

    @Override
    public void update() {
        gc
                .getEntities()
                .get(AnimatedSprite.class)
                .forEach(this::doSpriteAnimation);

        gc
                .getEntities()
                .get(AnimatedSpriteMetaData.class)
                .forEach(this::doSpriteOrientation);
    }

    @Override
    public void draw(final Render g2d) {
        gc
                .getEntities()
                .get(Sprite.class)
                .forEach((k, s) -> doRenderSprite(k, s, g2d));

        gc
                .getEntities()
                .get(AnimatedSprite.class)
                .forEach((k, s) -> doRenderAnimatedSprite(k, s, g2d));
    }

    private void doSpriteAnimation(
            final UUID id,
            final AnimatedSprite sprite) {

        var metaData = gc
                .getEntities()
                .get(id, AnimatedSpriteMetaData.class);

        var orientation = metaData.getOrientation();
        if (Objects.equals(orientation, Orientation.EMPTY)) {
            sprite.stop();
        } else {
            var offset = metaData.getCurrentOffset();
            sprite.setOffset(offset.getXp(), offset.getYp());
            sprite.play();
        }
        sprite.update();
    }

    private void doSpriteOrientation(
            final UUID id,
            final AnimatedSpriteMetaData metaData) {

        var vector = gc
                .getEntities()
                .get(id, Vector.class);

        if (vector.getDx() < 0 && vector.getDy() == 0) {
            metaData.setOrientation(Orientation.LEFT);
        } else if (vector.getDx() > 0 && vector.getDy() == 0) {
            metaData.setOrientation(Orientation.RIGHT);
        } else if (vector.getDx() == 0 && vector.getDy() < 0) {
            metaData.setOrientation(Orientation.UP);
        } else if (vector.getDx() == 0 && vector.getDy() > 0) {
            metaData.setOrientation(Orientation.DOWN);
        } else {
            metaData.setOrientation(Orientation.EMPTY);
        }
    }

    private void doRenderSprite(
            final UUID id,
            final Sprite sprite,
            final Render g2d) {

        var rectangle = gc
                .getEntities()
                .get(id, Rectangle.class);

        g2d.drawImage(
                rectangle.getXp(),
                rectangle.getYp(),
                sprite
        );

    }

    private void doRenderAnimatedSprite(
            final UUID id,
            final AnimatedSprite sprite,
            final Render g2d) {

        var rectangle = gc
                .getEntities()
                .get(id, Rectangle.class);

        g2d.drawImage(
                rectangle.getXp(),
                rectangle.getYp(),
                sprite.getCurrentSprite()
        );
    }

}
