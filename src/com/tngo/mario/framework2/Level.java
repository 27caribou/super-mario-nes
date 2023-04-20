package com.tngo.mario.framework2;

import com.tngo.mario.Game;

import java.awt.*;

public class Level {

    protected Handler handler;

    public Level() {

        handler = new Handler();
        handler.createTestLevel();

    }

    public void tick() {
        handler.tick();
    }

    public void render( Graphics g ) {

        g.setColor(Color.black);
        g.fillRect(0,0, Game.WIDTH, Game.HEIGHT);

        handler.render(g);

    }

}
