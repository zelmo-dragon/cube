package com.github.zelmothedragon.cube.core.graphic;

import com.github.zelmothedragon.cube.core.entity.geometry.Camera;
import com.github.zelmothedragon.cube.core.entity.geometry.Point;
import com.github.zelmothedragon.cube.core.entity.geometry.Rectangle;
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
     * Décalage en abcisse.
     */
    protected int xOffset;

    /**
     * Décalage en ordonnée.
     */
    protected int yOffset;

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
        this.xOffset = 0;
        this.yOffset = 0;
    }

    /**
     * Effacer toute la mémoire tampon. Génère un écran noire. Avant de
     * commencer à construire une image, il faut vider le tampon.
     */
    public void clear() {
        Arrays.fill(buffer, Pixel.BLACK);
    }

    /**
     * Dessiner un rectangle plein unicolore.
     *
     * @param rectangle rectangle
     * @param color Couleur
     */
    public void drawFillRect(
            final Rectangle rectangle,
            final int color) {

        for (var y = 0; y < rectangle.getHeight(); y++) {
            var ya = rectangle.getYp() + y;

            for (var x = 0; x < rectangle.getWidth(); x++) {
                var xa = rectangle.getXp() + x;
                setPixel(xa, ya, color);
            }
        }
    }

    /**
     * Dessiner un rectangle unicolore.
     *
     * @param rectangle rectangle
     * @param color Couleur
     */
    public void drawRect(
            final Rectangle rectangle,
            final int color) {

        for (var y = 0; y < rectangle.getHeight(); y++) {
            var ya = rectangle.getYp() + y;

            for (var x = 0; x < rectangle.getWidth(); x++) {
                var xa = rectangle.getXp() + x;
                if (x == 0
                        || x == rectangle.getWidth() - 1
                        || y == 0
                        || y == rectangle.getHeight() - 1) {

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

        // Algorithme de tracé de cercle d'Andres
        var x = 0;
        var y = radius;
        var d = radius - 1;

        while (y >= x) {

            setPixel(xp + x, yp + y, color);
            setPixel(xp - x, yp + y, color);
            setPixel(xp + x, yp - y, color);
            setPixel(xp - x, yp - y, color);

            setPixel(xp + y, yp + x, color);
            setPixel(xp - y, yp + x, color);
            setPixel(xp + y, yp - x, color);
            setPixel(xp - y, yp - x, color);

            if (d >= 2 * x) {
                d -= 2 * x + 1;
                x++;
            } else if (d < 2 * (radius - y)) {
                d += 2 * y - 1;
                y--;
            } else {
                d += 2 * (y - x - 1);
                y--;
                x++;
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

        // Algorithme de tracé de ligne de Bresenham
        var xp = x0;
        var yp = y0;

        var dx = Math.abs(x1 - xp);
        var dy = Math.abs(y1 - yp);

        var sx = (xp < x1) ? 1 : -1;
        var sy = (yp < y1) ? 1 : -1;

        var delta = dx - dy;

        while (!(xp == x1 && yp == y1)) {

            setPixel(xp, yp, color);

            var delta2 = 2 * delta;

            if (delta2 > -dy) {
                delta = delta - dy;
                xp = xp + sx;
            }

            if (delta2 < dx) {
                delta = delta + dx;
                yp = yp + sy;
            }
        }
    }

    /**
     * Dessiner une image.
     *
     * @param point Position
     * @param sprite Une image
     */
    public void drawImage(
            final Point point,
            final Sprite sprite) {

        for (var y = 0; y < sprite.getHeight(); y++) {
            var ya = point.getYp() + y;
            for (var x = 0; x < sprite.getWidth(); x++) {
                var xa = point.getXp() + x;
                setPixel(xa, ya, sprite.getPixel(x, y));
            }
        }
    }

    /**
     * Dessiner un texte à partir d'une police de charactères.
     *
     * @param point Position
     * @param sprite Police de charactères
     * @param text Message à dessiner
     */
    public void drawImage(
            final Point point,
            final FontSprite sprite,
            final String text) {

        var lines = text.split(FontSprite.LINE_SEPARATOR);
        for (var y = 0; y < lines.length; y++) {
            var ya = point.getYp() + y * sprite.getCharacterHeight();
            var characters = lines[y].split(FontSprite.CHAR_SEPARATOR);
            for (var x = 0; x < characters.length; x++) {
                var xa = point.getXp() + x * sprite.getCharacterWidth();
                var p = new Point(xa, ya);
                drawImage(p, sprite.getCharacter(characters[x]));
            }
        }
    }

    /**
     * Dessiner une carte de tuile graphique.
     *
     * @param point Position
     * @param tileMap Carte de tuiles graphiques
     */
    public void drawImage(
            final Point point,
            final TileMap tileMap) {

        for (var y = 0; y < tileMap.getMapHeight(); y++) {
            for (var x = 0; x < tileMap.getMapWidth(); x++) {
                var tile = tileMap.getTile(x, y);
                var xa = point.getXp() + x * tile.getWidth();
                var ya = point.getYp() + y * tile.getHeight();
                var position = new Point(xa, ya);
                drawImage(position, tile);
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
        var xo = xp - xOffset;
        var yo = yp - yOffset;
        if (Pixel.isInBound(xo, width) && Pixel.isInBound(yo, height)) {
            pixel = buffer[xo + yo * width];
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

        var xo = xp - xOffset;
        var yo = yp - yOffset;
        if (Pixel.isInBound(xo, width) && Pixel.isInBound(yo, height)) {

            var index = xo + yo * width;
            var alpha = Pixel.getAlpha(color);
            if (alpha == Pixel.UNICOLOR_MAX_VALUE) {
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

    /**
     * Changer le décalage de la caréma.
     *
     * @param camera Caméra
     */
    public void setOffset(final Camera camera) {
        this.xOffset = camera.getXp() - width / 2;
        this.yOffset = camera.getYp() - height / 2;
    }

    /**
     * Supprimer le décalage de la caméra.
     */
    public void resetOffset() {
        this.xOffset = 0;
        this.yOffset = 0;
    }
}
