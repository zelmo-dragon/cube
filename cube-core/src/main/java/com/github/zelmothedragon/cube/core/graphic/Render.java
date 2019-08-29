package com.github.zelmothedragon.cube.core.graphic;

import java.util.Arrays;

/**
 * Gestionnaire de rendu graphique. Permet de manipuler une image sous forme de
 * tableau de pixels.
 *
 * @author MOSELLE Maxime
 */
public final class Render {

    /**
     * Largeur de l'image en mémoire tampon.
     */
    private final int width;

    /**
     * Hauteur de l'image en mémoire tampn.
     */
    private final int height;

    /**
     * Mémoire tampon de l'image sous forme de tableau de pixels. Ce tampon sera
     * ensuite envoyer vers l'image de destination afin d'être afficher à
     * l'écran.
     */
    private final int[] buffer;

    /**
     * Constructeur. Construit un gestionnaire de rendu graphique pour manipuler
     * une image sous forme de tableau de pixels.
     *
     * @param width Largeur de l'image en mémoire tampon
     * @param height Hauteur de l'image en mémoire tampn
     */
    public Render(final int width, final int height) {
        this.width = width;
        this.height = height;
        this.buffer = new int[width * height];
    }

    /**
     * Effacer toute la mémoire tampon. Génère un écran noire. Avant de
     * commencer à construire une image, il faut vide le tampon.
     */
    public void clear() {
        Arrays.fill(buffer, 0);
    }

    /**
     * Accesseur, obtenir la largeur de l'image en mémoire tampon.
     *
     * @return La largeur
     */
    public int getWidth() {
        return width;
    }

    /**
     * Accesseur, obtenir la hauteur de l'image en mémoire tampon.
     *
     * @return La hauteur
     */
    public int getHeight() {
        return height;
    }

    /**
     * Accesseur, obtenir l'image sous forme de tableau de pixels.
     *
     * @return La mémoire tampon de l'image
     */
    public int[] getBuffer() {
        // Permet l'immuabilité de cette classe
        int[] copy = new int[buffer.length];
        System.arraycopy(buffer, 0, copy, 0, buffer.length);
        return copy;
    }

}
