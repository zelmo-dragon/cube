package com.github.zelmothedragon.cube.core.graphic;

import java.util.HashMap;
import java.util.Map;

/**
 * Carte de tuiles graphiques.
 *
 * @author MOSELLE Maxime
 */
public class TileMap extends Sprite {

    /**
     * Sépateur de cellule pour un fichier CSV.
     */
    public static final String CSV_CELL_SEPARATOR = ",";

    /**
     * Retour à la ligne pour un fichier CSV.
     */
    public static final String CSV_LINE_SEPARATOR = "\n";

    /**
     * Index vide, pas de tuile graphique.
     */
    private static final int EMPTY_INDEX = -1;

    /**
     * Mémoire cache contenant chaque tuile graphique de la carte.
     */
    private final Map<Integer, Sprite> cache;

    /**
     * Carte 2D pour désigner l'emplacement des tuiles.
     */
    private final int[][] map;

    /**
     * Largeur en pixel d'une tuile graphique.
     */
    private final int tileWidth;

    /**
     * Hauteur en pixel d'une tuile graphique.
     */
    private final int tileHeight;

    /**
     * Constructeur. Construit une carte de tuile graphique à partir d'une
     * feuille d'image.
     *
     * @param tileSet Feuille d'image contenant toutes les tuiles graphiques
     * @param tileWidth Largeur d'une tuile
     * @param tileHeight Hauteur d'un tuile
     * @param map Carte 2D pour désigner l'emplacement des tuiles
     */
    public TileMap(
            final Sprite tileSet,
            final int tileWidth,
            final int tileHeight,
            final int[][] map) {

        super(tileSet.width, tileSet.height, tileSet.buffer);
        this.cache = new HashMap<>();
        this.tileWidth = tileWidth;
        this.tileHeight = tileHeight;
        this.map = map;
    }

    /**
     * Obtenir la tuile graphique en fonction de coordonnées.
     *
     * @param xIndex Position en abcisse
     * @param yIndex Position en ordonnée
     * @return La tuile pour ces coordonnées, sinon une tuile vide
     */
    public Sprite getTile(final int xIndex, final int yIndex) {

        Sprite tile;
        if (xIndex >= 0
                && xIndex <= getMapWidth()
                && yIndex >= 0
                && yIndex <= getMapHeight()) {

            var index = map[yIndex][xIndex];
            if (cache.containsKey(index)) {
                tile = cache.get(index);
            } else if (index == EMPTY_INDEX) {
                tile = new Sprite(tileWidth, tileHeight);
                cache.put(index, tile);
            } else {
                var colunms = width / tileWidth;
                var xp = (index % colunms) * tileWidth;
                var yp = (index / colunms) * tileHeight;

                // Extraire de la feuille d'image
                // l'image de l'animation courante.
                tile = new Sprite(tileWidth, tileHeight);
                for (var y = 0; y < tileHeight; y++) {
                    var ya = yp + y;

                    for (var x = 0; x < tileWidth; x++) {
                        var xa = xp + x;
                        var pixel = getPixel(xa, ya);
                        tile.setPixel(x, y, pixel);
                    }
                }
                cache.put(index, tile);
            }
        } else {
            tile = cache.getOrDefault(EMPTY_INDEX, new Sprite(tileWidth, tileHeight));
        }
        return tile;
    }

    /**
     * Accesseur, obtenir la largeur de la carte.
     *
     * @return La largeur de la carte
     */
    public int getMapWidth() {
        return map[0].length;
    }

    /**
     * Accesseur, obtenir la hauteur de la carte.
     *
     * @return La hauteur de la carte
     */
    public int getMapHeight() {
        return map.length;
    }

}
