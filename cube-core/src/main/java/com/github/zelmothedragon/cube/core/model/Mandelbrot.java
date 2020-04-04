package com.github.zelmothedragon.cube.core.model;

import com.github.zelmothedragon.cube.core.util.lang.Equal;
import com.github.zelmothedragon.cube.core.util.lang.ToString;
import java.util.Objects;

/**
 * Données de l'ensemble de MandelBrot
 *
 * @author MOSELLE Maxime
 */
public final class Mandelbrot implements Component {

    private static final int DEFAULT_ITERATION = 100;

    private static final float DEFAULT_SCALE = 200;

    private int iteration;

    private float scale;

    public Mandelbrot(int iteration, float scale) {
        this.iteration = iteration;
        this.scale = scale;
    }

    public Mandelbrot() {
        this(DEFAULT_ITERATION, DEFAULT_SCALE);
    }

    @Override
    public int hashCode() {
        return Objects.hash(iteration, scale);
    }

    @Override
    public boolean equals(Object obj) {
        return Equal
                .with(Mandelbrot::getIteration)
                .thenWith(Mandelbrot::getScale)
                .apply(this, obj);
    }

    @Override
    public String toString() {
        return ToString
                .with("iteration", Mandelbrot::getIteration)
                .thenWith("scale", Mandelbrot::getScale)
                .apply(this);
    }

    public int getIteration() {
        return iteration;
    }

    public void setIteration(int iteration) {
        this.iteration = iteration;
    }

    public float getScale() {
        return scale;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }

}
