package com.github.zelmothedragon.cube.core.asset;

import com.github.zelmothedragon.cube.core.graphic.Sprite;

/**
 * Gestionnaire de ressources externe. Une instance unique de cette classe est
 * requise pour le fonctionnement de l'application. Le gestionnaire doit être
 * accessible depuis le conteneur du jeu.
 *
 * @see GameContainer
 *
 * @author MOSELLE Maxime
 */
public interface AssetManager {

    String DEBUG_BACKGROUND_IMAGE = "/assets/images/background.png";

    String DEBUG_WOOD_IMAGE = "/assets/images/wood.png";

    String DEBUG_PLAYER_IMAGE = "/assets/images/npc_test.png";

    String DEBUG_8X8_TEXT_NO_SHADOW = "/assets/fonts/8x8_text_white_no_shadow.png";

    String DEBUG_8X8_TEXT_SHADOW = "/assets/fonts/8x8_text_white_shadow.png";

    String DEBUG_8X8_TEXT_MAP = "/assets/fonts/8x8_text.txt";

    /**
     * Charger une image.
     *
     * @param path Chemin d'accès à la ressouce
     * @return Une image
     */
    Sprite loadSprite(String path);

    /**
     * Charger la cartographie d'une police de caractères.
     *
     * @param path Chemin d'accès à la ressouce
     * @return La cartographie d'une police de caractères
     */
    String LoadFontMap(String path);
}
