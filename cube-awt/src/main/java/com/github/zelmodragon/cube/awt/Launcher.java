package com.github.zelmodragon.cube.awt;

import com.github.zelmodragon.cube.awt.engine.Engine;

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

        System.setProperty("sun.java2d.opengl", "True");
        Thread.currentThread().setName("cube");
        var engine = new Engine();
        engine.start();
    }

}
