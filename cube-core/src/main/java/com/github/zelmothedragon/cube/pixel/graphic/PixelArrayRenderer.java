package com.github.zelmothedragon.cube.pixel.graphic;

import com.github.zelmothedragon.cube.core.component.AnimatedImage;
import com.github.zelmothedragon.cube.core.component.FontImage;
import com.github.zelmothedragon.cube.core.component.Image;
import com.github.zelmothedragon.cube.core.component.ImageMap;
import com.github.zelmothedragon.cube.core.graphic.Renderer;
import java.util.Arrays;

/**
 * Implémentation interne du gestionnaire de rendu basé sur la manipulation de tableau
 * de pixels.
 *
 * @author MOSELLE Maxime
 */
public class PixelArrayRenderer implements Renderer<int[]> {

    protected final int width;

    protected final int height;

    protected final int[] buffer;

    protected int xOffset;

    protected int yOffset;

    public PixelArrayRenderer(final int width, final int height) {
        this.width = width;
        this.height = height;
        this.buffer = new int[width * height];
        this.xOffset = 0;
        this.yOffset = 0;
    }

    @Override
    public void clear() {
        Arrays.fill(buffer, Pixels.COLOR_TRANSPARENT);
    }

    @Override
    public void drawRectangle(final int x, final int y, final int w, final int h, final int color) {
        for (var yp = 0; yp < h; yp++) {
            var ya = y + yp;

            for (var xp = 0; xp < w; xp++) {
                var xa = x + xp;
                if (xp == 0 || xp == w - 1 || yp == 0 || yp == h - 1) {
                    setPixel(xa, ya, color);
                }
            }
        }
    }

    @Override
    public void drawFillRectangle(final int x, final int y, final int w, final int h, final int color) {
        for (var yp = 0; yp < h; yp++) {
            var ya = y + yp;

            for (var xp = 0; xp < w; xp++) {
                var xa = x + xp;
                setPixel(xa, ya, color);
            }
        }
    }

    @Override
    public void drawCircle(final int x, final int y, final int radius, final int color) {
        // Algorithme de tracé de cercle d'Andres

        var xa = radius + x;
        var ya = radius + y;
        var xp = 0;
        var yp = radius;
        var d = radius - 1;

        while (yp >= xp) {

            setPixel(xa + xp, ya + yp, color);
            setPixel(xa - xp, ya + yp, color);
            setPixel(xa + xp, ya - yp, color);
            setPixel(xa - xp, ya - yp, color);

            setPixel(xa + yp, ya + xp, color);
            setPixel(xa - yp, ya + xp, color);
            setPixel(xa + yp, ya - xp, color);
            setPixel(xa - yp, ya - xp, color);

            if (d >= 2 * xp) {
                d -= 2 * xp + 1;
                xp++;
            } else if (d < 2 * (radius - yp)) {
                d += 2 * yp - 1;
                yp--;
            } else {
                d += 2 * (yp - xp - 1);
                yp--;
                xp++;
            }
        }
    }

    @Override
    public void drawFillCircle(final int x, final int y, final int radius, final int color) {
        var diameter = 2 * radius;

        for (var yp = 0; yp < diameter; yp++) {
            var ya = y + yp;
            var dy = Math.pow(yp - radius, 2);

            for (var xp = 0; xp < diameter; xp++) {
                var xa = x + xp;
                var dx = Math.pow(xp - radius, 2);
                var distance = Math.sqrt(dx + dy);
                if (distance < radius) {
                    setPixel(xa, ya, color);
                }
            }
        }
    }

    @Override
    public void drawGradientCircle(final int x, final int y, final int radius, final int color) {
        var diameter = 2 * radius;

        for (var yp = 0; yp < diameter; yp++) {
            var ya = y + yp;
            var dy = Math.pow(yp - radius, 2);

            for (var xp = 0; xp < diameter; xp++) {
                var xa = x + xp;
                var dx = Math.pow(xp - radius, 2);
                var distance = Math.sqrt(dx + dy);

                if (distance < radius) {
                    var power = 1 - (distance / radius);
                    var amplifiedColor = Pixels.amplify(color, power);
                    var pixel = Pixels.light(amplifiedColor, getPixel(xa, ya));
                    setPixel(xa, ya, pixel);
                }
            }
        }
    }

    @Override
    public void drawLine(final int x0, final int y0, final int x1, final int y1, final int color) {
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

    @Override
    public void drawImage(final int x, final int y, final Image<int[]> image) {
        var data = image.getRawData();
        for (var yp = 0; yp < image.getHeight(); yp++) {
            var ya = y + yp;

            for (var xp = 0; xp < image.getWidth(); xp++) {
                var xa = x + xp;
                var color = data[xp + yp * image.getWidth()];
                setPixel(xa, ya, color);
            }
        }
    }

    @Override
    public void drawImage(final int x, final int y, final ImageMap<int[]> image) {
        for (var yp = 0; yp < image.getMapHeight(); yp++) {
            var ya = y + yp * image.getImageHeight();

            for (var xp = 0; xp < image.getMapWidth(); xp++) {
                var xa = x + xp * image.getImageWidth();
                var subImage = image.getImage(xp, yp);
                drawImage(xa, ya, subImage);
            }
        }
    }

    @Override
    public void drawImage(final int x, final int y, final AnimatedImage<int[]> image) {
        var subImage = image.getCurrentImage();
        drawImage(x, y, subImage);
    }

    @Override
    public void drawImage(final int x, final int y, final FontImage<int[]> image, final String text) {
        var lines = text.split(FontImage.LINE_SEPARATOR);

        for (var yp = 0; yp < lines.length; yp++) {
            var ya = y + yp * image.getImageHeight();
            var characters = lines[yp].split(FontImage.CHAR_SEPARATOR);

            for (var xp = 0; xp < characters.length; xp++) {
                var xa = x + xp * image.getImageWidth();
                var c = characters[xp];
                var subImage = image.getImage(c);
                drawImage(xa, ya, subImage);
            }
        }

    }

    @Override
    public void setOffset(final int xOffset, final int yOffset) {
        this.xOffset = xOffset - width / 2;
        this.yOffset = yOffset - height / 2;
    }

    @Override
    public void resetOffset() {
        this.xOffset = 0;
        this.yOffset = 0;
    }

    private int getPixel(final int xp, final int yp) {
        int pixel;
        var xo = xp - xOffset;
        var yo = yp - yOffset;
        var i = xo + yo * width;
        if (Pixels.isInBound(xo, width) && Pixels.isInBound(yo, height)) {
            pixel = buffer[i];
        } else {
            pixel = Pixels.COLOR_BLACK;
        }
        return pixel;
    }

    private void setPixel(final int x, final int y, final int color) {

        var xo = x - xOffset;
        var yo = y - yOffset;
        var i = xo + yo * width;

        if (Pixels.isInBound(xo, width) && Pixels.isInBound(yo, height)) {
            if (color != Pixels.COLOR_TRANSPARENT) {
                buffer[i] = color;
            }
        }
    }

}
