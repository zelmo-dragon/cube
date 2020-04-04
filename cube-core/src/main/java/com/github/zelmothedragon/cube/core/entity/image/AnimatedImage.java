package com.github.zelmothedragon.cube.core.entity.image;

import com.github.zelmothedragon.cube.core.entity.Component;
import com.github.zelmothedragon.cube.core.entity.geometry.Orientation;
import com.github.zelmothedragon.cube.core.entity.geometry.Rectangle;

public interface AnimatedImage<T> extends Component {

    void update();

    void play();

    void pause();

    void stop();

    boolean isPlaying();

    Image<T> getCurrentImage();

    int getImageWidth();

    int getImageHeight();

    int getDuration();

    int getCount();

    void addOffset(final Orientation orientation, Rectangle rectangle);

    Rectangle getOffset(final Orientation orientation);

    void setOrientation(Orientation orientation);

    void setOffset(final int xOffset, final int yOffset);

    Orientation getOrientation();

    Rectangle getCurrentOffset();
}
