package com.github.zelmothedragon.cube.core.system;

import com.github.zelmothedragon.cube.core.GameContainer;
import com.github.zelmothedragon.cube.core.entity.geometry.Rectangle;
import com.github.zelmothedragon.cube.core.graphic.AnimatedSprite;
import com.github.zelmothedragon.cube.core.graphic.FontSprite;
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
                .get(AnimatedSprite.class)
                .forEach((k, s) -> s.update());

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
