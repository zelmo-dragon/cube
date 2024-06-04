package com.github.zelmodragon.cube.fx;

import com.github.zelmodragon.cube.fx.engine.Display;

/**
 * Lanceur de l'application.
 *
 * @author MOSELLE Maxime
 */
public final class Launcher {

    /**
     * Constructeur interne. Pas d'instanciation.
     */
    private Launcher() {
        // RAS
    }

    /**
     * Point d'entrée du programme.
     *
     * @param args Arguments systèmes
     */
    public static void main(final String[] args) {

        Thread.currentThread().setName("cube");
        Display.launch(args);
    }

}
