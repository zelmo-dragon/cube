package com.github.zelmothedragon.cube.core.entity.image;

import com.github.zelmothedragon.cube.core.entity.Component;

public interface FontImage<T> extends Component{

    String CHAR_SEPARATOR = "";

    String LINE_SEPARATOR = "\n";

    Image<T> getImage(String character);

    int getImageWidth();

    int getImageHeight();
}
