package com.tngo.mario.framework2;

import com.tngo.mario.Game;

import java.awt.*;

public class Level {

    public Level() {

    }

    public void tick() {

    }

    public void render( Graphics g ) {

        g.setColor(Color.black);
        g.fillRect(0,0, Game.WIDTH, Game.HEIGHT);

    }

}
