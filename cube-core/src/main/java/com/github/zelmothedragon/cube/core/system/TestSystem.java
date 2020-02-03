package com.github.zelmothedragon.cube.core.system;

import com.github.zelmothedragon.cube.core.GameContainer;
import com.github.zelmothedragon.cube.core.asset.AssetManager;
import com.github.zelmothedragon.cube.core.entity.geometry.Orientation;
import com.github.zelmothedragon.cube.core.graphic.AnimatedSpriteMetaData;
import com.github.zelmothedragon.cube.core.graphic.Render;
import com.github.zelmothedragon.cube.core.graphic.Sprite;
import com.github.zelmothedragon.cube.core.graphic.TileMap;
import com.github.zelmothedragon.cube.core.input.GamePad;
import java.util.UUID;

/**
 * Système de test.
 *
 * @author MOSELLE Maxime
 */
public final class TestSystem extends AbstractSystem {

    private final UUID player;

    private final Sprite background;

    private final Sprite wood;

    private final TileMap tileMap;

    public TestSystem(final GameContainer gc, final int priority) {
        super(gc, priority);

        this.background = gc.getAssets().loadSprite(AssetManager.DEBUG_BACKGROUND_IMAGE);
        this.wood = gc.getAssets().loadSprite(AssetManager.DEBUG_WOOD_IMAGE);
        this.player = gc.getFactory().createDebugPlayer();

        this.tileMap = new TileMap(
                gc.getAssets().loadSprite("/assets/images/overworld.png"),
                16,
                16,
                gc.getAssets().loadMap("/assets/maps/debug.csv")
        );
    }

    @Override
    public void update() {

        var playerMetaData = gc
                .getEntities()
                .get(player, AnimatedSpriteMetaData.class);

        if (gc.getInputs().isKeyUp(GamePad.LEFT)) {
            playerMetaData.setOrientation(Orientation.LEFT);
        } else if (gc.getInputs().isKeyUp(GamePad.RIGHT)) {
            playerMetaData.setOrientation(Orientation.RIGHT);
        } else if (gc.getInputs().isKeyUp(GamePad.UP)) {
            playerMetaData.setOrientation(Orientation.UP);
        } else if (gc.getInputs().isKeyUp(GamePad.DOWN)) {
            playerMetaData.setOrientation(Orientation.DOWN);
        } else {
            playerMetaData.setOrientation(Orientation.EMPTY);
        }
    }

    @Override
    public void draw(final Render g2d) {
        g2d.drawImage(
                0,
                0,
                tileMap
        );

        g2d.drawImage(64, 64, wood);

        g2d.drawRect(0, 0, 64, 64, 0xFFFF0000);
        g2d.drawGradientCircle(0, 0, 32, 0xFFFF0000);

        g2d.drawRect(64, 0, 64, 64, 0xFF00FF00);
        g2d.drawGradientCircle(64, 0, 32, 0xFF00FF00);

        g2d.drawRect(128, 0, 64, 64, 0xFF0000FF);
        g2d.drawGradientCircle(128, 0, 32, 0xFF0000FF);

        g2d.drawRect(192, 0, 64, 64, 0xFFFFFFFF);
        g2d.drawGradientCircle(192, 0, 32, 0xFFFFFFFF);

        g2d.drawRect(256, 0, 64, 64, 0xFF000000);
        g2d.drawGradientCircle(256, 0, 32, 0xFF000000);

        g2d.drawRect(
                gc.getInputs().getCursorX(),
                gc.getInputs().getCursorY(),
                5,
                5,
                0xFFFF0000
        );

    }

}
