package com.github.zelmothedragon.cube.awt.asset;

import com.github.zelmothedragon.cube.core.asset.AssetManager;
import com.github.zelmothedragon.cube.core.entity.geometry.Dimension;
import com.github.zelmothedragon.cube.core.graphic.FontSprite;
import com.github.zelmothedragon.cube.core.graphic.Sprite;
import com.github.zelmothedragon.cube.core.graphic.TileMap;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import javax.imageio.ImageIO;

/**
 * Implémentation référence du gestionnaire des ressouces numériques. Basé sur
 * la bibliothèque Java AWT.
 *
 * @author MOSELLE Maxime
 */
public final class ResourceManager implements AssetManager {

    /**
     * Constructeur. Construit le gestionnaire des ressources numériques.
     */
    public ResourceManager() {
        // RAS
    }

    @Override
    public Sprite loadSprite(final String path) {

        Sprite sprite;
        try (var stream = getClass().getResourceAsStream(path)) {
            var image = ImageIO.read(stream);
            var width = image.getWidth();
            var height = image.getHeight();
            var dimension = new Dimension(width, height);
            var buffer = image.getRGB(0, 0, width, height, null, 0, width);
            sprite = new Sprite(dimension, buffer);
        } catch (IOException ex) {
            ex.printStackTrace();
            sprite = null;
        }
        return sprite;
    }

    @Override
    public String loadFontMap(final String path) {
        String font;
        try (var stream = getClass().getResourceAsStream(path)) {
            var data = stream.readAllBytes();
            font = new String(data, StandardCharsets.UTF_8);
            font = font.replaceAll(FontSprite.LINE_SEPARATOR, FontSprite.CHAR_SEPARATOR);
        } catch (IOException ex) {
            font = null;
            ex.printStackTrace();
        }
        return font;
    }

    @Override
    public int[][] loadMap(final String path) {
        int[][] map;
        try (var stream = getClass().getResourceAsStream(path)) {

            var data = stream.readAllBytes();
            var text = new String(data, StandardCharsets.UTF_8);
            var lines = text.split(TileMap.CSV_LINE_SEPARATOR);
            var height = lines.length;
            var width = lines[0].split(TileMap.CSV_CELL_SEPARATOR).length;
            map = new int[height][width];

            for (var y = 0; y < map.length; y++) {
                var line = lines[y];
                var cells = line.split(TileMap.CSV_CELL_SEPARATOR);
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
