package com.github.zelmothedragon.cube.awt.graphic;

import com.github.zelmothedragon.cube.core.graphic.Renderer;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

public final class RendererAWT extends Renderer {

    private final BufferedImage image;

    public RendererAWT(final int width, final int height) {
        super(width, height);
        this.image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    }

    @Override
    public void clear() {
        super.clear();
    }

    public Image getImage() {
        var dataBuffer = (DataBufferInt) image.getRaster().getDataBuffer();
        var data = dataBuffer.getData();
        System.arraycopy(buffer, 0, data, 0, data.length);
        return image;
    }

}
