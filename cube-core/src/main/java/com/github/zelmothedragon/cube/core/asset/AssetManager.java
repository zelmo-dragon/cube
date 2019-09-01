package com.github.zelmothedragon.cube.core.asset;

import com.github.zelmothedragon.cube.core.graphic.Sprite;

/**
 *
 * @author MOSELLE Maxime
 */
public interface AssetManager {

    String DEBUG_BACKGROUND_IMAGE = "/assets/images/background.png";

    String DEBUG_WOOD_IMAGE = "/assets/images/wood.png";

    /**
     * Charger une image.
     *
     * @param path Chemin d'accès à la ressouce
     * @return Une image
     */
    Sprite loadSprite(String path);
}
