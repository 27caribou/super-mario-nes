package com.tngo.mario.window;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable {

    private boolean running = false;
    private Thread thread;
    public synchronized void start() {
        if ( running ) return;

        running = true;
        thread = new Thread(this);
        thread.start();
    }

    public void run() {
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int updates = 0;
        int frames = 0;
        while ( running ) {
            long now = System.nanoTime();
            delta += ( now - lastTime ) / ns;
            lastTime = now;
            while ( delta >= 1 ) {
                tick();
                updates++;
                delta--;
            }
            render();
            frames++;

            if ( System.currentTimeMillis() - timer > 1000 ) {
                timer += 1000;
                System.out.println("FPS: " + frames + " TICKS: " + updates);
                frames = 0;
                updates = 0;
            }
        }
    }

    private void tick() {

    }

    private void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if ( bs == null ){
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();
        //////////////////////////////////////

        // Draw here
        g.setColor(Color.black);
        g.fillRect(0,0, getWidth(), getHeight());


        //////////////////////////////////////
        g.dispose();
        bs.show();
    }

    public static void main(String[] args) {
        new Window(800, 600, "Super Mario Game Prototype", new Game());
    }
}
