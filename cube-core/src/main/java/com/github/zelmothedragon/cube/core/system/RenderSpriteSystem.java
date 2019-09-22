package com.github.zelmothedragon.cube.core.system;

import com.github.zelmothedragon.cube.core.GameContainer;
import com.github.zelmothedragon.cube.core.entity.geometry.Orientation;
import com.github.zelmothedragon.cube.core.entity.geometry.Rectangle;
import com.github.zelmothedragon.cube.core.graphic.AnimatedSprite;
import com.github.zelmothedragon.cube.core.graphic.AnimatedSpriteMetaData;
import com.github.zelmothedragon.cube.core.graphic.Render;
import com.github.zelmothedragon.cube.core.graphic.Sprite;
import java.util.Objects;

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
    public RenderSpriteSystem(GameContainer gc, int priority) {
        super(gc, priority);
    }

    @Override
    public void update() {
        gc
                .getEntities()
                .get(AnimatedSprite.class)
                .forEach((k, s) -> {

                    var metaData = gc
                            .getEntities()
                            .get(k, AnimatedSpriteMetaData.class);

                    var offset = metaData.getCurrentOffset();
                    var orientation = metaData.getOrientation();

                    if (Objects.equals(orientation, Orientation.EMPTY)) {
                        s.stop();
                    } else {
                        s.setOffset(offset.getXp(), offset.getYp());
                        s.play();
                    }
                    s.update();
                });

    }

    @Override
    public void draw(final Render g2d) {
        gc
                .getEntities()
                .get(Sprite.class)
                .forEach((k, s) -> {
                    var rectangle = gc
                            .getEntities()
                            .get(k, Rectangle.class);

                    g2d.drawImage(
                            rectangle.getXp(),
                            rectangle.getYp(),
                            s
                    );

                });

        gc
                .getEntities()
                .get(AnimatedSprite.class)
                .forEach((k, s) -> {
                    var rectangle = gc
                            .getEntities()
                            .get(k, Rectangle.class);

                    g2d.drawImage(
                            rectangle.getXp(),
                            rectangle.getYp(),
                            s.getCurrentSprite()
                    );
                });
    }

}
