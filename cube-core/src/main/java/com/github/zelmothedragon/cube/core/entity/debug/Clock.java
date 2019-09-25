package com.github.zelmothedragon.cube.core.entity.debug;

import com.github.zelmothedragon.cube.core.entity.Component;
import com.github.zelmothedragon.cube.core.util.lang.Equal;
import com.github.zelmothedragon.cube.core.util.lang.ToString;
import java.util.Objects;

/**
 * Horloge de débogage.
 *
 * @author MOSELLE Maxime
 */
public class Clock implements Component {

    /**
     * Conversion de la taille de la mémoire en Méga octet.
     */
    private static final int MEGA_BYTE_UNIT = 1024 * 1024;

    /**
     * Format du message de débogage.
     */
    private static final String MESSAGE_TEMPLATE = "UPS:%s, FPS:%s, MEM:%s/%s MB";

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

    /**
     * Constructeur par défaut.
     */
    public Clock() {
        this.ups = 0;
        this.fps = 0;
        this.timer = System.currentTimeMillis();
        this.message = "loading...";
    }

    @Override
    public int hashCode() {
        return Objects.hash(message);
    }

    @Override
    public boolean equals(final Object obj) {
        return Equal
                .of(this, obj)
                .with(Clock::getMessage)
                .get();
    }

    @Override
    public String toString() {
        return ToString
                .of(this)
                .with("message", Clock::getMessage)
                .get();
    }

    /**
     * Mettre à jour l'horloge pour la boucle de mise à jour du jeu.
     */
    public void update() {
        ups++;
        if (System.currentTimeMillis() - timer >= 1000) {
            var rt = Runtime.getRuntime();
            var totalMem = rt.totalMemory() / MEGA_BYTE_UNIT;
            var usedMem = totalMem - rt.freeMemory() / MEGA_BYTE_UNIT;
            message = String.format(
                    MESSAGE_TEMPLATE,
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

    /**
     * Mettre à jour l'horloge pour la boucle de rendu du jeu.
     */
    public void render() {
        fps++;
    }

    /**
     * Accesseur, obtenir le message de débogage.
     *
     * @return Le message de débogage
     */
    public String getMessage() {
        return message;
    }

}
