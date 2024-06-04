package com.github.zelmodragon.cube.core.model;

import java.util.Objects;

/**
 * Identifie toutes les classes destinées à être des propriétés d'une entité.
 *
 * @author MOSELLE Maxime
 */
public interface Component {

    /**
     * Composant vide. Privilégier cette constante plutôt que la valeur
     * <code>null</code>.
     */
    Component EMPTY = new EmptyComponent();

    /**
     * Indiquer si le composant est vide.
     *
     * @return La valeur <code>true</code> si le composant est vide, sinon
     * <code>false</code>
     */
    default boolean isEmpty() {
        return Objects.equals(getClass(), EmptyComponent.class);
    }

    /**
     * Composant vide. Pas de comportement particulier.
     */
    static class EmptyComponent implements Component {

        /**
         * Constructeur interne.
         */
        private EmptyComponent() {
            // RAS
        }

    }
}
