package com.github.zelmothedragon.cube.core.input;

/**
 * Représente un fonction pour le traitement d'un événement d'une touche.
 *
 * @author MOSELLE Maxime
 */
@FunctionalInterface
public interface InputAction {
    
    /**
     * Traitement de l'événement d'une touche.
     */
    void apply();
}
