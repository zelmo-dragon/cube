package com.github.zelmothedragon.cube.core.graphic;

import com.github.zelmothedragon.cube.core.entity.geometry.Dimension;
import com.github.zelmothedragon.cube.core.util.lang.ToString;
import java.util.HashMap;
import java.util.Map;

/**
 * Police de charactère basée sur une image sous forme de tableau de pixel.
 *
 * @author MOSELLE Maxime
 */
public final class FontSprite extends Sprite {

    /**
     * Séparateur de caractères dans les fichiers de cartographie de police de
     * caractères.
     */
    public static final String CHAR_SEPARATOR = "";

    /**
     * Séparateur de lignes dans les fichiers de cartographie de police de
     * caractères.
     */
    public static final String LINE_SEPARATOR = "\n";

    /**
     * Valeur si charactère vide.
     */
    public static final int EMPTY_CHARACTER = -1;

    /**
     * Mémoire cache contenant chaque image de caractères.
     */
    private final Map<Integer, Sprite> cache;

    /**
     * Dimension d'un caractère.
     */
    private final Dimension characterSize;

    /**
     * Cartographie de la police de caractères.
     */
    private final String fontMap;

    /**
     * Constructeur. Constuit une police de charactères depuis une image et une
     * carte.
     *
     * @param fontSpriteSheet Image contenant tous les caractères.
     * @param characterSize Dimension d'un caractère
     * @param fontMap Cartographie de la police de caractères
     */
    public FontSprite(
            final Sprite fontSpriteSheet,
            final Dimension characterSize,
            final String fontMap) {

        super(fontSpriteSheet.rectangle.getDimension(), fontSpriteSheet.buffer);
        this.cache = new HashMap<>();
        this.characterSize = characterSize;
        this.fontMap = fontMap;
    }

    @Override
    public String toString() {
        return ToString
                .with("characterSize", FontSprite::getCharacterSize)
                .thenWith("buffer.length", FontSprite::getBufferLength)
                .apply(this);
    }

    /**
     * Récupérer une image en fonction d'un caractère.
     *
     * @param character Le charactère
     * @return L'image du charactère
     */
    public Sprite getCharacter(final String character) {

        Sprite font;
        var index = fontMap.indexOf(character);
        if (cache.containsKey(index)) {
            font = cache.get(index);
        } else {
            font = new Sprite(characterSize);
            if (index != EMPTY_CHARACTER) {
                var columns = rectangle.getDimension().getWidth() / characterSize.getWidth();
                var xp = (index % columns) * characterSize.getWidth();
                var yp = (index / columns) * characterSize.getHeight();

                for (var y = 0; y < characterSize.getHeight(); y++) {
                    var ya = yp + y;

                    for (var x = 0; x < characterSize.getWidth(); x++) {
                        var xa = xp + x;
                        var pixel = getPixel(xa, ya);
                        font.setPixel(x, y, pixel);
                    }
                }
            }
            cache.put(index, font);
        }
        return font;
    }

    /**
     * Accesseur, obtenir la dimension d'un caractère.
     *
     * @return La dimension
     */
    public Dimension getCharacterSize() {
        return characterSize;
    }

}
