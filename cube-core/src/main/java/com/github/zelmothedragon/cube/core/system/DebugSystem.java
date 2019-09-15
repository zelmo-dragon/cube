package com.github.zelmothedragon.cube.core.system;

import com.github.zelmothedragon.cube.core.GameContainer;
import com.github.zelmothedragon.cube.core.asset.AssetManager;
import com.github.zelmothedragon.cube.core.entity.debug.Clock;
import com.github.zelmothedragon.cube.core.graphic.AnimatedSprite;
import com.github.zelmothedragon.cube.core.graphic.FontSprite;
import com.github.zelmothedragon.cube.core.graphic.Pixel;
import com.github.zelmothedragon.cube.core.graphic.Render;
import com.github.zelmothedragon.cube.core.graphic.Sprite;
import com.github.zelmothedragon.cube.core.input.GamePad;
import java.util.UUID;

/**
 * Système de déboggage.
 *
 * @author MOSELLE Maxime
 */
public final class DebugSystem extends AbstractSystem {

    private final UUID debug;

    private final Sprite background;

    private final Sprite wood;

    private final AnimatedSprite player;

    private int xp;

    private int yp;

    public DebugSystem(final GameContainer gc, final int priority) {
        super(gc, priority);

        this.xp = 0;
        this.yp = 0;
        this.background = gc.getAssets().loadSprite(AssetManager.DEBUG_BACKGROUND_IMAGE);
        this.wood = gc.getAssets().loadSprite(AssetManager.DEBUG_WOOD_IMAGE);
        this.player = new AnimatedSprite(
                gc.getAssets().loadSprite(AssetManager.DEBUG_PLAYER_IMAGE),
                50,
                4,
                16,
                32
        );

        this.debug = gc.getFactory().createDebugInformation();
    }

    @Override
    public void update() {

        if (gc.getInputs().isKeyUp(GamePad.LEFT)) {
            xp--;
            player.setOffset(0, 96);
            player.play();
        } else if (gc.getInputs().isKeyUp(GamePad.RIGHT)) {
            xp++;
            player.setOffset(0, 32);
            player.play();
        } else if (gc.getInputs().isKeyUp(GamePad.UP)) {
            yp--;
            player.setOffset(0, 64);
            player.play();
        } else if (gc.getInputs().isKeyUp(GamePad.DOWN)) {
            yp++;
            player.setOffset(0, 0);
            player.play();
        } else {
            player.stop();
        }

        player.update(1.0);

        var clock = gc.getEntities().getComponent(debug, Clock.class);
        clock.update();
    }

    @Override
    public void draw(final Render g2d) {

        g2d.drawImage(0, 0, background);
        g2d.drawImage(64, 64, wood);

        var playerSprite = Pixel.scale(player.getCurrentSprite(), 3);
        g2d.drawImage(xp, yp, playerSprite);

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

        var clock = gc.getEntities().getComponent(debug, Clock.class);
        var font = gc.getEntities().getComponent(debug, FontSprite.class);
        clock.render();

        g2d.drawImage(0, 0, font, clock.getMessage());

    }

}
