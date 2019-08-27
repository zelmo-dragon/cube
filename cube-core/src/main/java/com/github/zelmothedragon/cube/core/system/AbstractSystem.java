package com.github.zelmothedragon.cube.core.system;

import com.github.zelmothedragon.cube.core.GameContainer;

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
     */
    public abstract void draw();

    /**
     * Accesseur, indique si le système est actif ou non.
     *
     * @return La valeur <code>true</code> si le système est actif, sinon
     * <code>false</code>
     */
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * Muttateur, modifier l'état d'activité du système.
     *
     * @param enabled Indique si le système est actif ou non
     */
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

}
