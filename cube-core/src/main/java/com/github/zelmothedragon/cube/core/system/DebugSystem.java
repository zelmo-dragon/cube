package com.github.zelmothedragon.cube.core.system;

import com.github.zelmothedragon.cube.core.GameContainer;
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

    public DebugSystem(final GameContainer gc, final int priority) {
        super(gc, priority);
        this.ups = 0;
        this.fps = 0;
        this.timer = System.currentTimeMillis();
        this.message = "loading...";
    }

    @Override
    public void update() {
        ups++;

        if (gc.getInputs().isKeyUp(GamePad.UP)) {
            System.out.println("UP");
        }
        if (gc.getInputs().isKeyUp(GamePad.DOWN)) {
            System.out.println("DOWN");
        }
        if (gc.getInputs().isKeyUp(GamePad.LEFT)) {
            System.out.println("LEFT");
        }
        if (gc.getInputs().isKeyUp(GamePad.RIGHT)) {
            System.out.println("RIGHT");
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
    public void draw() {
        fps++;

    }

}
