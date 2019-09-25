package com.github.zelmothedragon.cube.core.util.lang;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

/**
 * Génère une chaîne de caractères représentant les données d'un objet en
 * utilisant une approche fonctionnelle. Utile pour redéfinir la méthode
 * <code>toString</code>.
 *
 *
 * @param <T> Type quelconque
 * @author MOSELLE Maxime
 */
public final class ToString<T> {

    /**
     * Instance cible pour générer une chaîne de caractères représentant les
     * données.
     */
    private final T target;

    /**
     * Regroupe toutes les méthodes permettant la génération d'une chaîne de
     * caractères représentant les données associé à un nom. Ce nom est
     * généralement le nom de l'attribut. La méthode, quant à elle est
     * générallement un accesseur.
     */
    private final Map<String, Function<T, ?>> methods;

    /**
     * Constructeur. Ce constructeur est privé, afin de faciliter l'emploi de
     * cette classe, utiliser la méthode <code>of</code> et chaîner les appels
     * de méthodes.
     *
     * @param target Instance cible
     */
    private ToString(final T target) {
        this.target = target;
        this.methods = new HashMap<>();
    }

    /**
     * Point d'entrée de cette classe pour commencer à construire la
     * représentation textuelle d'un objet.
     *
     * @param <T> Type quelconque
     * @param target Instance cible
     * @return Une instance de cette classe afin de chaîner les appels de
     * méthodes
     */
    public static <T> ToString<T> of(final T target) {
        return new ToString<>(Objects.requireNonNull(target));
    }

    /**
     * Ajouter une propriété pour générer la représentation textuelle de
     * l'objet.
     *
     * @param <R> Type de retour de la méthode
     * @param name Nom de l'attribut
     * @param method Méthode permettant de renvoyer la valeur de l'attribut,
     * généralement un accesseur
     * @return Une instance de cette classe afin de chaîner les appels de
     * méthodes
     */
    public <R> ToString<T> with(final String name, final Function<T, R> method) {
        methods.put(name, method);
        return this;
    }

    /**
     * Opération de terminaison, génère une chaîne de caractères représentant
     * les données d'un objet.
     *
     * @return La représentation textuelle
     */
    public String get() {
        StringBuilder sb = new StringBuilder();
        sb
                .append(target.getClass().getSimpleName())
                .append("{");

        methods.forEach((k, v) -> {
            sb
                    .append(k)
                    .append("=")
                    .append(String.valueOf(v.apply(target)))
                    .append(", ");
        });
        sb.append("}");
        sb.deleteCharAt(sb.lastIndexOf(","));
        return sb.toString();
    }

}
