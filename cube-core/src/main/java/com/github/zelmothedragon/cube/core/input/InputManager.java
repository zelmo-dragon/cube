package com.github.zelmothedragon.cube.core.input;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Gestionnaire des entrées du joueur.
 *
 * @author MOSELLE Maxime
 */
public final class InputManager {

    /**
     * Liste des touches de la manettes.
     */
    private final Set<Key> keys;

    /**
     * Constructeur. Construit un gestionnaire des entrées du joueur, pour le
     * bon fonctionnement du programme cette classe doit être instanciée une
     * seul fois.
     */
    public InputManager() {
        this.keys = new HashSet<>(GamePad.values().length);
    }

    /**
     * Synchroniser toutes les touches durant la phase de mise à jour du jeu.
     */
    public void update() { 
        keys.forEach(Key::update);
    }

    /**
     * Accesseur, indique si la touche est appuyée ou non.
     *
     * @param name Nom de la touche
     *
     * @return La valeur <code>true</code> si la touche est appuyée, sinon
     * <code>false</code>
     */
    public boolean isKeyUp(final GamePad name) {
        return keys
                .stream()
                .filter(k -> Objects.equals(k.getName(), name))
                .findFirst()
                .orElse(Key.EMPTY)
                .isKeyUp();
    }

    /**
     * Accesseur, indique si la touche est relâchée ou non.
     *
     * @param name Nom de la touche
     *
     * @return La valeur <code>true</code> si la touche est relâchée, sinon
     * <code>false</code>
     */
    public boolean isKeyDown(final GamePad name) {
        return keys
                .stream()
                .filter(k -> Objects.equals(k.getName(), name))
                .findFirst()
                .orElse(Key.EMPTY)
                .isKeyDown();
    }

    /**
     * Assigner une nouvelle touche.
     *
     * @param name Nom de la touche
     * @param keyCode Code technique de la touche
     */
    public void assign(final GamePad name, final int keyCode) {
        keys.add(new Key(name, keyCode));
    }

    /**
     * Appuyer sur une touche. Cette méthode permet le traitement technique hors
     * temps de la boucle principale du jeu.
     *
     * @param keyCode Code technique de la touche
     */
    public void keyPressed(final int keyCode) {
        keys
                .stream()
                .filter(k -> Objects.equals(k.getKeyCode(), keyCode))
                .forEach(Key::keyPressed);
    }

    /**
     * Relâcher une touche. Cette méthode permet le traitement technique hors de
     * la boucle principale du jeu.
     *
     * @param keyCode Code technique de la touche
     */
    public void keyReleased(final int keyCode) {
        keys
                .stream()
                .filter(k -> Objects.equals(k.getKeyCode(), keyCode))
                .forEach(Key::keyReleased);
    }

}
