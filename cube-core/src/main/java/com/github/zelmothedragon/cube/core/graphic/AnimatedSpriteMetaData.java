package com.github.zelmothedragon.cube.core.graphic;

import com.github.zelmothedragon.cube.core.entity.Component;
import com.github.zelmothedragon.cube.core.entity.geometry.Orientation;
import com.github.zelmothedragon.cube.core.entity.geometry.Rectangle;
import java.util.EnumMap;
import java.util.Map;

/**
 * Méta-données d'une image animée.
 *
 * @author MOSELLE Maxime
 */
public final class AnimatedSpriteMetaData implements Component {

    /**
     * Orientations possibles.
     */
    private final Map<Orientation, Rectangle> orientations;

    /**
     * Constructeur par défaut.
     */
    public AnimatedSpriteMetaData() {
        this.orientations = new EnumMap<>(Orientation.class);
    }

    /**
     * Ajouter un orientation.
     *
     * @param orientation Orientation
     * @param rectangle Emplacement
     */
    public void addOrientation(final Orientation orientation, Rectangle rectangle) {
        this.orientations.put(orientation, rectangle);
    }

    /**
     * Obtenir l'emplacement en fonction d'une orientation.
     *
     * @param orientation Orientation
     * @return L'Emplacement
     */
    public Rectangle getOrientation(final Orientation orientation) {
        return orientations.getOrDefault(orientation, new Rectangle());
    }

}
