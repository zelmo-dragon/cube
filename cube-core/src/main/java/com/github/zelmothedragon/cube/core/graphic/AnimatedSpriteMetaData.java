package com.github.zelmothedragon.cube.core.graphic;

import com.github.zelmothedragon.cube.core.entity.Component;
import com.github.zelmothedragon.cube.core.entity.geometry.Orientation;
import com.github.zelmothedragon.cube.core.entity.geometry.Rectangle;
import com.github.zelmothedragon.cube.core.util.lang.Equal;
import com.github.zelmothedragon.cube.core.util.lang.ToString;
import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;

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
     * Orientation actuelle. Si l'orientation prend la valeur
     * <code>Orientation.EMPTY</code> alors l'animation de l'image s'arrête, en
     * revanche le décalage n'est pas réinitialisé.
     */
    private Orientation orientation;

    /**
     * Décalage actuelle. Il permet de connaitre quelle portion de l'image
     * animée doit être déssinée à l'écran.
     */
    private Rectangle currentOffset;

    /**
     * Constructeur par défaut.
     */
    public AnimatedSpriteMetaData() {
        this.orientations = new EnumMap<>(Orientation.class);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orientation, currentOffset);
    }

    @Override
    public boolean equals(final Object obj) {
        return Equal
                .of(this, obj)
                .with(AnimatedSpriteMetaData::getOrientation)
                .with(AnimatedSpriteMetaData::getCurrentOffset)
                .get();
    }

    @Override
    public String toString() {
        return ToString
                .of(this)
                .with("orientation", AnimatedSpriteMetaData::getOrientation)
                .with("currentOffset", AnimatedSpriteMetaData::getCurrentOffset)
                .get();
    }

    /**
     * Ajouter un orientation.
     *
     * @param orientation Orientation
     * @param rectangle Emplacement
     */
    public void addOffset(final Orientation orientation, Rectangle rectangle) {
        this.orientations.put(orientation, rectangle);
    }

    /**
     * Obtenir l'emplacement en fonction d'une orientation.
     *
     * @param orientation Orientation
     * @return L'Emplacement
     */
    public Rectangle getOffset(final Orientation orientation) {
        return orientations.get(orientation);
    }

    /**
     * Modifier l'orientation et le décalage actuelle de l'image animée.
     *
     * @param orientation Orientation
     */
    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
        this.currentOffset = orientations.get(orientation);
    }

    /**
     * Accesseur, obtenir l'orientation courante.
     *
     * @return L'orientation courante
     */
    public Orientation getOrientation() {
        return orientation;
    }

    /**
     * Accesseur, obtenir le décalage courant.
     *
     * @return Le décalage courant
     */
    public Rectangle getCurrentOffset() {
        return currentOffset;
    }

}
