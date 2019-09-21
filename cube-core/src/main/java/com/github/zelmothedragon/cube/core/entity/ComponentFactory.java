package com.github.zelmothedragon.cube.core.entity;

/**
 * Fabrique de composant.
 *
 * @author MOSELLE Maxime
 */
public final class ComponentFactory {

    /**
     * Constructeur interne.
     */
    private ComponentFactory() {
        // RAS
    }

    /**
     * Instancier un composant dynamiquement
     *
     * @param <T> Type générique de composant
     * @param type Classe du composant
     * @return Une nouvelle instance d'un composant
     */
    public static <T extends Component> T newInstance(final Class<T> type) {
        T component;
        try {
            component = (T) type.getDeclaredConstructor().newInstance();

        } catch (Exception ex) {
            // /!\ Attention invocation dynamique
            ex.printStackTrace();
            component = null;
        }
        return component;
    }
}
