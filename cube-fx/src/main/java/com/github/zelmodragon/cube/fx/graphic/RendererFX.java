package com.github.zelmodragon.cube.fx.graphic;

import com.github.zelmodragon.cube.pixel.graphic.PixelArrayRenderer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.PixelFormat;
import javafx.scene.image.WritableImage;

/**
 * Gestionnaire de rendu graphique. Permet de manipuler une image à partir de la
 * bibliothèque JavaFX.
 *
 * @author MOSELLE Maxime
 */
public final class RendererFX extends PixelArrayRenderer {

    /**
     * Contexte graphique de JavaFX.
     */
    private final GraphicsContext g2d;

    /**
     * Image mémoire.
     */
    private final WritableImage image;

    /**
     * Constructeur.Construit un gestionnaire de rendu graphique pour manipuler
     * une image à partir de la bibliothèque JavaFX.
     *
     * @param width Largeur de l'image en mémoire tampon
     * @param height Hauteur de l'image en mémoire tampon
     * @param g2d Contexte 2D de JavaFX.
     */
    public RendererFX(
            final int width,
            final int height,
            final GraphicsContext g2d) {
        
        super(width, height);
        this.g2d = g2d;
        this.image = new WritableImage(width, height);
    }
    
    @Override
    public void clear() {
        super.clear();
        g2d.clearRect(0, 0, g2d.getCanvas().getWidth(), g2d.getCanvas().getHeight());
    }

    /**
     * Effectuer le rendu graphique. Dessine l'image en mémoire dans le canevas.
     */
    public void draw() {
        // Transférer la mémoire tampon dans l'image
        image
                .getPixelWriter()
                .setPixels(
                        0,
                        0,
                        width,
                        height,
                        PixelFormat.getIntArgbPreInstance(),
                        buffer,
                        0,
                        width
                );

        // Dessiner l'image dans le canevas
        g2d.drawImage(image, 0, 0, g2d.getCanvas().getWidth(), g2d.getCanvas().getHeight());
    }
    
}
