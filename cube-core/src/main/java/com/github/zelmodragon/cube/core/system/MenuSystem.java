package com.github.zelmodragon.cube.core.system;

import com.github.zelmodragon.cube.core.graphic.Renderer;
import com.github.zelmodragon.cube.core.GameManager;
import com.github.zelmodragon.cube.core.input.GamePad;
import com.github.zelmodragon.cube.core.model.Family;

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
