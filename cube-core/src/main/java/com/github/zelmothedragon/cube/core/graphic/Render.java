package com.github.zelmothedragon.cube.core.graphic;

import java.util.Arrays;

/**
 * Gestionnaire de rendu graphique. Permet de manipuler une image sous forme de
 * tableau de pixels.
 *
 * @author MOSELLE Maxime
 */
public abstract class Render {

    /**
     * Largeur de l'image en mémoire tampon.
     */
    protected final int width;

    /**
     * Hauteur de l'image en mémoire tampn.
     */
    protected final int height;

    /**
     * Mémoire tampon de l'image sous forme de tableau de pixels. Ce tampon sera
     * ensuite envoyer vers l'image de destination afin d'être afficher à
     * l'écran.
     */
    protected final int[] buffer;

    /**
     * Constructeur. Construit un gestionnaire de rendu graphique pour manipuler
     * une image sous forme de tableau de pixels.
     *
     * @param width Largeur de l'image en mémoire tampon
     * @param height Hauteur de l'image en mémoire tampn
     */
    protected Render(final int width, final int height) {
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
     * Dessiner un rectangle plein unicolore.
     *
     * @param xp Position en abcisse
     * @param yp Position en ordonnée
     * @param w Largeur en pixel
     * @param h Hauteur en pixels
     * @param color Couleur
     */
    public void drawFillRect(
            final int xp,
            final int yp,
            final int w,
            final int h,
            final int color) {

        int xa;
        int ya;

        for (var y = 0; y < h; y++) {
            ya = yp + y;
            if (isInBound(ya, height)) {
                for (var x = 0; x < w; x++) {
                    xa = xp + x;
                    if (isInBound(xa, width)) {
                        buffer[xa + ya * width] = color;
                    }
                }
            }
        }
    }

    /**
     * Dessiner un rectangle unicolore.
     *
     * @param xp Position en abcisse
     * @param yp Position en ordonnée
     * @param w Largeur en pixel
     * @param h Hauteur en pixels
     * @param color Couleur
     */
    public void drawRect(
            final int xp,
            final int yp,
            final int w,
            final int h,
            final int color) {

        int xa;
        int ya;

        for (var y = 0; y < h; y++) {
            ya = yp + y;
            if (isInBound(ya, height)) {
                for (var x = 0; x < w; x++) {
                    xa = xp + x;
                    if (isInBound(xa, width) && (x == 0 || x == w - 1 || y == 0 || y == h - 1)) {
                        buffer[xa + ya * width] = color;
                    }
                }
            }
        }
    }

    /**
     * Dessiner un cercle plein unicolore.
     *
     * @param xp Position en abcisse
     * @param yp Position en ordonnée
     * @param radius Rayon du cercle en pixel
     * @param color Couleur
     */
    public void drawFillCircle(
            final int xp,
            final int yp,
            final int radius,
            final int color) {

        int xa;
        int ya;

        double dx;
        double dy;

        var diameter = radius * 2;

        for (var y = 0; y < diameter; y++) {
            ya = yp + y;
            dy = Math.pow(y - radius, 2);
            if (isInBound(ya, height)) {
                for (var x = 0; x < diameter; x++) {
                    xa = xp + x;
                    dx = Math.pow(x - radius, 2);
                    if (isInBound(xa, width)) {
                        var distance = Math.sqrt(dx + dy);
                        if (distance < radius) {
                            buffer[xa + ya * width] = color;
                        }
                    }
                }
            }
        }
    }

    /**
     * Dessiner un cercle dégradé unicolore.
     *
     * @param xp Position en abcisse
     * @param yp Position en ordonnée
     * @param radius Rayon du cercle en pixel
     * @param color Couleur
     */
    public void drawGradientCircle(
            final int xp,
            final int yp,
            final int radius,
            final int color) {

        int xa;
        int ya;

        double dx;
        double dy;

        var diameter = radius * 2;

        for (var y = 0; y < diameter; y++) {
            ya = yp + y;
            dy = Math.pow(y - radius, 2);
            if (isInBound(ya, height)) {
                for (var x = 0; x < diameter; x++) {
                    xa = xp + x;
                    dx = Math.pow(x - radius, 2);
                    if (isInBound(xa, width)) {
                        var distance = Math.sqrt(dx + dy);
                        if (distance < radius) {
                            var power = 1 - (distance / radius);
                            var alpha = Pixel.getAlpha(color) * power;
                            var red = Pixel.getRed(color) * power;
                            var green = Pixel.getGreen(color) * power;
                            var blue = Pixel.getBlue(color) * power;

                            buffer[xa + ya * width] = Pixel.toPixel(
                                    (int) alpha,
                                    (int) red,
                                    (int) green,
                                    (int) blue
                            );
                        }
                    }
                }
            }
        }
    }

    /**
     * Dessiner un cercle plein unicolore.
     *
     * @param xp Position en abcisse
     * @param yp Position en ordonnée
     * @param radius Rayon du cercle en pixel
     * @param color Couleur
     */
    public void drawCircle(
            final int xp,
            final int yp,
            final int radius,
            final int color) {

        int xa;
        int ya;

        double dx;
        double dy;

        var diameter = radius * 2;

        for (var y = 0; y < diameter; y++) {
            ya = yp + y;
            dy = Math.pow(y - radius, 2);
            if (isInBound(ya, height)) {
                for (var x = 0; x < diameter; x++) {
                    xa = xp + x;
                    dx = Math.pow(x - radius, 2);
                    if (isInBound(xa, width)) {
                        var distance = Math.sqrt(dx + dy);
                        if (distance < radius && distance >= radius - 1) {
                            buffer[xa + ya * width] = color;
                        }
                    }
                }
            }
        }
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
        var copy = new int[buffer.length];
        System.arraycopy(buffer, 0, copy, 0, buffer.length);
        return copy;
    }

    private void setPixel(final int xp, final int yp, final int pixel) {

        var alpha = Pixel.getAlpha(pixel);
        if (xp >= 0 && xp < width && yp >= 0 && yp < height && alpha > 0) {
            buffer[xp + yp * width] = pixel;
        }

    }

    /**
     * Vérifier qu'une position est comprise dans une taille.
     *
     * @param position Position
     * @param size Taille
     * @return La valeur <code>true</code> si la position est comprise dans la
     * taille sinon <code>false</code>
     */
    private static boolean isInBound(final int position, final int size) {

        return position >= 0 && position < size;
    }

}
