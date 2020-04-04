package com.github.zelmothedragon.cube.core.system;

import com.github.zelmothedragon.cube.core.GameManager;
import com.github.zelmothedragon.cube.core.model.Family;
import com.github.zelmothedragon.cube.core.graphic.Renderer;
import com.github.zelmothedragon.cube.core.input.GamePad;

public final class MenuSystem extends AbstractSystem {

    public MenuSystem(final GameManager manager, final int priority) {
        super(manager, priority);
    }

    @Override
    public void update() {
        manager.getInputs().computeIfKeyPressed(GamePad.DEBUG, this::buttonDebugAction);
        manager.getInputs().computeIfKeyPressed(GamePad.SELECT, this::buttonSelectAction);
    }

    @Override
    public void draw(Renderer<?> renderer) {
    }

    private void buttonDebugAction() {
        if (manager.getSystems().isEnabled(DebugSystem.class)) {
            manager.getSystems().disable(DebugSystem.class);
        } else {
            manager.getSystems().enbable(DebugSystem.class);
        }
    }

    private void buttonSelectAction() {
        if (manager.getSystems().isEnabled(MandelbrotSystem.class)) {
            manager.getEntities().remove(Family.MANDELBROT);
            manager.getSystems().disable(MandelbrotSystem.class);
        } else {
            manager.getFactory().createMandelbrot();
            manager.getSystems().enbable(MandelbrotSystem.class);
        }
    }

}
