package com.github.zelmothedragon.cube.core.input;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Gestionnaire des entrées du joueur. Une instance unique de cette classe est
 * requise pour le fonctionnement de l'application. Le gestionnaire doit être
 * accessible depuis le conteneur du jeu.
 *
 * @see GameContainer
 *
 * @author MOSELLE Maxime
 */
public final class InputManager {

    /**
     * Liste des touches de la manettes.
     */
    private final Set<Key> keys;

    /**
     * Position abcisse du curseur.
     */
    private double cursorX;

    /**
     * Position ordonnée du curseur.
     */
    private double cursorY;

    /**
     * Constructeur. Construit un gestionnaire des entrées du joueur, pour le
     * bon fonctionnement du programme cette classe doit être instanciée une
     * seul fois.
     */
    public InputManager() {
        this.keys = new HashSet<>(GamePad.values().length);
        this.cursorX = 0.0;
        this.cursorY = 0.0;
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
    public boolean isKeyPressed(final GamePad name) {
        return keys
                .stream()
                .filter(k -> Objects.equals(k.getName(), name))
                .findFirst()
                .orElse(Key.EMPTY)
                .isPressed();
    }

    /**
     * Accesseur, indique si la touche est relâchée ou non.
     *
     * @param name Nom de la touche
     *
     * @return La valeur <code>true</code> si la touche est relâchée, sinon
     * <code>false</code>
     */
    public boolean isKeyReleased(final GamePad name) {
        return keys
                .stream()
                .filter(k -> Objects.equals(k.getName(), name))
                .findFirst()
                .orElse(Key.EMPTY)
                .isReleased();
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
                .forEach(Key::pressed);
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
                .forEach(Key::released);
    }

    /**
     * Déplacer le curseur. Cette méthode permet le traitement technique hors de
     * la boucle principale du jeu.
     *
     * @param cursorX Position abcisse
     * @param cursorY Position ordonnée
     */
    public void cursorMoved(final double cursorX, final double cursorY) {
        this.cursorX = cursorX;
        this.cursorY = cursorY;
    }

    /**
     * Accesseur, obtenir la position du curseur en abcisse.
     *
     * @return La position du cuseur en abcisse
     */
    public int getCursorX() {
        return (int) cursorX;
    }

    /**
     * Accesseur, obtenir la position du curseur en ordonnée.
     *
     * @return La position du curseur en ordonnée
     */
    public int getCursorY() {
        return (int) cursorY;
    }

}
