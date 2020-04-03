package com.github.zelmothedragon.cube.core.graphic;

import com.github.zelmothedragon.cube.core.entity.image.ImageMap;
import com.github.zelmothedragon.cube.core.entity.image.FontImage;
import com.github.zelmothedragon.cube.core.entity.image.Image;
import com.github.zelmothedragon.cube.core.entity.image.AnimatedImage;

public interface Renderer<T> {

    void clear();

    void drawRectangle(int x, int y, int w, int h, int color);

    void drawFillRectangle(int x, int y, int w, int h, int color);

    void drawCircle(int x, int y, int radius, int color);

    void drawFillCircle(int x, int y, int radius, int color);

    void drawGradientCircle(int x, int y, int radius, int color);

    void drawLine(int x0, int y0, int x1, int y1, int color);

    void drawImage(int x, int y, Image<T> image);

    void drawImage(int x, int y, AnimatedImage<T> image);

    void drawImage(int x, int y, FontImage<T> image, String text);

    void drawImage(int x, int y, ImageMap<T> image);

    void setOffset(int xOffset, int yOffset);

    void resetOffset();

}
