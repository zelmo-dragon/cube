package com.github.zelmothedragon.cube.core.graphic;

import com.github.zelmothedragon.cube.core.entity.geometry.Dimension;
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
     * Dimension d'une tuile graphique.
     */
    private final Dimension tileSize;

    /**
     * Carte 2D pour désigner l'emplacement des tuiles.
     */
    private final int[][] map;

    /**
     * Constructeur. Construit une carte de tuile graphique à partir d'une
     * feuille d'image.
     *
     * @param tileSet Feuille d'image contenant toutes les tuiles graphiques
     * @param tileSize Dimension d'une tuile graphique
     * @param map Carte 2D pour désigner l'emplacement des tuiles
     */
    public TileMap(
            final Sprite tileSet,
            final Dimension tileSize,
            final int[][] map) {

        super(tileSet.getRectangle().getDimension(), tileSet.buffer);
        this.cache = new HashMap<>();
        this.tileSize = tileSize;
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
                tile = new Sprite(tileSize);
                cache.put(index, tile);
            } else {
                var colunms = rectangle.getDimension().getWidth() / tileSize.getWidth();
                var xp = (index % colunms) * tileSize.getWidth();
                var yp = (index / colunms) * tileSize.getHeight();

                // Extraire de la feuille d'image
                // l'image de l'animation courante.
                tile = new Sprite(tileSize);
                for (var y = 0; y < tileSize.getHeight(); y++) {
                    var ya = yp + y;

                    for (var x = 0; x < tileSize.getWidth(); x++) {
                        var xa = xp + x;
                        var pixel = getPixel(xa, ya);
                        tile.setPixel(x, y, pixel);
                    }
                }
                cache.put(index, tile);
            }
        } else {
            tile = cache.getOrDefault(EMPTY_INDEX, new Sprite(tileSize));
        }
        return tile;
    }

    /**
     * Accesseur, obtenir la largeur de la carte.
     *
     * @return La largeur de la carte en tuile
     */
    public int getMapWidth() {
        return map[0].length;
    }

    /**
     * Accesseur, obtenir la hauteur de la carte.
     *
     * @return La hauteur de la carte en tuile
     */
    public int getMapHeight() {
        return map.length;
    }

    /**
     * Accesseur, obtenir la largeur de la carte.
     *
     * @return La largeur de la carte en pixel
     */
    public int getMapWidthInPixel() {
        return tileSize.getWidth() * getMapWidth();
    }

    /**
     * Accesseur, obtenir la hauteur de la carte.
     *
     * @return La hauteur de la carte en pixel
     */
    public int getMapHeightInPixel() {
        return tileSize.getHeight() * getMapHeight();
    }

}
