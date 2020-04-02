package com.github.zelmothedragon.cube.awt.graphic;

import com.github.zelmothedragon.cube.core.graphic.Renderer;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

/**
 * Gestionnaire de rendu graphique. Permet de manipuler une image à partir de la
 * bibliothèque Java AWT.
 *
 * @author MOSELLE Maxime
 */
public final class RendererAWT extends Renderer {

    /**
     * Image mémoire.
     */
    private final BufferedImage image;

    /**
     * Constructeur.Construit un gestionnaire de rendu graphique pour manipuler
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
        var dataBuffer = (DataBufferInt) image.getRaster().getDataBuffer();
        var data = dataBuffer.getData();
        System.arraycopy(buffer, 0, data, 0, data.length);
        return image;
    }

}
