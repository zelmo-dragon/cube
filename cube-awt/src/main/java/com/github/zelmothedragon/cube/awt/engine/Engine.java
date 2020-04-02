package com.github.zelmothedragon.cube.awt.engine;

public final class Engine implements Runnable {

    private static final double PULSE = 1e9 / 60.0;

    private static final boolean LOCK_FRAME_RATE = false;

    private final Thread loop;

    private final Display display;

    private volatile boolean running;

    public Engine() {
        this.loop = new Thread(this, "java-game-awt");
        this.display = new Display();
        this.running = false;
    }

    public synchronized void start() {
        this.running = true;
        this.loop.start();
    }

    public synchronized void stop() {
        this.running = false;
        try {
            this.loop.join();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    private void update() {
        display.update();
    }

    private void draw() {
        display.draw();
    }

    @Override
    public void run() {

        double delta = 0.0;
        long now;
        long lastTime = System.nanoTime();

        while (running) {

            now = System.nanoTime();
            delta += (now - lastTime) / PULSE;
            lastTime = now;

            while (delta >= 1) {
                update();
                delta--;
            }

            draw();

            if (LOCK_FRAME_RATE) {
                long sleepTime = (long) ((now - System.nanoTime() + PULSE) / 1e6);
                if (sleepTime < 1) {
                    sleepTime = 1;
                }
                try {
                    Thread.sleep(sleepTime);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

}
