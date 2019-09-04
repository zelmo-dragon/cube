package com.github.zelmothedragon.cube.fx.asset;

import com.github.zelmothedragon.cube.core.asset.AssetManager;
import com.github.zelmothedragon.cube.core.graphic.FontSprite;
import com.github.zelmothedragon.cube.core.graphic.Sprite;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import javafx.scene.image.Image;
import javafx.scene.image.PixelFormat;

/**
 * Implémentation référence du gestionnaire des ressouces numériques. Basé sur
 * la bibliothèque JavaFX.
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

        var image = new Image(path);
        var width = (int) image.getWidth();
        var height = (int) image.getHeight();
        var buffer = new int[width * height];

        image
                .getPixelReader()
                .getPixels(
                        0,
                        0,
                        width,
                        height,
                        PixelFormat.getIntArgbPreInstance(),
                        buffer,
                        0,
                        width
                );

        return new Sprite(width, height, buffer);

    }

    @Override
    public String LoadFontMap(final String path) {
        String font;
        try ( var stream = getClass().getResourceAsStream(path)) {
            var data = stream.readAllBytes();
            font = new String(data, StandardCharsets.UTF_8);
            font = font.replaceAll(FontSprite.LINE_SEPARATOR, FontSprite.CHAR_SEPARATOR);
        } catch (IOException ex) {
            font = null;
            ex.printStackTrace();
        }
        return font;
    }

}
