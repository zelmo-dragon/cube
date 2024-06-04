package com.github.zelmodragon.cube.core.model;

/**
 * Nature d'un bloc.
 *
 * @author MOSELLE Maxime
 */
public enum Block {

    /**
     * Solide. Le bloc ne peut pas être traversé.
     */
    SOLID,
    /**
     * Vide. Le bloc n'interagit pas avec son environnement.
     */
    VOID;
}
