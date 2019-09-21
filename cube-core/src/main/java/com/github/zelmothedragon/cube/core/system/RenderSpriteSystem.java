package com.github.zelmothedragon.cube.core.system;

import com.github.zelmothedragon.cube.core.GameContainer;
import com.github.zelmothedragon.cube.core.entity.geometry.Point;
import com.github.zelmothedragon.cube.core.entity.geometry.Rectangle;
import com.github.zelmothedragon.cube.core.graphic.AnimatedSprite;
import com.github.zelmothedragon.cube.core.graphic.Render;
import com.github.zelmothedragon.cube.core.graphic.Sprite;

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
                .getComponent(AnimatedSprite.class)
                .forEach((k, s) -> s.update());

    }

    @Override
    public void draw(final Render g2d) {

        gc
                .getEntities()
                .getComponent(Sprite.class)
                .forEach((k, s) -> {

                    var point = gc
                            .getEntities()
                            .getComponent(k, Point.class);

                    g2d.drawImage(
                            point.getXp(),
                            point.getYp(),
                            s
                    );

                    var rectangle = gc
                            .getEntities()
                            .getComponent(k, Rectangle.class);

                    g2d.drawImage(
                            rectangle.getXp(),
                            rectangle.getYp(),
                            s
                    );

                });

    }

}
