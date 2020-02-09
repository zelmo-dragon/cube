package com.github.zelmothedragon.cube.core.system;

import com.github.zelmothedragon.cube.core.GameContainer;
import com.github.zelmothedragon.cube.core.entity.Entity;
import com.github.zelmothedragon.cube.core.entity.geometry.Camera;
import com.github.zelmothedragon.cube.core.entity.geometry.Point;
import com.github.zelmothedragon.cube.core.entity.geometry.Rectangle;
import com.github.zelmothedragon.cube.core.graphic.Render;

/**
 * Gestion de la caméra.
 *
 * @author MOSELLE Maxime
 */
public final class CameraSystem extends AbstractSystem {
    
    public CameraSystem(final GameContainer gc, final int priority) {
        super(gc, priority);
    }
    
    @Override
    public void update() {
        
        gc
                .getEntities()
                .filter(Camera.class)
                .stream()
                .forEach(CameraSystem::updateCamera);
        
    }
    
    @Override
    public void draw(final Render g2d) {
        
        gc
                .getEntities()
                .filter(Camera.class)
                .stream()
                .map(e -> e.getComponent(Camera.class))
                .forEach(c -> g2d.setOffset(c));
        
    }
    
    private static void updateCamera(final Entity entity) {
        
        var camera = entity.getComponent(Camera.class);
        if (entity.hasComponent(Point.class)) {
            var point = entity.getComponent(Point.class);
            camera.follow(point);
        } else if (entity.hasComponent(Rectangle.class)) {
            var rectangle = entity.getComponent(Rectangle.class);
            camera.follow(rectangle);
        } else {
            // RAS
        }
    }
    
}
