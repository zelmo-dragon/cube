package com.github.zelmothedragon.cube.core.entity.image;

import com.github.zelmothedragon.cube.core.entity.Component;

public interface AnimatedImage<T> extends Component{

    void update();

    void play();

    void pause();

    void stop();

    boolean isPlaying();

    Image<T> getCurrentImage();

    int getDuration();

    int getCount();
    
    void setOffset(int xOffset, int yOffset);
}
