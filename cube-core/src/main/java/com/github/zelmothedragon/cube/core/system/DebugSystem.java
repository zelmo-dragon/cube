package com.github.zelmothedragon.cube.core.system;

import com.github.zelmothedragon.cube.core.GameContainer;
import com.github.zelmothedragon.cube.core.asset.AssetManager;
import com.github.zelmothedragon.cube.core.graphic.AnimatedSprite;
import com.github.zelmothedragon.cube.core.graphic.FontSprite;
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
    
    private final AnimatedSprite player;
    
    private final FontSprite font;
    
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
        this.player = new AnimatedSprite(
                gc.getAssets().loadSprite(AssetManager.DEBUG_PLAYER_IMAGE),
                50,
                4,
                16,
                32
        );
        
        this.font = new FontSprite(
                gc.getAssets().loadSprite(AssetManager.DEBUG_8X8_TEXT_SHADOW),
                gc.getAssets().LoadFontMap(AssetManager.DEBUG_8X8_TEXT_MAP),
                8,
                8
        );
    }
    
    @Override
    public void update() {
        ups++;
        
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
            ups = 0;
            fps = 0;
            timer += 1000;
        }
    }
    
    @Override
    public void draw(final Render g2d) {
        fps++;
        g2d.drawImage(0, 0, background);
        g2d.drawImage(64, 64, wood);
        g2d.drawImage(xp, yp, player.getCurrentSprite());
        g2d.drawGradientCircle(0, 0, 128, 0xFFFF0000);
        g2d.drawImage(0, 0, font, message);
        
    }
    
}
