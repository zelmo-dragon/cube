package com.github.zelmothedragon.cube.core.graphic;

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
     * Cartographie de la police de caractères.
     */
    private final String fontMap;

    /**
     * Largeur d'un charactère en pixel.
     */
    private final int characterWidth;

    /**
     * Hauteur d'un charactère en pixel
     */
    private final int characterHeight;

    /**
     * Constructeur. Constuit une police de charactères depuis une image et une
     * carte.
     *
     * @param fontSpriteSheet Image contenant tous les caractères.
     * @param fontMap Cartographie de la police de caractères
     * @param characterWidth Largeur d'un charactère en pixel
     * @param characterHeight Hauteur d'un charactère en pixel
     */
    public FontSprite(
            final Sprite fontSpriteSheet,
            final String fontMap,
            final int characterWidth,
            final int characterHeight) {

        super(fontSpriteSheet.width, fontSpriteSheet.height, fontSpriteSheet.buffer);
        this.cache = new HashMap<>();
        this.fontMap = fontMap;
        this.characterWidth = characterWidth;
        this.characterHeight = characterHeight;
    }

    @Override
    public String toString() {
        return ToString
                .of(this)
                .with("width", FontSprite::getWidth)
                .with("height", FontSprite::getHeight)
                .with("buffer.length", FontSprite::getBufferLength)
                .with("characterWidth", FontSprite::getCharacterWidth)
                .with("characterHeight", FontSprite::getCharacterHeight)
                .get();
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
            font = new Sprite(characterWidth, characterHeight);
            if (index != EMPTY_CHARACTER) {
                var columns = width / characterWidth;
                var xp = (index % columns) * characterWidth;
                var yp = (index / columns) * characterHeight;

                for (var y = 0; y < characterHeight; y++) {
                    var ya = yp + y;

                    for (var x = 0; x < characterWidth; x++) {
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
     * Accesseur, obtenir la largeur d'un charactère en pixel.
     *
     * @return Largeur d'un charactère en pixel
     */
    public int getCharacterWidth() {
        return characterWidth;
    }

    /**
     * Accesseur, obtenir la hauteur d'un charactère en pixel
     *
     * @return Hauteur d'un charactère en pixel.
     */
    public int getCharacterHeight() {
        return characterHeight;
    }

}
