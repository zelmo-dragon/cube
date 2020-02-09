package com.github.zelmothedragon.cube.fx.graphic;

import com.github.zelmothedragon.cube.core.graphic.Pixel;
import com.github.zelmothedragon.cube.core.graphic.Render;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.PixelFormat;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

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
        // Effacer le canevas
        super.clear();
        g2d.clearRect(0, 0, g2d.getCanvas().getWidth(), g2d.getCanvas().getHeight());
    }

//    @Override
//    public void drawFillRect(int xp, int yp, int w, int h, int color) {
//        // TODO: corriger le scaling
//        var fxColor = toColor(color);
//        g2d.setFill(fxColor);
//        g2d.fillRect(xp, yp, w, h);
//    }
//
//    @Override
//    public void drawRect(int xp, int yp, int w, int h, int color) {
//        // TODO: corriger le scaling
//        var fxColor = toColor(color);
//        g2d.setStroke(fxColor);
//        g2d.strokeRect(xp, yp, w, h);
//    }
//
//    @Override
//    public void drawFillCircle(int xp, int yp, int radius, int color) {
//        // TODO: corriger le scaling
//        var fxColor = toColor(color);
//        g2d.setFill(fxColor);
//        g2d.fillOval(xp, yp, radius, radius);
//    }
//
//    @Override
//    public void drawGradientCircle(int xp, int yp, int radius, int color) {
//        // TODO: corriger le scaling
//        var fxColor = toColor(color);
//        var start = new Stop(0.0, fxColor);
//        var stop = new Stop(1.0, Color.TRANSPARENT);
//        var gradient = new RadialGradient(
//                0.0,
//                0.0,
//                0.5,
//                0.5,
//                0.5,
//                true,
//                CycleMethod.NO_CYCLE,
//                start,
//                stop
//        );
//        g2d.setFill(gradient);
//        g2d.fillOval(xp, yp, radius, radius);
//    }
//
//    @Override
//    public void drawLine(int x0, int y0, int x1, int y1, int color) {
//        // TODO: corriger le scaling
//        var fxColor = toColor(color);
//        g2d.setFill(fxColor);
//        g2d.strokeLine(x0, y0, x1, y1);
//    }
//
//    @Override
//    public void drawCircle(int xp, int yp, int radius, int color) {
//        // TODO: corriger le scaling
//        var fxColor = toColor(color);
//        g2d.setFill(fxColor);
//        g2d.strokeOval(xp, yp, radius, radius);
//    }
//
//    @Override
//    public void drawImage(int xp, int yp, Sprite sprite) {
//        // TODO: corriger le scaling
//        var w = sprite.getWidth();
//        var h = sprite.getHeight();
//        var fxImage = new WritableImage(w, h);
//        fxImage
//                .getPixelWriter()
//                .setPixels(
//                        0,
//                        0,
//                        w,
//                        h,
//                        PixelFormat.getIntArgbPreInstance(),
//                        sprite.getBuffer(),
//                        0,
//                        w
//                );
//        
//        g2d.drawImage(fxImage, xp, yp);
//    }
//
//    @Override
//    public void drawImage(int xp, int yp, FontSprite sprite, String text) {
//        // TODO: corriger le scaling
//        super.drawImage(xp, yp, sprite, text);
//    }
//
//    @Override
//    public void drawImage(int xp, int yp, TileMap tileMap) {
//        // TODO: corriger le scaling
//        super.drawImage(xp, yp, tileMap);
//    }

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

    private static Color toColor(final int color) {
        var red = Pixel.getRed(color);
        var green = Pixel.getGreen(color);
        var blue = Pixel.getBlue(color);
        var alpha = (double) Pixel.getAlpha(color) / (double) Pixel.OPAQUE;
        return Color.rgb(red, green, blue, alpha);
    }
}
