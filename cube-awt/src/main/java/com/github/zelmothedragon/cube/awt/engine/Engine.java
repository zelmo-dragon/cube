package com.github.zelmothedragon.cube.awt.engine;

/**
 * Coeur du moteur du jeu. Cadence la boucle principale du jeu.
 *
 * @author MOSELLE Maxime
 */
public final class Engine implements Runnable {

    /**
     * Fréquence de pulsation de la boucle principale du jeu.
     */
    private static final double PULSE = 1e9 / 60.0;

    /**
     * Vérrouiller la mise à jour de l'affichage. Pour déboggage uniquement,
     * dans la pratique laisser la valeur <code>true</code>.
     */
    private static final boolean LOCK_FRAME_RATE = false;

    /**
     * Processus principal de la boucle du jeu.
     */
    private final Thread loop;

    /**
     * Affichage principal.
     */
    private final Display display;

    /**
     * Indique sur la boucle est en cours d'exécution.
     */
    private volatile boolean running;

    /**
     * Constructeur. Construit le moteur du jeu pour l'ordonnancement de tous
     * les traitements.
     */
    public Engine() {
        this.loop = new Thread(this, "cube-awt");
        this.display = new Display();
        this.running = false;
    }

    /**
     * Démarrer le moteur.
     */
    public synchronized void start() {
        this.running = true;
        this.loop.start();
    }

    /**
     * Stopper le moteur.
     */
    public synchronized void stop() {
        this.running = false;
        try {
            this.loop.join();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Mettre à jour la logique métier du jeu.
     */
    private void update() {
        display.update();
    }

    /**
     * Mettre à jour le rendu graphique du jeu.
     */
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
                // Appelée x60 par seconde
                update();
                delta--;
            }

            // Appelée autant de fois que possible 
            // après le traitement de la méthode "update"
            draw();

            if (LOCK_FRAME_RATE) {
                // OU
                // Appelée x60 par seconde
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
