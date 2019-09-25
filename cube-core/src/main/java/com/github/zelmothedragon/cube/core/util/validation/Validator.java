package com.github.zelmothedragon.cube.core.util.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Génère la validation d'un objet avec une approche fonctionnelle.
 *
 * @param <T> Type quelconque
 * @author MOSELLE Maxime
 */
public final class Validator<T> {

    /**
     * Instance cible de l'objet à valider.
     */
    private final T target;

    /**
     * Liste des erreurs survenues lors de la validation.
     */
    private final List<ValidationException> exceptions;

    /**
     * Constructeur. Ce constructeur est privé, afin de faciliter l'emploi de
     * cette classe, utiliser la méthode <code>of</code> et chaîner les appels
     * de méthodes.
     *
     * @param target Instance cible
     */
    private Validator(final T target) {
        this.target = target;
        this.exceptions = new ArrayList<>();
    }

    /**
     * Point d'entrée de cette classe pour commencer à construire la validation
     * d'un objet.
     *
     * @param <T> Type quelconque
     * @param target Instance cible
     * @return Une instance de cette classe afin de chaîner les appels de
     * méthodes
     */
    public static <T> Validator<T> of(final T target) {
        return new Validator<>(target);
    }

    /**
     * Ajouter un test de validation.
     *
     * @param predicate Clause conditionnelle pour valider l'objet
     * @param message Message d'erreur si la validation échoue
     * @return Une instance de cette classe afin de chaîner les appels de
     * méthodes
     */
    public Validator<T> validate(final Predicate<T> predicate, final String message) {

        if (!predicate.test(target)) {
            exceptions.add(new ValidationException(message));
        }
        return this;
    }

    /**
     * Ajouter un test de validation.
     *
     * @param <R> Type de retour de la méthode
     * @param projection Méthode permettant de renvoyer la valeur de l'attribut,
     * généralement un accesseur
     * @param predicate Clause conditionnelle le retour de la méthode
     * @param message Message d'erreur si la validation échoue
     * @return Une instance de cette classe afin de chaîner les appels de
     * méthodes
     */
    public <R> Validator<T> validate(
            final Function<T, R> projection,
            final Predicate<R> predicate,
            final String message) {

        return validate(projection.andThen(predicate::test)::apply, message);
    }

    /**
     * Opération de terminaison.Vérifie l'objet et lève une exception si la
     * validation échoue.
     *
     * @return L'instance de l'objet cible testé
     * @throws ValidationException Génère cette exception si la validation de
     * l'objet échoue
     */
    public T get() throws ValidationException {
        if (!exceptions.isEmpty()) {
            ValidationException exception = new ValidationException("Object validation fail");
            exceptions.forEach(exception::addSuppressed);
            throw exception;
        }
        return target;
    }
}
