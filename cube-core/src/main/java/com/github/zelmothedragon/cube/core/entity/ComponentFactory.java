package com.github.zelmothedragon.cube.core.entity;

import com.github.zelmothedragon.cube.core.entity.behavior.Controllable;
import com.github.zelmothedragon.cube.core.entity.debug.Clock;
import com.github.zelmothedragon.cube.core.entity.geometry.Orientation;
import com.github.zelmothedragon.cube.core.entity.geometry.Rectangle;
import com.github.zelmothedragon.cube.core.entity.geometry.Vector;
import com.github.zelmothedragon.cube.core.graphic.AnimatedSprite;
import com.github.zelmothedragon.cube.core.graphic.AnimatedSpriteMetaData;
import com.github.zelmothedragon.cube.core.graphic.FontSprite;
import com.github.zelmothedragon.cube.core.graphic.Sprite;
import com.github.zelmothedragon.cube.core.graphic.TileMap;
import java.util.HashMap;
import java.util.Map;

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
            // TODO: Trouver un moyen plus propre d'implémenter
            // le design pattern NullObject
            var components = switcher();
            component = (T) components.get(type);
        } catch (Exception ex) {
            // /!\ Attention invocation dynamique
            ex.printStackTrace();
            component = null;
        }
        return component;
    }

    private static Map<Class<? extends Component>, Component> switcher() {
        var data = new HashMap<Class<? extends Component>, Component>();

        //behavior
        data.put(Controllable.class, Controllable.INSTANCE);

        //geometry
        data.put(Rectangle.class, new Rectangle());
        data.put(Vector.class, new Vector());
        
        //graphic
        var metaData = new AnimatedSpriteMetaData();
        metaData.addOffset(Orientation.EMPTY, new Rectangle());
        metaData.setOrientation(Orientation.EMPTY);
       
        data.put(Sprite.class, new Sprite(0, 0));
        data.put(FontSprite.class, new FontSprite(new Sprite(0, 0), "", 0, 0));
        data.put(AnimatedSprite.class, new AnimatedSprite(new Sprite(0, 0), 0, 0, 0, 0));
        data.put(AnimatedSpriteMetaData.class, metaData);
        data.put(TileMap.class, new TileMap(new Sprite(0, 0), 0, 0, new int[0][0]));

        // debug
        data.put(Clock.class, new Clock());

        return data;
    }
}
