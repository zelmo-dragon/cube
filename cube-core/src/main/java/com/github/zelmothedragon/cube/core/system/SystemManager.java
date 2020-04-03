package com.github.zelmothedragon.cube.core.system;

import com.github.zelmothedragon.cube.core.GameManager;
import com.github.zelmothedragon.cube.core.graphic.Renderer;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

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
     * L'ensemble de tous les systèmes existants. L'ensemble des systèmes
     * définissent la logique fonctionnement et le rendu graphique du jeu, en
     * somme l'écosystème du jeu.
     */
    private final List<AbstractSystem> world;

    /**
     * Constructeur. Construit un gestionnaire de système, pour le bon
     * fonctionnement du programme cette classe doit être instanciée une seul
     * fois.
     *
     * @param manager Gestionnaire du jeu
     */
    public SystemManager(final GameManager manager) {
        world = Arrays.asList(
                new CameraSystem(manager, 50),
                new BackgroundSystem(manager, 200),
                //new MandelbrotSystem(manager, 500),
                new CollisionSystem(manager, 501),
                new PlayerSystem(manager, 510),
                new DebugSystem(manager, Integer.MAX_VALUE)
        );

        // Ce type de liste est immuable, mais supporte le tri.
        Collections.sort(world);
        world.forEach(AbstractSystem::enable);
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
    public void draw(final Renderer g2d) {
        world
                .stream()
                .filter(AbstractSystem::isEnabled)
                .forEach(s -> s.draw(g2d));
    }

    /**
     * Activer un système.
     *
     * @param system Type de système
     */
    public void enbable(final Class<? extends AbstractSystem> system) {
        world
                .stream()
                .filter(s -> Objects.equals(s.getClass(), system))
                .findAny()
                .ifPresent(AbstractSystem::enable);
    }

    /**
     * Désactiver un système.
     *
     * @param system Type de système
     */
    public void disable(final Class<? extends AbstractSystem> system) {
        world
                .stream()
                .filter(s -> Objects.equals(s.getClass(), system))
                .findAny()
                .ifPresent(AbstractSystem::disable);
    }

}
