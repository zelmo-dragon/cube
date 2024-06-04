package com.github.zelmodragon.cube.core.input;

/**
 * Noms des touches de la manette du jeu.
 *
 * @author MOSELLE Maxime
 */
public enum GamePad {

    /**
     * Vide. Aucune action sur cette touche.
     */
    EMPTY,
    /**
     * Touche directionnelle haut.
     */
    UP,
    /**
     * Touche directionnelle bas.
     */
    DOWN,
    /**
     * Touche directionnelle gauche.
     */
    LEFT,
    /**
     * Touche directionnelle droite.
     */
    RIGHT,
    /**
     * Bouton action.
     */
    ACTION,
    /**
     * Bouton d'option.
     */
    OPTION,
    /**
     * Bouton retour.
     */
    BACK,
    /**
     * Bouton de démarrage.
     */
    START,
    /**
     * Bouton de sélection.
     */
    SELECT,
    /**
     * Bouton de déboggage.
     */
    DEBUG;

    /**
     * Constructeur interne. Pas d'instanciation pour une énumération.
     */
    private GamePad() {
        // RAS
    }

}
