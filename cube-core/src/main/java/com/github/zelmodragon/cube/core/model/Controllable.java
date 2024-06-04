package com.github.zelmodragon.cube.core.model;

/**
 * Composant marqueur qui indique qu'une entité est contrôlable.
 *
 * @author MOSELLE Maxime
 */
public final class Controllable implements Component {

    /**
     * Singleton. Instance unique permet de marquer une entité comme
     * contrôlable.
     */
    public static final Controllable INSTANCE = new Controllable();

    /**
     * Constructeur interne.
     */
    private Controllable() {
        // RAS
    }

}
