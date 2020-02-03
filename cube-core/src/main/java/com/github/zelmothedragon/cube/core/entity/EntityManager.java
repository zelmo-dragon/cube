package com.github.zelmothedragon.cube.core.entity;

import com.github.zelmothedragon.cube.core.GameContainer;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
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
     * @return L'identifiant unique de l'entité
     */
    public UUID newEntity() {
        var id = UUID.randomUUID();
        var entities = new HashMap<UUID, Component>();
        entities.put(id, Component.EMPTY);
        data.put(Component.EmptyComponent.class, entities);
        return id;
    }

    /**
     * Ajouter un composant.
     *
     * @param id Identifiant de l'entité
     * @param component Composant
     */
    public void add(final UUID id, final Component component) {
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
     * Obtenir un composant.
     *
     * @param <T> Type générique de composant
     * @param id Identifiant de l'entité
     * @param type Classe du composant
     * @return Le composant recherché
     */
    public <T extends Component> T get(final UUID id, final Class<T> type) {
        final T component;
        if (data.containsKey(type)) {
            var entities = data.get(type);
            component = (T) entities.get(id);
        } else {
            component = null;
        }
        return component;
    }

    /**
     * Obtenir toutes les entités par classe de composant.
     *
     * @param <T> Type générique de composant
     * @param type Classe du composant
     * @return L'ensemble des entités associé à ce type de composant, peut
     * retourner la valeur <code>Collections.emptyMap()</code> si aucune entité
     * ne correspond à ce type de composant
     */
    public <T extends Component> Map<UUID, T> get(final Class<T> type) {
        final Map<UUID, Component> entities;
        if (data.containsKey(type)) {
            entities = data.get(type);
        } else {
            entities = Collections.emptyMap();
        }
        return (Map<UUID, T>) entities;
    }

    /**
     * Supprimer un composant.
     *
     * @param <T> Type générique de composant
     * @param id Identifiant de l'entité
     * @param type Classe du composant
     */
    public <T extends Component> void remove(final UUID id, final Class<T> type) {
        if (data.containsKey(type)) {
            var entities = data.get(type);
            entities.remove(id);
        }
    }

    /**
     * Supprimer un composant pour toutes les entités.
     *
     * @param <T> Type générique de composant
     * @param type Classe du composant
     */
    public <T extends Component> void remove(final Class<T> type) {
        data.remove(type);
    }

    /**
     * Vérifier l'existence d'un composant pour une entité.
     *
     * @param <T> ype générique de composant
     * @param id Identifiant de l'entité
     * @param type Classe du composant
     * @return La valeur <code>true</code> si une instance de composant existe,
     * sinon la valeur <code>false</code>
     */
    public <T extends Component> boolean contains(final UUID id, final Class<T> type) {
        boolean contain;
        if (data.containsKey(type)) {
            var entities = data.get(type);
            var component = entities.get(id);
            contain = Objects.nonNull(component);
        } else {
            contain = false;
        }
        return contain;
    }

}
