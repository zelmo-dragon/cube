package com.github.zelmothedragon.cube.core.system;

import com.github.zelmothedragon.cube.core.GameContainer;
import com.github.zelmothedragon.cube.core.entity.data.Mandelbrot;
import com.github.zelmothedragon.cube.core.entity.geometry.Rectangle;
import com.github.zelmothedragon.cube.core.graphic.Pixel;
import com.github.zelmothedragon.cube.core.graphic.Render;
import com.github.zelmothedragon.cube.core.graphic.Sprite;
import com.github.zelmothedragon.cube.core.input.GamePad;
import java.util.UUID;

/**
 * Ensemble de Mandelbrot.
 *
 * @author MOSELLE Maxime
 */
public class MandelbrotSystem extends AbstractSystem {

    private final UUID mandelbrot;

    public MandelbrotSystem(final GameContainer gc, final int priority) {
        super(gc, priority);
        this.mandelbrot = gc.getFactory().createMandelbrot();
    }

    @Override
    public void update() {

        var data = gc
                .getEntities()
                .get(mandelbrot, Mandelbrot.class);

        System.out.println(data);

        if (gc.getInputs().isKeyPressed(GamePad.ACTION)) {
            var scale = data.getScale();
            scale += 1;
            data.setScale(scale);
        }
        if (gc.getInputs().isKeyPressed(GamePad.OPTION)) {
            var iteration = data.getIteration();
            iteration += 1;
            data.setIteration(iteration);
        }

    }

    @Override
    public void draw(final Render g2d) {

        var rectangle = gc
                .getEntities()
                .get(mandelbrot, Rectangle.class);

        var data = gc
                .getEntities()
                .get(mandelbrot, Mandelbrot.class);

        var sprite = gc
                .getEntities()
                .get(mandelbrot, Sprite.class);

        var w = sprite.getWidth();
        var h = sprite.getHeight();
        for (var y = 0; y < h; y++) {
            for (var x = 0; x < w; x++) {
                var xp = (x - w / 2.0) / data.getScale();
                var yp = (y - h / 2.0) / data.getScale();
                var color = calculatePoint(data.getIteration(), xp, yp);
                sprite.setPixel(x, y, color);
            }
        }

        g2d.drawRect(
                rectangle.getXp(),
                rectangle.getYp(),
                rectangle.getWidth(),
                rectangle.getHeight(),
                0xFFFF0000
        );

    }

    private static int calculatePoint(
            final int iteration,
            final double xp,
            final double yp) {
        var cx = xp;
        var cy = yp;
        var x = xp;
        var y = yp;
        var n = 0;
        while (n < iteration) {
            var nx = cx + x * x - y * y;
            var ny = cy + 2 * x * y;
            x = nx;
            y = ny;

            if (x * x + y * y > 4) {
                break;
            }
            n++;
        }
        int color;
        if (n == iteration) {
            color = Pixel.BLACK;
        } else {
            var hue = n / (float) iteration;
            color = Pixel.convertHSB(hue, 0.5f, 1);
        }
        return color;
    }

}
