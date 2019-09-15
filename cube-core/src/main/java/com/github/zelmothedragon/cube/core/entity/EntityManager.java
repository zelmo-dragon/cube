package com.github.zelmothedragon.cube.core.entity;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Gestionnaire d'entités. Une instance unique de cette classe est requise pour
 * le fonctionnement de l'application. Le gestionnaire doit être accessible
 * depuis le conteneur du jeu.
 *
 * @see GameContainer
 *
 * @author MOSELLE Maxime
 */
public final class EntityManager {

    /**
     * Toutes les entités du jeu.
     */
    private final Map<Class<? extends Component>, Map<UUID, Component>> data;

    /**
     * Constructeur. Construit un gestionnaire d'entités. Pour le bon
     * fonctionnement du programme cette classe doit être instanciée une seul
     * fois.
     */
    public EntityManager() {
        this.data = new HashMap<>();
    }

    /**
     * Construire une nouvelle entité.
     *
     * @param family Famille d'entité
     * @return L'identifiant unique de l'entité
     */
    public UUID newEntity(final Family family) {
        return addComponent(family);
    }

    /**
     * Ajouter un composant.
     *
     * @param id Identifiant de l'entité
     * @param component Composant
     */
    public void addComponent(final UUID id, final Component component) {
        final var type = component.getClass();
        if (data.containsKey(type)) {
            var entities = data.get(type);
            entities.put(id, component);
        } else {
            var entities = new HashMap<UUID, Component>();
            entities.put(id, component);
            data.put(type, entities);
        }
    }

    /**
     * Ajouter un composant. Une nouvelle entité est également créée pour
     * associer ce composant.
     *
     * @param component Composant
     * @return L'identifiant de l'entité
     */
    public UUID addComponent(final Component component) {
        final var type = component.getClass();
        final var id = UUID.randomUUID();
        if (data.containsKey(type)) {
            var entities = data.get(type);
            entities.put(id, component);
        } else {
            var entities = new HashMap<UUID, Component>();
            entities.put(id, component);
            data.put(type, entities);
        }
        return id;
    }

    /**
     * Obtenir un composant.
     *
     * @param <T> Type générique de composant
     * @param id Identifiant de l'entité
     * @param type Classe du composant
     * @return Le composant recherché, peut retourner la valeur
     * <code>Component.EMPTY</code> si le composant demandé n'existe pas
     */
    public <T extends Component> T getComponent(final UUID id, final Class<T> type) {
        final Component component;
        if (data.containsKey(type)) {
            var entities = data.get(type);
            component = entities.getOrDefault(id, Component.EMPTY);
        } else {
            component = Component.EMPTY;
        }
        return (T) component;
    }

    /**
     * Obtenir toutes les entités par classe de composant.
     *
     * @param type Classe du composant
     * @return L'ensemble des entités associé à ce type de composant, peut
     * retourner la valeur <code>Collections.emptyMap()</code> si aucune entité
     * ne correspond à ce type de composant
     */
    public Map<UUID, Component> getComponent(final Class<? extends Component> type) {
        final Map<UUID, Component> entities;
        if (data.containsKey(type)) {
            entities = data.get(type);
        } else {
            entities = Collections.emptyMap();
        }
        return entities;
    }

    /**
     * Supprimer un composant.
     *
     * @param id Identifiant de l'entité
     * @param type Classe du composant
     */
    public void removeComponent(final UUID id, final Class<? extends Component> type) {
        if (data.containsKey(type)) {
            var entities = data.get(type);
            entities.remove(id);
        }
    }

    /**
     * Supprimer un composant pour toutes les entités.
     *
     * @param type Classe du composant
     */
    public void removeComponent(final Class<? extends Component> type) {
        data.remove(type);
    }

}
