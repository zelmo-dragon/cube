package com.github.zelmothedragon.cube.core.entity.image;

import com.github.zelmothedragon.cube.core.entity.Component;

public interface ImageMap<T> extends Component {

    String CELL_SEPARATOR = ",";

    String LINE_SEPARATOR = "\n";

    Image<T> getImage(int x, int y);

    int getImageWidth();

    int getImageHeight();

    int getMapWidth();

    int getMapHeight();
}
