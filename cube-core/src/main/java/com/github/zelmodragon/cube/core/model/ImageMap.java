package com.github.zelmodragon.cube.core.model;

/**
 * Image de tuiles graphique.
 *
 * @param <T> Type du format d'image
 * @author MOSELLE Maxime
 */
public interface ImageMap<T> extends Component {

    /**
     * Séparateur de cellule.
     */
    String CELL_SEPARATOR = ",";

    /**
     * Séparateur de ligne.
     */
    String LINE_SEPARATOR = "\n";

    /**
     * Récupérer une tuile graphique en fonction de ses coordonnées sur la
     * carte.
     *
     * @param x Coordonné en abscisse
     * @param y Coordonné en ordonnée
     * @param layout Indice de profondeur
     * @return L'image
     */
    Image<T> getImage(int x, int y, int layout);

    /**
     * Accesseur, obtenir la largeur de l'image en pixel.
     *
     * @return La largeur
     */
    int getImageWidth();

    /**
     * Accesseur, obtenir la hauteur de l'image en pixel.
     *
     * @return La hauteur
     */
    int getImageHeight();

    /**
     * Accesseur, obtenir la largeur de la carte en unité de tuile graphique.
     *
     * @return La largeur
     */
    int getMapWidth();

    /**
     * Accesseur, obtenir la hauteur de la carte en unité de tuile graphique.
     *
     * @return La hauteur
     */
    int getMapHeight();

    /**
     * Accesseur, obtenir le nombre d'indices de profondeur.
     *
     * @return Le nombre d'indices de profondeur
     */
    int getLayoutCount();
}
