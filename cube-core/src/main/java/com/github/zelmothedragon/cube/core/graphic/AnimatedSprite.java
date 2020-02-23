package com.github.zelmothedragon.cube.core.graphic;

import com.github.zelmothedragon.cube.core.entity.geometry.Dimension;
import com.github.zelmothedragon.cube.core.entity.geometry.Point;
import com.github.zelmothedragon.cube.core.util.lang.ToString;
import java.util.HashMap;
import java.util.Map;

/**
 * Image animée sous forme de tableau de pixel.
 *
 * @author MOSELLE Maxime
 */
public final class AnimatedSprite extends Sprite {

    /**
     * Mémoire cache contenant chaque image de l'animation.
     */
    private final Map<Integer, Sprite> cache;

    /**
     * Dimension en pixel d'une image composant l'animation.
     */
    private final Dimension spriteSize;

    /**
     * Décalage. Ce décalage indique à partir d'où l'image initiale doit être lu
     * depuis la feuille d'image.
     */
    private final Point offset;

    /**
     * Delais de l'animation.
     */
    private final int duration;

    /**
     * Nombre d'image pour une animation complète.
     */
    private final int count;

    /**
     * Index de l'image courante dans l'animation.
     */
    private int frame;

    /**
     * Identifiant technique de la position de l'image depuis la feuille de
     * d'image.
     */
    private int currentId;

    /**
     * Indique si l'animastion est en cours d'exécution.
     */
    private boolean playing;

    /**
     * Constructeur. Construit une image animée.
     *
     * @param spriteSheet Feuille d'image contenant toutes les animations
     * @param duration Delais de l'animation
     * @param count Nombre d'image composant une animation
     * @param spriteSize Dimension en pixel d'une image composant l'animation
     */
    public AnimatedSprite(
            final Sprite spriteSheet,
            final Dimension spriteSize,
            final int duration,
            final int count) {

        super(
                spriteSheet.getRectangle().getDimension(),
                spriteSheet.buffer
        );

        this.cache = new HashMap<>();
        this.spriteSize = spriteSize;
        this.offset = new Point();
        this.duration = duration;
        this.count = count;
        this.frame = 0;
        this.currentId = -1;
        this.playing = false;

    }

    @Override
    public String toString() {
        return ToString
                .with("rectangle", AnimatedSprite::getRectangle)
                .thenWith("buffer.length", AnimatedSprite::getBufferLength)
                .thenWith("spriteSize", e -> e.spriteSize)
                .thenWith("offset", e -> e.offset)
                .thenWith("duration", e -> e.duration)
                .thenWith("count", e -> e.count)
                .thenWith("frame", e -> e.frame)
                .thenWith("playing", e -> e.playing)
                .apply(this);
    }

    /**
     * Mettre à jour l'animation.
     */
    public void update() {

        // Réinitialiser l'index de l'animation lorsque qu'un cycle complet
        // de l'animation est exécuter.
        if (playing) {
            frame++;
            if (frame > duration) {
                frame = 0;
            }
        }

        // Calculer l'index et la position de la prochaine image.
        var frac = frame * count / duration;
        var index = Math.min((int) Math.floor(frac), count - 1);
        var columns = rectangle.getDimension().getWidth() / spriteSize.getWidth();
        var xp = (index % columns) * spriteSize.getWidth() + offset.getXp();
        var yp = (index / columns) * spriteSize.getHeight() + offset.getYp();

        currentId = xp + yp * count;
        if (!cache.containsKey(currentId)) {

            // Extraire de la feuille d'image
            // l'image de l'animation courante.
            var sprite = new Sprite(spriteSize);
            for (var y = 0; y < spriteSize.getHeight(); y++) {
                var ya = yp + y;

                for (var x = 0; x < spriteSize.getWidth(); x++) {
                    var xa = xp + x;
                    var pixel = getPixel(xa, ya);
                    sprite.setPixel(x, y, pixel);
                }
            }
            cache.put(currentId, sprite);
        }
    }

    /**
     * Lancer l'animation.
     */
    public void play() {
        playing = true;
    }

    /**
     * Mettre en pause l'animation.
     */
    public void pause() {
        playing = false;
    }

    /**
     * Arrêter l'animation.
     */
    public void stop() {
        playing = false;
        frame = 0;
    }

    /**
     * Changer le décalage.
     *
     * @param offset Décalage
     */
    public void setOffset(final Point offset) {
        this.offset.setPont(offset);
    }

    /**
     * Accesseur, obtenir l'image de l'animation courante.
     *
     * @return L'image de l'animation courante
     */
    public Sprite getCurrentSprite() {
        return cache.get(currentId);
    }

    /**
     * Accesseur, indiquer si l'animation est en cours d'exécution.
     *
     * @return La valeur <code>true</code> si l'animation est en cours, sinon la
     * valeur <code>false</code>
     */
    public boolean isPlaying() {
        return playing;
    }

}
