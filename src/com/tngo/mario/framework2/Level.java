package com.tngo.mario.framework2;

import com.tngo.mario.Game;
import com.tngo.mario.objects2.CanvasItem;
import com.tngo.mario.objects2.GameObject;

import java.awt.*;

public class Level {

    protected Handler handler;

    public Level() {

        handler = new Handler();

        createTestLevel();
    }

    public void tick() {
        handler.tick();
    }

    public void render( Graphics g ) {

        g.setColor(Color.black);
        g.fillRect(0,0, Game.WIDTH, Game.HEIGHT);

        handler.render(g);

    }

    public void createTestLevel() {

        for ( int yy = 0; yy < Game.HEIGHT + 32; yy += 32 ) {
            handler.addCanvasItem( new CanvasItem( 0, yy, 32, 32, "white" ));
            handler.addCanvasItem( new CanvasItem( Game.WIDTH - 32, yy, 32, 32, "white" ));
        }

        for ( int xx = 0; xx < Game.WIDTH + 32; xx += 32 ) {
            handler.addCanvasItem( new CanvasItem( xx, Game.HEIGHT - 32, 32, 32, "white" ));
        }

//        handler.addCanvasItem( new CanvasItem( 100, Game.HEIGHT - 200, 20, 50, "blue" ));
        handler.addCanvasItem( new GameObject( 100, Game.HEIGHT - 200, 20, 50, "green", "Player" ));

    }

}
