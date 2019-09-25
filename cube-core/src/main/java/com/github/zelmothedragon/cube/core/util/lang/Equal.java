package com.github.zelmothedragon.cube.core.util.lang;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

/**
 * Génère le calcul de la méthode <code>equals</code> des objets en utilisant
 * une approche fonctionnelle. Lors de la redéfinition de la méthode
 * <code>equals</code>, il est également nécessaire de redéfinir la méthode
 * <code>hashCode</code>. La classe <code>java.util.Objects</code> propose déjà
 * une manière simple de cette méthode.
 *
 * @param <T> Type quelconque
 * @author MOSELLE Maxime
 */
public final class Equal<T> {

    /**
     * Instance initiale pour le calcul de l'égalité.
     */
    private final T me;

    /**
     * Instance cible pour le calcul de l'égalité.
     */
    private final Object target;

    /**
     * Liste de fonctions utilisées pour la comparaison.
     */
    private final List<Function<T, ?>> methods;

    /**
     * Constructeur. Ce constructeur est privé, afin de faciliter l'emploi de
     * cette classe, utiliser la méthode <code>of</code> et chaîner les appels
     * de méthodes.
     *
     * @param me Instance initiale
     * @param target Instance cible
     */
    private Equal(final T me, final Object target) {
        this.me = me;
        this.target = target;
        this.methods = new ArrayList<>();
    }

    /**
     * Point d'entrée de cette classe pour commencer à construire le calcul de
     * l'égalité entre deux objets.
     *
     * @param <T> Type quelconque
     * @param me Instance initiale
     * @param target Instance cible
     * @return Une instance de cette classe afin de chaîner les appels de
     * méthodes
     */
    public static <T> Equal<T> of(final T me, final Object target) {
        return new Equal<>(
                Objects.requireNonNull(me),
                Objects.requireNonNull(target)
        );
    }

    /**
     * Ajouter une méthode utilisée pour le calcul de l'égalité. L'appel de
     * cette méthode peut être chaîner autant de fois que nécessaire.
     *
     * @param <R> Type de retour de la méthode
     * @param method Une méthode de l'instance initiale, en général des
     * accesseurs sont employés
     * @return Une instance de cette classe afin de chaîner les appels de
     * méthodes
     */
    public <R> Equal<T> with(final Function<T, R> method) {
        methods.add(method);
        return this;
    }

    /**
     * Opération de terminaison, résout le calcul de l'égalité.
     *
     * @return La valeur <code>true</code> si les deux objets sont identiques,
     * sinon la valeur <code>false</code>
     */
    public boolean get() {
        boolean eq;
        if (me == target) {
            eq = true;
        } else if (!Objects.equals(me.getClass(), target.getClass())) {
            eq = false;
        } else {
            var other = (T) target;
            eq = methods
                    .stream()
                    .allMatch(p -> Objects.equals(p.apply(me), p.apply(other)));
        }
        return eq;
    }

}
