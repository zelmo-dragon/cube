package com.github.zelmodragon.cube.awt.graphic;

import com.github.zelmodragon.cube.pixel.graphic.PixelArrayRenderer;
import java.awt.Image;
import java.awt.image.BufferedImage;

/**
 * Gestionnaire de rendu graphique. Permet de manipuler une image à partir de la
 * bibliothèque Java AWT.
 *
 * @author MOSELLE Maxime
 */
public final class RendererAWT extends PixelArrayRenderer {

    /**
     * Image mémoire.
     */
    private final BufferedImage image;

    /**
     * Constructeur. Construit un gestionnaire de rendu graphique pour manipuler
     * une image à partir de la bibliothèque Java AWT.
     *
     * @param width Largeur de l'image en mémoire tampon
     * @param height Hauteur de l'image en mémoire tampon
     */
    public RendererAWT(final int width, final int height) {
        super(width, height);
        this.image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    }

    /**
     * Obtenir l'image de rendu.
     *
     * @return L'image pour le prochain rendu graphique
     */
    public Image getImage() {
        image.setRGB(0, 0, width, height, buffer, 0, width);
        return image;
    }

}
