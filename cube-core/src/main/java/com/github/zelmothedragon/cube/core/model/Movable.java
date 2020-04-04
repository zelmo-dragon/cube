package com.github.zelmothedragon.cube.core.model;

/**
 * Composant marqueur qui indique qu'une entité est mobile.
 *
 * @author MOSELLE Maxime
 */
public final class Movable implements Component {

    /**
     * Singleton. Instance unique permet de marquer un entité commme mobile.
     */
    public static final Movable INSTANCE = new Movable();

    /**
     * Constructeur interne.
     */
    private Movable() {
        // RAS
    }

}
