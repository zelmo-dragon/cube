package com.github.zelmothedragon.cube.awt.event;

import com.github.zelmothedragon.cube.core.input.GamePad;
import com.github.zelmothedragon.cube.core.input.InputManager;
import java.awt.GraphicsEnvironment;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Objects;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 * Gestion du clavier pour Java AWT.
 *
 * @author MOSELLE Maxime
 */
public final class KeyboardEvent extends KeyAdapter {

    private final InputManager manager;

    public KeyboardEvent(final InputManager manager) {
        this.manager = manager;
        loadKeys(manager);
    }

    @Override
    public void keyPressed(final KeyEvent e) {
        var window = (JFrame) e.getSource();
        SwingUtilities.invokeLater(() -> toggleScreen(window, e));
        manager.keyPressed(e.getKeyCode());
        e.consume();
    }

    @Override
    public void keyReleased(final KeyEvent e) {
        manager.keyReleased(e.getKeyCode());
        e.consume();
    }

    /**
     * Basculer l'affichage en plein écran ou en mode fenêtré. Par défaut le
     * basculement se fait avec la touche <code>F11</code>.
     *
     * @param stage Stage courant
     * @param event Événement du clavier
     */
    private static void toggleScreen(final JFrame window, final KeyEvent event) {
        if (Objects.equals(event.getKeyCode(), KeyEvent.VK_F11)) {
            var screen = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
            window.dispose();
            if (screen.isFullScreenSupported() && !window.isUndecorated()) {
                window.setUndecorated(true);
                screen.setFullScreenWindow(window);
            } else {
                window.setUndecorated(false);
                screen.setFullScreenWindow(null);
            }
            window.setVisible(true);
        }
    }

    /**
     * Charger la configuration par défaut pour le gestionnaire des entrées.
     *
     * @param manager Gestionnaire des entrées
     */
    private static void loadKeys(final InputManager manager) {
        manager.assign(GamePad.UP, KeyEvent.VK_UP);
        manager.assign(GamePad.DOWN, KeyEvent.VK_DOWN);
        manager.assign(GamePad.LEFT, KeyEvent.VK_LEFT);
        manager.assign(GamePad.RIGHT, KeyEvent.VK_RIGHT);
        manager.assign(GamePad.ACTION, KeyEvent.VK_A);
        manager.assign(GamePad.OPTION, KeyEvent.VK_Q);
        manager.assign(GamePad.BACK, KeyEvent.VK_Z);
        manager.assign(GamePad.START, KeyEvent.VK_ENTER);
        manager.assign(GamePad.SELECT, KeyEvent.VK_BACK_SPACE);
    }
}
