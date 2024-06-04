package com.github.zelmodragon.cube.core.model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Gestionnaire d'entités. Une instance unique de cette classe est requise pour
 * le fonctionnement de l'application. Le gestionnaire doit être accessible
 * depuis le conteneur du jeu.
 *
 * @author MOSELLE Maxime
 */
public final class EntityManager {

    /**
     * Ensemble des entités.
     */
    private final Set<Entity> data;

    /**
     * Constructeur par défaut.
     */
    public EntityManager() {
        this.data = new HashSet<>();
    }

    /**
     * Obtenir une entité en fonction de son identifiant.
     *
     * @param id Identifiant unique
     * @return L'entité trouvée
     */
    public Entity get(final UUID id) {
        return data
                .stream()
                .filter(e -> Objects.equals(e.getId(), id))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("Entity not found for id " + id));
    }

    /**
     * Filter les entités en fonction d'un composant.
     *
     * @param type Le type du composant
     * @return L'ensemble des entités possédant le même composant
     */
    public Set<Entity> filter(final Class<? extends Component> type) {
        return data
                .stream()
                .filter(e -> e.hasComponent(type))
                .collect(Collectors.toSet());
    }

    /**
     * Obtenir des entités par famille.
     *
     * @param family Famille d'entité
     * @return L'ensemble des entités de même famille
     */
    public Set<Entity> filter(final Family family) {
        return data
                .stream()
                .filter(e -> Objects.equals(e.getFamily(), family))
                .collect(Collectors.toSet());
    }

    /**
     * Ajouter une nouvelle entité dans le gestionnaire.
     *
     * @param entity Nouvelle entité
     */
    public void add(final Entity entity) {
        data.add(entity);
    }

    /**
     * Supprimer une entité.
     *
     * @param id Identifiant unique
     * @return La valeur <code>true</code> si l'entité est supprimée
     */
    public boolean remove(final UUID id) {
        return data
                .removeIf(e -> Objects.equals(e.getId(), id));
    }

    /**
     * Supprimer une famille d'entité.
     *
     * @param family Famille d'entité
     * @return La valeur <code>true</code> si la famille d'entité est supprimée
     */
    public boolean remove(final Family family) {
        return data
                .removeIf(e -> Objects.equals(e.getFamily(), family));
    }

    /**
     * Vérifier l'existence d'une entité/
     *
     * @param id Identifiant unique
     * @return La valeur <code>true</code> si l'entité existe
     */
    public boolean hasEntity(final UUID id) {
        return data
                .stream()
                .filter(e -> Objects.equals(e.getId(), id))
                .findAny()
                .isPresent();
    }

}
