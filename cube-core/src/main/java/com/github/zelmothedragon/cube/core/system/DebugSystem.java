package com.github.zelmothedragon.cube.core.system;

import com.github.zelmothedragon.cube.core.GameContainer;
import com.github.zelmothedragon.cube.core.asset.AssetManager;
import com.github.zelmothedragon.cube.core.entity.debug.Clock;
import com.github.zelmothedragon.cube.core.entity.geometry.Orientation;
import com.github.zelmothedragon.cube.core.entity.geometry.Rectangle;
import com.github.zelmothedragon.cube.core.graphic.AnimatedSpriteMetaData;
import com.github.zelmothedragon.cube.core.graphic.FontSprite;
import com.github.zelmothedragon.cube.core.graphic.Render;
import com.github.zelmothedragon.cube.core.graphic.Sprite;
import com.github.zelmothedragon.cube.core.graphic.TileMap;
import com.github.zelmothedragon.cube.core.input.GamePad;
import java.util.UUID;

/**
 * Système de déboggage.
 *
 * @author MOSELLE Maxime
 */
public final class DebugSystem extends AbstractSystem {

    private final UUID debug;

    private final UUID player;

    private final Sprite background;

    private final Sprite wood;

    private final TileMap tileMap;

    public DebugSystem(final GameContainer gc, final int priority) {
        super(gc, priority);

        this.background = gc.getAssets().loadSprite(AssetManager.DEBUG_BACKGROUND_IMAGE);
        this.wood = gc.getAssets().loadSprite(AssetManager.DEBUG_WOOD_IMAGE);

        this.debug = gc.getFactory().createDebugInformation();
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

        var debugClock = gc.getEntities().get(debug, Clock.class);
        debugClock.update();
    }

    @Override
    public void draw(final Render g2d) {
        var debugRect = gc.getEntities().get(debug, Rectangle.class);
        var debugFont = gc.getEntities().get(debug, FontSprite.class);
        var debugClock = gc.getEntities().get(debug, Clock.class);

        g2d.drawImage(
                debugRect.getXp(),
                debugRect.getYp(),
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

        for (var i = 0; i < 50; i++) {
            g2d.drawLine(
                    50 + i,
                    0,
                    gc.getInputs().getCursorX(),
                    gc.getInputs().getCursorY(),
                    0xFFFF00FF
            );

            g2d.drawLine(
                    0,
                    50 + i,
                    gc.getInputs().getCursorX(),
                    gc.getInputs().getCursorY(),
                    0xFFFFFF00
            );
        }

        g2d.drawRect(
                gc.getInputs().getCursorX(),
                gc.getInputs().getCursorY(),
                5,
                5,
                0xFFFF0000
        );

        debugClock.render();

        g2d.drawImage(
                debugRect.getXp(),
                debugRect.getYp(),
                debugFont,
                debugClock.getMessage()
        );

    }

}
