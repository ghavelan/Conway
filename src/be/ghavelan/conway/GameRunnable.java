package be.ghavelan.conway;

/**
 * Created by Gilles Havelange on 12/07/15.
 */
public class GameRunnable implements Runnable {

    public static final int DELAY = 100;
    public Thread gameThread;
    boolean suspended = false;
    private GameComponent component;

    public GameRunnable(GameComponent aComponent) {

        component = aComponent;

    }

    @Override
    public void run() {

        while (true) {

            try {
                component.update();

                Thread.sleep(DELAY);

                synchronized (this) {

                    while (suspended) {

                        wait();
                    }

                }
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }

        }
    }

    public void start() {

        if (gameThread == null) {

            gameThread = new Thread(this);
            gameThread.start();
        }
    }

    public void suspend() {

        suspended = true;
        gameThread = null;
    }

    synchronized void resume() {

        suspended = false;
        notify();
    }

}
