package com.tngo.mario.framework2;

import com.tngo.mario.Game;
import com.tngo.mario.objects2.CanvasItem;
import com.tngo.mario.objects2.GameObject;
import com.tngo.mario.objects2.Player;
import com.tngo.mario.utils.KeyboardInput;

import java.awt.*;

public class Level {

    protected Handler handler;
    int playerIndex;

    public Level( Game game ) {

        handler = new Handler();

        createTestLevel();
        game.addKeyListener( new KeyboardInput( (Player) handler.getItem(playerIndex) ));
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
            handler.addItem( new CanvasItem( 0, yy, 32, 32, "white" ));
            handler.addItem( new CanvasItem( Game.WIDTH - 32, yy, 32, 32, "white" ));
        }

        for ( int xx = 0; xx < Game.WIDTH + 32; xx += 32 ) {
            handler.addItem( new CanvasItem( xx, Game.HEIGHT - 32, 32, 32, "white" ));
        }

        playerIndex = handler.getSize();
        handler.addItem( new Player( 100, Game.HEIGHT - 200, 20, 50, "green", "Player" ) );
    }

}
