package com.github.zelmothedragon.cube.core.system;

import com.github.zelmothedragon.cube.core.GameManager;
import com.github.zelmothedragon.cube.core.entity.Entity;
import com.github.zelmothedragon.cube.core.entity.behavior.Controllable;
import com.github.zelmothedragon.cube.core.entity.data.Mandelbrot;
import com.github.zelmothedragon.cube.core.entity.geometry.Rectangle;
import com.github.zelmothedragon.cube.core.entity.image.Image;
import com.github.zelmothedragon.cube.core.graphic.Renderer;
import com.github.zelmothedragon.cube.core.input.GamePad;
import com.github.zelmothedragon.cube.pixel.graphic.Pixels;

/**
 * Ensemble de Mandelbrot.
 *
 * @author MOSELLE Maxime
 */
public class MandelbrotSystem extends AbstractSystem {

    /**
     * Entité de Mandelbrot.
     */
    private final Entity mandelbrot;

    /**
     * Constructeur. Constuire un système, une seule instance est nécessaire
     * pour le fonctionnemenr global de l'application. Le système doit être
     * instancier dans le gestionnaire de système.
     *
     * @param manager Gestionnaire du jeu
     * @param priority Priorié d'exécuter du système
     */
    MandelbrotSystem(final GameManager manager, final int priority) {
        super(manager, priority);
        this.mandelbrot = manager.getFactory().createMandelbrot();
    }

    @Override
    public void update() {

        if (mandelbrot.hasComponent(Controllable.class)) {
            var data = mandelbrot.getComponent(Mandelbrot.class);
            if (manager.getInputs().isKeyPressed(GamePad.ACTION)) {
                var scale = data.getScale();
                scale += 1;
                data.setScale(scale);
            }
            if (manager.getInputs().isKeyPressed(GamePad.OPTION)) {
                var iteration = data.getIteration();
                iteration += 1;
                data.setIteration(iteration);
            }
        }

        if (manager.getInputs().isKeyPressed(GamePad.BACK)) {
            this.disable();
        }
    }

    @Override
    public void draw(final Renderer<?> renderer) {

        var data = mandelbrot.getComponent(Mandelbrot.class);
        var rectangle = mandelbrot.getComponent(Rectangle.class);
        var image = mandelbrot.getComponent(Image.class);
        var buffer = (int[]) image.getRawData();

        var w = rectangle.getWidth();
        var h = rectangle.getHeight();
        for (var y = 0; y < h; y++) {
            for (var x = 0; x < w; x++) {
                var xp = (x - w / 2.0) / data.getScale();
                var yp = (y - h / 2.0) / data.getScale();
                var color = calculatePoint(data.getIteration(), xp, yp);
                buffer[x + y * w] = color;
            }
        }
        renderer.drawImage(0, 0, image);
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
            color = Pixels.COLOR_BLACK;
        } else {
            var hue = n / (float) iteration;
            color = Pixels.convertHSB(hue, 0.5f, 1);
        }
        return color;
    }

}
