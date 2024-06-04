package com.github.zelmodragon.cube.core.util.validation;

/**
 * Représente une erreur de validation d'un objet.
 *
 * @author MOSELLE Maxime
 */
public class ValidationException extends Exception {

    /**
     * Numéro de série.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constructeur. Construit une erreur de validation.
     *
     * @param message Message d'erreur
     */
    ValidationException(String message) {
        super(message);
    }

}
