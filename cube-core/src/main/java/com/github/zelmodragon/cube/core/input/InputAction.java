package com.github.zelmodragon.cube.core.input;

/**
 * Représente une fonction pour le traitement d'un événement d'une touche.
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
