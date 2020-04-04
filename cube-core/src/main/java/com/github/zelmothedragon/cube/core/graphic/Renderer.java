package com.github.zelmothedragon.cube.core.graphic;

import com.github.zelmothedragon.cube.core.component.AnimatedImage;
import com.github.zelmothedragon.cube.core.component.FontImage;
import com.github.zelmothedragon.cube.core.component.Image;
import com.github.zelmothedragon.cube.core.component.ImageMap;

/**
 * Gestionnaire de rendu graphique.
 *
 * @param <T> Type du format de l'image
 * @author MOSELLE Maxime
 */
public interface Renderer<T> {

    /**
     * Effacer l'affichage.
     */
    void clear();

    /**
     * Dessiner un rectangle unicolore.
     *
     * @param x Coordonnée en abcisse
     * @param y Coordonnée en ordonnée
     * @param w Largeur en pixel
     * @param h Hauteur en pixel
     * @param color Couleur unique au format <i>AARRGGBB</i>
     */
    void drawRectangle(int x, int y, int w, int h, int color);

    /**
     * Dessiner un rectangle plein unicolore.
     *
     * @param x Coordonnée en abcisse
     * @param y Coordonnée en ordonnée
     * @param w Largeur en pixel
     * @param h Hauteur en pixel
     * @param color Couleur unique au format <i>AARRGGBB</i>
     */
    void drawFillRectangle(int x, int y, int w, int h, int color);

    /**
     * Dessiner un cercle unicolore.
     *
     * @param x Coordonnée en abcisse
     * @param y Coordonnée en ordonnée
     * @param radius Rayon en pixel
     * @param color Couleur unique au format <i>AARRGGBB</i>
     */
    void drawCircle(int x, int y, int radius, int color);

    /**
     * Dessiner un cercle plein unicolore.
     *
     * @param x Coordonnée en abcisse
     * @param y Coordonnée en ordonnée
     * @param radius Rayon en pixel
     * @param color Couleur unique au format <i>AARRGGBB</i>
     */
    void drawFillCircle(int x, int y, int radius, int color);

    /**
     * Dessiner un cercle dégradé unicolore.
     *
     * @param x Coordonnée en abcisse
     * @param y Coordonnée en ordonnée
     * @param radius Rayon en pixel
     * @param color Couleur unique au format <i>AARRGGBB</i>
     */
    void drawGradientCircle(int x, int y, int radius, int color);

    /**
     * Dessiner une ligne.
     *
     * @param x0 Coordonnée en abcisse du premier point
     * @param y0 Coordonnée en ordonnée du premier point
     * @param x1 Coordonnée en abcisse du second second point
     * @param y1 Coordonnée en ordonnée du second second point
     * @param color Couleur unique au format <i>AARRGGBB</i>
     */
    void drawLine(int x0, int y0, int x1, int y1, int color);

    /**
     * Dessiner une image.
     *
     * @param x Coordonnée en abcisse
     * @param y Coordonnée en ordonnée
     * @param image Image simple
     */
    void drawImage(int x, int y, Image<T> image);

    /**
     * Dessiner une image animée.
     *
     * @param x Coordonnée en abcisse
     * @param y Coordonnée en ordonnée
     * @param image Image animée
     */
    void drawImage(int x, int y, AnimatedImage<T> image);

    /**
     * Dessiner une image de police de caractère.
     *
     * @param x Coordonnée en abcisse
     * @param y Coordonnée en ordonnée
     * @param image Image de police de caractère
     * @param text Texte
     */
    void drawImage(int x, int y, FontImage<T> image, String text);

    /**
     *
     * Dessiner une image de tuile graphique.
     *
     * @param x Coordonnée en abcisse
     * @param y Coordonnée en ordonnée
     * @param image Image de tuile graphique
     */
    void drawImage(int x, int y, ImageMap<T> image);

    /**
     * Ajuster le décalage du rendu.
     *
     * @param xOffset Décalage en abcisse
     * @param yOffset Décalage en ordonnée
     */
    void setOffset(int xOffset, int yOffset);

    /**
     * Réinitialiser le décalage.
     */
    void resetOffset();

    /**
     * Accesseur, obtenir la largeur de l'affichage en pixel.
     *
     * @return La largeur
     */
    int getWidth();

    /**
     * Accesseur, obtenir la hauteur de l'affichage en pixel.
     *
     * @return La hauteur
     */
    int getHeight();
}
