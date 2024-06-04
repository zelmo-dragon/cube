package com.github.zelmodragon.cube.core.system;

import com.github.zelmodragon.cube.core.graphic.Renderer;
import com.github.zelmodragon.cube.core.GameManager;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Gestionnaire des systèmes. Une instance unique de cette classe est requise
 * pour le fonctionnement de l'application. Le gestionnaire doit être accessible
 * depuis le conteneur du jeu.
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
     * fonctionnement du programme cette classe doit être instanciée une seule
     * fois.
     *
     * @param manager Gestionnaire du jeu
     */
    public SystemManager(final GameManager manager) {
        world = Arrays.asList(
                new MenuSystem(manager, 100),
                new CameraSystem(manager, 200),
                new CollisionSystem(manager, 201),
                new PlayerSystem(manager, 202),
                new BackgroundImageMapSystem(manager, 301),
                new ShadowImageMapSystem(manager, 302),
                new GroundImageMapSystem(manager, 303),
                new ImageSystem(manager, 304),
                new AnimatedImageSystem(manager, 305),
                new ForegroundImageMapSystem(manager, 306),
                new SkyImageMapSystem(manager, 307),
                new MandelbrotSystem(manager, 800),
                new TestSystem(manager, 901),
                new DebugSystem(manager, Integer.MAX_VALUE)
        );

        // Ce type de liste est immuable, mais supporte le tri.
        Collections.sort(world);

        disable(MandelbrotSystem.class);
        disable(DebugSystem.class);
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
     *
     * @param renderer Gestionnaire de rendu graphique
     */
    public void draw(final Renderer<?> renderer) {
        world
                .stream()
                .filter(AbstractSystem::isEnabled)
                .forEach(s -> s.draw(renderer));
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

    /**
     * Indiquer si un système est actif ou non.
     *
     * @param system Type de système
     * @return La valeur <code>true</code> si le système est actif, sinon la
     * valeur <code>false</code> est retournée dans tous les autres cas
     */
    public boolean isEnabled(final Class<? extends AbstractSystem> system) {
        return world
                .stream()
                .filter(s -> Objects.equals(s.getClass(), system))
                .map(AbstractSystem::isEnabled)
                .findAny()
                .orElse(Boolean.FALSE);
    }

}
