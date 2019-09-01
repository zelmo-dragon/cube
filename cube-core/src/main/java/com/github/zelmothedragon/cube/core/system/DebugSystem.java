package com.github.zelmothedragon.cube.core.system;

import com.github.zelmothedragon.cube.core.GameContainer;
import com.github.zelmothedragon.cube.core.asset.AssetManager;
import com.github.zelmothedragon.cube.core.graphic.Render;
import com.github.zelmothedragon.cube.core.graphic.Sprite;
import com.github.zelmothedragon.cube.core.input.GamePad;

/**
 * Système de déboggage.
 *
 * @author MOSELLE Maxime
 */
public final class DebugSystem extends AbstractSystem {

    /**
     * Conversion de la taille de la mémoire en Méga octet.
     */
    private static final int MEGA_BYTE_UNIT = 1024 * 1024;

    /**
     * Nombre de mise à jour par seconde.
     */
    private int ups;

    /**
     * Nombre d'image à la seconde.
     */
    private int fps;

    /**
     * Horlogue interne.
     */
    private long timer;

    /**
     * Message de déboggage.
     */
    private String message;

    private final Sprite background;

    private final Sprite wood;

    private int xp;

    private int yp;

    public DebugSystem(final GameContainer gc, final int priority) {
        super(gc, priority);
        this.ups = 0;
        this.fps = 0;
        this.timer = System.currentTimeMillis();
        this.message = "loading...";

        this.xp = 0;
        this.yp = 0;
        this.background = gc.getAssets().loadSprite(AssetManager.DEBUG_BACKGROUND_IMAGE);
        this.wood = gc.getAssets().loadSprite(AssetManager.DEBUG_WOOD_IMAGE);
    }

    @Override
    public void update() {
        ups++;

        if (gc.getInputs().isKeyUp(GamePad.UP)) {
            yp--;
        }
        if (gc.getInputs().isKeyUp(GamePad.DOWN)) {
            yp++;
        }
        if (gc.getInputs().isKeyUp(GamePad.LEFT)) {
            xp--;
        }
        if (gc.getInputs().isKeyUp(GamePad.RIGHT)) {
            xp++;
        }

        if (System.currentTimeMillis() - timer >= 1000) {
            var rt = Runtime.getRuntime();
            var totalMem = rt.totalMemory() / MEGA_BYTE_UNIT;
            var usedMem = totalMem - rt.freeMemory() / MEGA_BYTE_UNIT;
            message = String.format(
                    "UPS:%s, FPS:%s, MEM:%s/%s MB",
                    ups,
                    fps,
                    usedMem,
                    totalMem
            );

            System.out.println(message);
            ups = 0;
            fps = 0;
            timer += 1000;
        }
    }

    @Override
    public void draw(final Render g2d) {
        fps++;
        var time = System.nanoTime();

        g2d.drawImage(0, 0, background);
        g2d.drawImage(64, 64, wood);
        g2d.drawGradientCircle(xp, yp, 64, 0x00FF0000);
        g2d.drawRect(xp, yp, 128, 128, 0xFFFF0000);

        time = System.nanoTime() - time;
        System.out.printf("TIME: %s ns%n", time);
    }

}
