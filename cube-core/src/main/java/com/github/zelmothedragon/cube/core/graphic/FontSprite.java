package com.github.zelmothedragon.cube.core.graphic;

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
        this.fontMap = fontMap;
        this.characterWidth = characterWidth;
        this.characterHeight = characterHeight;
    }

    /**
     * Récupérer une image en fonction d'un caractère.
     *
     * @param character Le charactère
     * @return L'image du charactère
     */
    public Sprite getCharacter(final String character) {

        var font = new Sprite(characterWidth, characterHeight);
        var index = fontMap.indexOf(character);
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
