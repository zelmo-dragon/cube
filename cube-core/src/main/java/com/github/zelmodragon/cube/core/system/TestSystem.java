package com.github.zelmodragon.cube.core.system;

import com.github.zelmodragon.cube.core.graphic.Renderer;
import com.github.zelmodragon.cube.core.GameManager;

public final class TestSystem extends AbstractSystem {

    TestSystem(final GameManager manager, final int priority) {
        super(manager, priority);
    }

    @Override
    public void update() {
    }

    @Override
    public void draw(Renderer<?> renderer) {
    }

}
