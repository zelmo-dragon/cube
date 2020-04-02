package com.github.zelmothedragon.cube.awt.engine;

import com.github.zelmothedragon.cube.awt.asset.ResourceManager;
import com.github.zelmothedragon.cube.awt.event.CursorEvent;
import com.github.zelmothedragon.cube.awt.event.KeyboardEvent;
import com.github.zelmothedragon.cube.awt.graphic.RendererAWT;
import com.github.zelmothedragon.cube.core.GameManager;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Objects;
import javax.swing.JFrame;

public final class Display {

    private static final int SCALE = 4;

    private static final int WIDTH = 320;

    private static final int HEIGHT = WIDTH / 16 * 9;

    private final JFrame window;

    private final Canvas canvas;

    private final RendererAWT renderer;

    private final GameManager manager;

    public Display() {
        this.window = new JFrame();
        this.canvas = new Canvas();
        this.renderer = new RendererAWT(WIDTH, HEIGHT);
        this.manager = new GameManager(new ResourceManager());

        this.canvas.setPreferredSize(new Dimension(SCALE * WIDTH, SCALE * HEIGHT));
        this.canvas.setBackground(Color.BLACK);

        var keyboard = new KeyboardEvent(manager.getInputs());
        this.window.addKeyListener(keyboard);

        var mouse = new CursorEvent(manager.getInputs());
        this.window.addMouseListener(mouse);
        this.window.addMouseMotionListener(mouse);
        this.window.addMouseWheelListener(mouse);

        this.window.add(canvas);
        this.window.pack();

        this.window.setTitle("Java AWT");
        this.window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.window.setLocationRelativeTo(null);
        this.window.setResizable(false);
        this.window.setVisible(true);
    }

    void update() {
        manager.getInputs().update();
        manager.getSystems().update();
    }

    void draw() {
        var bs = canvas.getBufferStrategy();
        if (Objects.nonNull(bs)) {
            renderer.clear();
            manager.getSystems().draw(renderer);
            var g2d = bs.getDrawGraphics();
            var image = renderer.getImage();
            g2d.drawImage(image, 0, 0, canvas.getWidth(), canvas.getHeight(), null);
            g2d.dispose();
            bs.show();
        } else {
            canvas.createBufferStrategy(2);
        }
        Toolkit.getDefaultToolkit().sync();
    }

}
