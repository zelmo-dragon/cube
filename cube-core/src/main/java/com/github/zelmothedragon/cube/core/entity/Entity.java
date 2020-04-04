package com.github.zelmothedragon.cube.core.entity;

import com.github.zelmothedragon.cube.core.component.Component;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

/**
 * Entité métier. Une entité contient toutes les données nécessaires pour le
 * fonctionnement du programme. Dans le cadre d'un jeu, modéliser des entités
 * avec une hiérarchie de classe n'est pas une solution satisfaisante.
 * L'héritage est solution trop rigide et peut évolutif. C'est pourquoi tous les
 * attributs seront stockés dans un dictionnaire de données type
 * <code>java.util.Map</code>.
 *
 * @author MOSELLE Maxime
 */
public final class Entity {

    /**
     * Identifiant unique.
     */
    private final UUID id;

    /**
     * Famille d'entité.
     */
    private final Family family;

    /**
     * Ensemble des propriétés de l'entité.
     */
    private final Map<Class<? extends Component>, Component> data;

    /**
     * Constructeur.Construit une entité sans propriété.
     *
     * @param family Famille d'entité
     */
    public Entity(final Family family) {
        this.id = UUID.randomUUID();
        this.family = family;
        this.data = new HashMap<>();

    }

    @Override
    public int hashCode() {
        // Pourquoi laissez-vous les IDE générer cette méthode
        // alors que Java possède déjà la solution ?
        return Objects.hash(id);
    }

    @Override
    public boolean equals(final Object obj) {
        // Parce qu'une méthode ne doit avoir q'un seul 'return'.
        final boolean eq;
        if (this == obj) {
            eq = true;
        } else if (Objects.isNull(obj)) {
            eq = false;
        } else {
            if (!Objects.equals(getClass(), obj.getClass())) {
                eq = false;
            } else {
                Entity other = (Entity) obj;
                eq = Objects.equals(id, other.id);
            }
        }
        return eq;
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append("Entity{id=")
                .append(id)
                .append(", family=")
                .append(family)
                .append(", components=")
                .append(data.size())
                .append("}")
                .toString();
    }

    /**
     * Ajouter un composant à cette entité. Une entité ne peut possède deux fois
     * le même composant.
     *
     * @param component Nouveau composant
     */
    public void addComponent(final Component component) {
        data.put(component.getClass(), component);
    }

    /**
     * Ajouter un composant à cette entité.Une entité ne peut possède deux fois
     * le même composant.
     *
     * @param type Type du composant
     * @param component Nouveau composant
     */
    public void addComponent(final Class<? extends Component> type, final Component component) {
        data.put(type, component);
    }

    /**
     * Supprimer un composant à cette entité.
     *
     * @param <C> Type générique de composant
     * @param type Type de composant
     */
    public <C extends Component> void removeComponent(final Class<C> type) {
        data.remove(type);
    }

    /**
     * Vérifier qu'un composant existe dans cette entité.
     *
     * @param <C> Type générique de composant
     * @param type ype de composant
     * @return La valeur <code>true</code> si le composant existe, sinon
     * <code>false</code> dans tous les autres cas
     */
    public <C extends Component> boolean hasComponent(final Class<C> type) {
        return data.containsKey(type);
    }

    /**
     * Obtenir un composant en fonction de son type.
     *
     * @param <C> Type générique de composant
     * @param type ype de composant
     * @return Le composant typé dynamiquement avec la généricité, peut
     * retourner la valeur <code>null</code> si le composant n'existe pas
     */
    public <C extends Component> C getComponent(final Class<C> type) {
        return (C) data.get(type);
    }

    /**
     * Accesseur, obtenir l'identifiant.
     *
     * @return L'identifiant unique, ne doit jamais être nul
     */
    public UUID getId() {
        return id;
    }

    /**
     * Accesseur, obtenir la famille d'entité.
     *
     * @return La famille d'entité, ne doit jamais être nulle
     */
    public Family getFamily() {
        return family;
    }

}
