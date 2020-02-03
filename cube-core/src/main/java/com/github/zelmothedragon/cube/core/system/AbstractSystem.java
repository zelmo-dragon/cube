package com.github.zelmothedragon.cube.core.system;

import com.github.zelmothedragon.cube.core.GameContainer;
import com.github.zelmothedragon.cube.core.graphic.Render;

/**
 * Système générique.
 *
 * @author MOSELLE Maxime
 */
public abstract class AbstractSystem implements Comparable<AbstractSystem> {

    /**
     * Conteneur du contexte du jeu.
     */
    protected final GameContainer gc;

    /**
     * Priorité d'exécution du système.
     */
    private final int priority;

    /**
     * Indique si ce système est actif ou non.
     */
    private boolean enabled;

    /**
     * Constructeur. Construit un système spécialisé dans un traitement. Le
     * système est inactif par défaut.
     *
     * @param gc Conteneur du contexte du jeu
     * @param priority Priorité d'exécution du système
     */
    protected AbstractSystem(final GameContainer gc, final int priority) {
        this.gc = gc;
        this.priority = priority;
        this.enabled = false;
    }

    @Override
    public int compareTo(AbstractSystem s) {
        return Integer.compare(priority, s.priority);
    }

    /**
     * Mettre à jour la logique métier du jeu.
     */
    public abstract void update();

    /**
     * Mettre à jour le rendu graphique du jeu.
     *
     * @param g2d Gestionnaire de rendu graphique
     */
    public abstract void draw(Render g2d);

    /**
     * Activer le système.
     */
    public void enable() {
        this.enabled = true;
    }

    /**
     * Désactiver le système.
     */
    public void disable() {
        this.enabled = false;
    }

    /**
     * Accesseur, indiquer si le système est actif ou non.
     *
     * @return La valeur <code>true</code> si le système est actif, sinon
     * <code>false</code>
     */
    public boolean isEnabled() {
        return enabled;
    }

}
