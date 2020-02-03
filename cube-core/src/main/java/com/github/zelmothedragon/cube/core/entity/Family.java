package com.github.zelmothedragon.cube.core.entity;

/**
 * Famille d'entité. A la différence ou chaque entité possède un identifiant
 * unique de type <code>java.util.UUID</code> pour le désigner, Cette
 * énumération désigne une famille d'entité.
 *
 * @author MOSELLE Maxime
 */
@Deprecated
public enum Family implements Component {

    /**
     * Personnage par défaut.
     */
    PLAYER,
    /**
     * Débogage.
     */
    DEBUG,
    /**
     * Désigne les entités ne faisant parties d'aucune famille. Il est
     * préférable d'utiliser cette valeur plutôt que la valeur
     * <code>null</code>.
     */
    EMPTY;

}
