package com.github.zelmothedragon.cube.core.system;

import com.github.zelmothedragon.cube.core.GameContainer;
import java.util.Arrays;
import java.util.List;

/**
 * Gestionnaire des systèmes.
 *
 * @author MOSELLE Maxime
 */
public final class SystemManager {

    /**
     * La liste de tous les systèmes existants. L'ensemble des systèmes
     * définissent la logique fonctionnement et le rendu graphique du jeu, en
     * somme l'écosystème du jeu.
     */
    private final List<AbstractSystem> world;

    /**
     * Constructeur. Construit un gestionnaire de système, pour le bon
     * fonctionnement du programme cette classe doit être instanciée une seul
     * fois.
     *
     * @param gc Conteneur du contexte du jeu
     */
    public SystemManager(final GameContainer gc) {
        this.world = Arrays.asList(
                new DebugSystem(gc, 0)
        );

        // Pour test uniquement.
        this.world.forEach(s -> s.setEnabled(true));
    }

    /**
     * Mettre à jour la logique métier du jeu. Uniquement pour les systèmes
     * actifs.
     */
    public void update() {
        world
                .stream()
                .filter(AbstractSystem::isEnabled)
                .forEach(AbstractSystem::update);
    }

    /**
     * Mettre à jour le rendu graphique du jeu. Uniquement pour les systèmes
     * actifs.
     */
    public void draw() {
        world
                .stream()
                .filter(AbstractSystem::isEnabled)
                .forEach(AbstractSystem::draw);
    }

}
