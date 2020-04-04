package com.github.zelmothedragon.cube.core.asset;

import com.github.zelmothedragon.cube.core.component.AnimatedImage;
import com.github.zelmothedragon.cube.core.component.FontImage;
import com.github.zelmothedragon.cube.core.component.Image;
import com.github.zelmothedragon.cube.core.component.ImageMap;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

/**
 * Gestionnaire de ressources externe. Une instance unique de cette classe est
 * requise pour le fonctionnement de l'application. Le gestionnaire doit être
 * accessible depuis le conteneur du jeu.
 *
 * @param <T> Type du format d'image
 * @author MOSELLE Maxime
 */
public interface AssetManager<T> {

    String DEBUG_BACKGROUND_IMAGE = "/assets/images/overworld.png";

    String DEBUG_BACKGROUND_MAP_LAYER_0 = "/assets/maps/debug.csv";

    String DEBUG_WOOD_IMAGE = "/assets/images/wood.png";

    String DEBUG_PLAYER_IMAGE = "/assets/images/npc_test.png";

    String DEBUG_8X8_TEXT_NO_SHADOW = "/assets/fonts/8x8_text_white_no_shadow.png";

    String DEBUG_8X8_TEXT_SHADOW = "/assets/fonts/8x8_text_white_shadow.png";

    String DEBUG_8X8_TEXT_MAP = "/assets/fonts/8x8_text.txt";

    /**
     * Charger une image vide.
     *
     * @param w Largeur
     * @param h Hauteur
     * @return Une nouvelle image vide
     */
    Image<T> loadImage(int w, int h);

    /**
     * Charger une image simple.
     *
     * @param imagePath Chemin de l'image
     * @return Une image
     */
    Image<T> loadImage(String imagePath);

    /**
     * Charger une feuille d'image pour l'animation.
     *
     * @param imagePath Chemin de l'image
     * @param w Largeur
     * @param h Hauteur
     * @param duration Délai de l'animation
     * @param count Nombre d'image pour une séquence d'animation
     * @return Une feuille d'image
     */
    AnimatedImage<T> loadAnimatedImage(String imagePath, int w, int h, int duration, int count);

    /**
     * Charger une feuille d'image de police de caractère.
     *
     * @param imagePath Chemin de l'image
     * @param mapPath Chemin de la cartographie de la feuille d'image
     * @param w Largeur d'un caractère
     * @param h Hauteur d'un caractère
     * @return Une feuille d'image
     */
    FontImage<T> loadFontImagge(String imagePath, String mapPath, int w, int h);

    /**
     * Charger une feuille d'image pour la génération de carte.
     *
     * @param imagePath Chemin de l'image
     * @param mapPath Chemin de la cartographie de la feuille d'image
     * @param w Largeur d'une tuile graphique
     * @param h Hauteur d'une tuile graphique
     * @return Une feuille d'image
     */
    ImageMap<T> loadImageMap(String imagePath, String mapPath, int w, int h);

    /**
     * Charger une ressource.
     *
     * @param path Chemin de la ressource
     * @return Un flux vers la ressource
     */
    public static InputStream loadResource(final String path) {
        return AssetManager.class.getResourceAsStream(path);
    }

    /**
     * Charger la cartographie d'une police de caractère.
     *
     * @param mapPath Chemin de la cartographie de la feuille
     * @return La cartographie
     */
    public static String loadFontMap(final String mapPath) {
        String font;
        try (var stream = loadResource(mapPath)) {
            var data = stream.readAllBytes();
            font = new String(data, StandardCharsets.UTF_8);
            font = font.replaceAll(FontImage.LINE_SEPARATOR, FontImage.CHAR_SEPARATOR);
        } catch (IOException ex) {
            font = null;
            ex.printStackTrace();
        }
        return font;
    }

    /**
     * Charger la cartographie pour la génération de carte.
     *
     * @param mapPath Chemin de la cartographie de la feuille
     * @return La cartographie
     */
    public static int[][] loadMap(final String mapPath) {
        int[][] map;
        try (var stream = loadResource(mapPath)) {

            var data = stream.readAllBytes();
            var text = new String(data, StandardCharsets.UTF_8);
            var lines = text.split(ImageMap.LINE_SEPARATOR);
            var height = lines.length;
            var width = lines[0].split(ImageMap.CELL_SEPARATOR).length;
            map = new int[height][width];

            for (var y = 0; y < map.length; y++) {
                var line = lines[y];
                var cells = line.split(ImageMap.CELL_SEPARATOR);
                for (var x = 0; x < map[0].length; x++) {
                    map[y][x] = Integer.valueOf(cells[x]);
                }
            }
        } catch (IOException ex) {
            map = new int[0][0];
            ex.printStackTrace();
        }
        return map;
    }
}
