package com.github.zelmodragon.cube.core.model;

/**
 * Image de police de caractère.
 *
 * @param <T> Type du format d'image
 * @author MOSELLE Maxime
 */
public interface FontImage<T> extends Component {

    /**
     * Séparateur de cellule.
     */
    String CHAR_SEPARATOR = "";

    /**
     * Séparateur de ligne.
     */
    String LINE_SEPARATOR = "\n";

    /**
     * Récupérer une image d'un caractère en fonction du caractère.
     *
     * @param character Caractère
     * @return L'image
     */
    Image<T> getImage(String character);

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
}
