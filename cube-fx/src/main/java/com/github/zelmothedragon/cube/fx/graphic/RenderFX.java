package com.github.zelmothedragon.cube.fx.graphic;

import com.github.zelmothedragon.cube.core.graphic.Render;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.PixelFormat;
import javafx.scene.image.WritableImage;

/**
 * Gestionnaire de rendu graphique. Permet de manipuler une image à partir de la
 * bibliothèque JavaFX.
 *
 * @author MOSELLE Maxime
 */
public final class RenderFX extends Render {

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
    public RenderFX(
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
        // Effacer le canevas
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
