package com.github.zelmothedragon.cube.core.system;

import com.github.zelmothedragon.cube.core.GameContainer;
import com.github.zelmothedragon.cube.core.graphic.Render;
import java.util.List;

/**
 * Gestionnaire des systèmes. Une instance unique de cette classe est requise
 * pour le fonctionnement de l'application. Le gestionnaire doit être accessible
 * depuis le conteneur du jeu.
 *
 * @see GameContainer
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
        this.world = List.of(
                new InputSystem(gc, 10),
                new MoveSystem(gc, 20),
                new RenderSpriteSystem(gc, 30),
//                ,
//                new TestSystem(gc, 0),
                new DebugSystem(gc, Integer.MAX_VALUE)
        );

        // Pour test uniquement.
        this.world.forEach(AbstractSystem::enable);
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
     * Mettre à jour le rendu graphique du jeu.Uniquement pour les systèmes
     * actifs.
     *
     * @param g2d Gestionnaire de rendu graphique
     */
    public void draw(final Render g2d) {
        world
                .stream()
                .filter(AbstractSystem::isEnabled)
                .forEach(s -> s.draw(g2d));
    }

}
