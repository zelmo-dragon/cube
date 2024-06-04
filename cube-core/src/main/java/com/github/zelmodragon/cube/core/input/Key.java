package com.github.zelmodragon.cube.core.input;

import com.github.zelmodragon.cube.core.util.lang.Equal;
import com.github.zelmodragon.cube.core.util.lang.ToString;
import java.io.Serializable;
import java.util.Objects;

/**
 * Touche de la manette.
 *
 * @author MOSELLE Maxime
 */
public final class Key implements Serializable {

    /**
     * Numéro de série.
     */
    private static final long serialVersionUID = -4613165600205365885L;

    /**
     * Touche factice.
     */
    public static final Key EMPTY = new Key(GamePad.EMPTY, -1);

    /**
     * Nom de la touche.
     */
    private final GamePad name;

    /**
     * Code technique de la touche.
     */
    private final int keyCode;

    /**
     * Indique si la touche est appuyée durant la boucle de la mise à jour du
     * jeu.
     */
    private boolean pressed;

    private int pressing;

    private int absorbing;

    /**
     * Constructeur. Construit une touche avec un nom associé avec le code
     * physique de la touche.
     *
     * @param name Nom de la touche
     * @param keyCode Code technique de la touche
     */
    public Key(final GamePad name, final int keyCode) {
        this.name = name;
        this.keyCode = keyCode;
        this.pressed = false;
        this.pressing = 0;
        this.absorbing = 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, keyCode);
    }

    @Override
    public boolean equals(final Object obj) {
        return Equal
                .with(Key::getName)
                .thenWith(Key::getKeyCode)
                .apply(this, obj);
    }

    @Override
    public String toString() {
        return ToString
                .with("name", Key::getName)
                .thenWith("keyCode", Key::getKeyCode)
                .thenWith("pressed", Key::isPressed)
                .apply(this);
    }

    /**
     * Basculer l'état de la touche.
     *
     * @param toggle État de la touche
     */
    public void toggle(boolean toggle) {
        pressed = toggle;
    }

    /**
     * Accesseur, indique si la touche est appuyée ou non.
     *
     * @return La valeur <code>true</code> si la touche est appuyée, sinon
     * <code>false</code>
     */
    public boolean isPressed() {
        return pressed;
    }

    /**
     * Accesseur, obtenir le nom de la touche.
     *
     * @return Le nom de la touche
     */
    public GamePad getName() {
        return name;
    }

    /**
     * Accesseur, obtenir le code technique de la touche.
     *
     * @return Le code technique de la touche
     */
    public int getKeyCode() {
        return keyCode;
    }

}
