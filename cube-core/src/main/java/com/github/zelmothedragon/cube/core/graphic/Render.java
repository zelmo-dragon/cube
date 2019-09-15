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
     * commencer à construire une image, il faut vider le tampon.
     */
    public void clear() {
        Arrays.fill(buffer, Pixel.TRANSPARENT);
    }

    /**
     * Dessiner un rectangle plein unicolore.
     *
     * @param xp Position en abcisse
     * @param yp Position en ordonnée
     * @param w Largeur en pixel
     * @param h Hauteur en buffer
     * @param color Couleur
     */
    public void drawFillRect(
            final int xp,
            final int yp,
            final int w,
            final int h,
            final int color) {

        for (var y = 0; y < h; y++) {
            var ya = yp + y;

            for (var x = 0; x < w; x++) {
                var xa = xp + x;
                setPixel(xa, ya, color);
            }

        }
    }

    /**
     * Dessiner un rectangle unicolore.
     *
     * @param xp Position en abcisse
     * @param yp Position en ordonnée
     * @param w Largeur en pixel
     * @param h Hauteur en buffer
     * @param color Couleur
     */
    public void drawRect(
            final int xp,
            final int yp,
            final int w,
            final int h,
            final int color) {

        for (var y = 0; y < h; y++) {
            var ya = yp + y;

            for (var x = 0; x < w; x++) {
                var xa = xp + x;
                if (x == 0 || x == w - 1 || y == 0 || y == h - 1) {
                    setPixel(xa, ya, color);
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

        var diameter = radius * 2;

        for (var y = 0; y < diameter; y++) {
            var ya = yp + y;
            var dy = Math.pow(y - radius, 2);

            for (var x = 0; x < diameter; x++) {
                var xa = xp + x;
                var dx = Math.pow(x - radius, 2);
                var distance = Math.sqrt(dx + dy);
                if (distance < radius) {
                    setPixel(xa, ya, color);
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

        var diameter = radius * 2;

        for (var y = 0; y < diameter; y++) {
            var ya = yp + y;
            var dy = Math.pow(y - radius, 2);
            for (var x = 0; x < diameter; x++) {
                var xa = xp + x;
                var dx = Math.pow(x - radius, 2);
                var distance = Math.sqrt(dx + dy);
                if (distance < radius) {
                    var power = 1 - (distance / radius);
                    var alpha = Pixel.getAlpha(color) * power;
                    var red = Pixel.getRed(color) * power;
                    var green = Pixel.getGreen(color) * power;
                    var blue = Pixel.getBlue(color) * power;

                    var pixel = Pixel.light(
                            (int) alpha,
                            (int) red,
                            (int) green,
                            (int) blue,
                            getPixel(xa, ya)
                    );
                    setPixel(xa, ya, pixel);
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

        var diameter = radius * 2;

        for (var y = 0; y < diameter; y++) {
            var ya = yp + y;
            var dy = Math.pow(y - radius, 2);

            for (var x = 0; x < diameter; x++) {
                var xa = xp + x;
                var dx = Math.pow(x - radius, 2);
                var distance = Math.sqrt(dx + dy);
                if (distance < radius && distance >= radius - 1) {
                    setPixel(xa, ya, color);
                }
            }
        }
    }

    /**
     * Dessiner une ligne unicolore.
     *
     * @param x0 Position en abcisse du premier point
     * @param y0 Position en ordonnée du premier point
     * @param x1 Position en abcisse du second point
     * @param y1 Position en ordonnée du second point
     * @param color Couleur
     */
    public void drawLine(
            final int x0,
            final int y0,
            final int x1,
            final int y1,
            final int color) {

        var xp = x0;
        var yp = y0;

        var dx = Math.abs(x1 - x0);
        var dy = Math.abs(y1 - x0);

        var sx = x0 < x1 ? 1 : -1;
        var sy = y0 < y1 ? 1 : -1;

        var delta = dx - dy;

        while (Pixel.isInBound(xp, width)
                && Pixel.isInBound(yp, height)
                && (xp != x1 || yp != y1)) {

            buffer[xp + yp * width] = color;

            var i = delta * 2;

            if (i > -1 * dy) {
                delta -= dy;
                xp += sx;
            }

            if (i < dx) {
                delta += dx;
                yp += sy;
            }
        }
    }

    /**
     * Dessiner une image.
     *
     * @param xp Position en abcisse
     * @param yp Position en ordonnée
     * @param sprite Une image
     */
    public void drawImage(final int xp, final int yp, final Sprite sprite) {

        for (var y = 0; y < sprite.height; y++) {
            var ya = yp + y;

            for (var x = 0; x < sprite.width; x++) {
                var xa = xp + x;
                setPixel(xa, ya, sprite.getPixel(x, y));
            }
        }
    }

    /**
     * Dessiner un texte à partir d'une police de charactères.
     *
     * @param xp Position en abcisse
     * @param yp Position en ordonnée
     * @param sprite Police de charactères
     * @param text Message à dessiner
     */
    public void drawImage(
            final int xp,
            final int yp,
            final FontSprite sprite,
            final String text) {

        var lines = text.split(FontSprite.LINE_SEPARATOR);
        for (int y = 0; y < lines.length; y++) {
            var ya = yp + y * sprite.getCharacterHeight();
            var characters = lines[y].split(FontSprite.CHAR_SEPARATOR);
            for (int x = 0; x < characters.length; x++) {
                var xa = xp + x * sprite.getCharacterWidth();

                drawImage(xa, ya, sprite.getCharacter(characters[x]));
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
     * Accesseur, obtenir l'image sous forme de tableau de buffer.
     *
     * @return La mémoire tampon de l'image
     */
    public int[] getBuffer() {
        // Permet l'immuabilité de cette classe
        return Arrays.copyOf(buffer, buffer.length);
    }

    /**
     * Accesseur, obtenir un pixel sur l'image.
     *
     * @param xp Position en abcisse
     * @param yp Position en ordonnée
     * @return Un pixel de l'image
     */
    private int getPixel(final int xp, final int yp) {
        int pixel;
        if (Pixel.isInBound(xp, width) && Pixel.isInBound(yp, height)) {
            pixel = buffer[xp + yp * width];
        } else {
            pixel = Pixel.TRANSPARENT;
        }
        return pixel;
    }

    /**
     * Muttateur, écrire un pixel sur l'image.
     *
     * @param xp Position en abcisse
     * @param yp Position en ordonnée
     * @param color Une couleur
     */
    private void setPixel(final int xp, final int yp, final int color) {

        if (Pixel.isInBound(xp, width) && Pixel.isInBound(yp, height)) {

            var index = xp + yp * width;
            var alpha = Pixel.getAlpha(color);
            if (alpha == Pixel.OPAQUE) {
                buffer[index] = color;
            } else {
                var pixel = buffer[index];
                pixel = Pixel.blend(color, pixel);

                if (pixel != Pixel.TRANSPARENT) {
                    buffer[index] = pixel;
                }
            }
        }
    }
}
