package com.github.zelmothedragon.cube.core.entity.image;

import com.github.zelmothedragon.cube.core.entity.Component;

public interface Image<T> extends Component {

    int EMPTY_INDEX = -1;

    int getIndex();

    T getRawData();

    int getWidth();

    int getHeight();
}
