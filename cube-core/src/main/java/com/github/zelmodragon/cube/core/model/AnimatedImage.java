package com.github.zelmodragon.cube.core.model;

import com.github.zelmodragon.cube.core.util.geometry.Rectangle;

/**
 * Image animée.
 *
 * @param <T> Type du format d'image
 * @author MOSELLE Maxime
 */
public interface AnimatedImage<T> extends Component {

    /**
     * Mettre à jour l'animation.
     */
    void update();

    /**
     * Jouer l'animation.
     */
    void play();

    /**
     * Mettre en pause l'animation.
     */
    void pause();

    /**
     * Stopper l'animation.
     */
    void stop();

    /**
     * Ajouter les coordonnées de début d'une animation pour une orientation.
     *
     * @param orientation Orientation de l'image
     * @param rectangle Zone de découpage de la première image de l'animation
     * sur la feuille d'image
     */
    void addOffset(final Orientation orientation, Rectangle rectangle);

    /**
     * Récupérer la zone de découpage de la première image de l'animation sur la
     * feuille d'image.
     *
     * @param orientation Orientation de l'image
     * @return La zone de découpage
     */
    Rectangle getOffset(final Orientation orientation);

    /**
     * Récupérer la zone de découpage de l'animation courrante sur la feuille
     * d'image.
     *
     * @return La zone de découpage
     */
    Rectangle getCurrentOffset();

    /**
     * Changer l'orientation de l'image.
     *
     * @param orientation Orientation de l'image
     */
    void setOrientation(Orientation orientation);

    /**
     * Accesseur, obtenir l'orientation.
     *
     * @return L'orientation
     */
    Orientation getOrientation();

    /**
     * Récupérer l'image de la position courante de l'animation.
     *
     * @return L'image courante
     */
    Image<T> getCurrentImage();

    /**
     * Accesseur, indique si l'animation est en cours.
     *
     * @return La valeur <code>true</code> si l'animation est en cours, sinon la
     * valeur <code>false</code> est retournée
     */
    boolean isPlaying();

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
     * Accesseur, obtenir la vitesse de l'animation.
     *
     * @return La vitesse de l'animation
     */
    int getDuration();

    /**
     * Accesseur, obtenir le nombre d'images composant une animation.
     *
     * @return
     */
    int getCount();

}
