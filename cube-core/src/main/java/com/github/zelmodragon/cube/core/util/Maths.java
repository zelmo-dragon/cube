package com.github.zelmodragon.cube.core.util;

/**
 * Fonctions mathématiques avancées.
 *
 * @author MOSELLE Maxime
 */
public final class Maths {

    /**
     * Constructeur interne, pas d'instanciation.
     */
    private Maths() {
        throw new AssertionError("No instances for you!");
    }

    public static float map(
            final float value,
            final float start0,
            final float stop0,
            final float start1,
            final float stop1) {

        return start1 + (stop1 - start1) * ((value - start0) / (stop0 - start0));
    }

}
