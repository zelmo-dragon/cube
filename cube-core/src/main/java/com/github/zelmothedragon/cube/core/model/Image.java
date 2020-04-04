package com.github.zelmothedragon.cube.core.model;

/**
 * Image simple.
 *
 * @param <T> Type du format d'image
 * @author MOSELLE Maxime
 */
public interface Image<T> extends Component {

    /**
     * Index vide.
     */
    int EMPTY_INDEX = -1;

    /**
     * Accesseur, obtenir l'index de cette image.
     *
     * @return L'index
     */
    int getIndex();

    /**
     * Accesseur, obtenir la donnée brute de l'image.
     *
     * @return La donnée brute
     */
    T getRawData();

    /**
     * Accesseur, obtenir la largeur de l'image en pixel.
     *
     * @return La largeur
     */
    int getWidth();

    /**
     * Accesseur, obtenir la hauteur de l'image en pixel.
     *
     * @return La hauteur
     */
    int getHeight();
}
