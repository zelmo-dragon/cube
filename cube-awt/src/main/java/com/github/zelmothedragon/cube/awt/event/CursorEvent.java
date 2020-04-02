package com.github.zelmothedragon.cube.awt.event;

import com.github.zelmothedragon.cube.core.input.InputManager;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Gestion de la souris pour Java AWT.
 *
 * @author MOSELLE Maxime
 */
public final class CursorEvent extends MouseAdapter {

    /**
     * Gestionnaire des événements d'entrée.
     */
    private final InputManager manager;

    /**
     * Constructeur. Constuire le gestionnaire événementielle de la souris.
     *
     * @param manager Gestionnaire des événements d'entrée
     */
    public CursorEvent(final InputManager manager) {
        this.manager = manager;
    }

    @Override
    public void mouseMoved(final MouseEvent e) {
        manager.cursorMoved(e.getX(), e.getY());
    }

}
