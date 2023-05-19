package com.tngo.mario.framework2;

import com.tngo.mario.Game;
import com.tngo.mario.objects2.CanvasItem;
import com.tngo.mario.objects2.GameObject;
import com.tngo.mario.objects2.Player;
import com.tngo.mario.utils.KeyboardInput;

import com.tngo.mario.tests.CollisionTest;
import com.tngo.mario.tests.LevelTest;
import com.tngo.mario.tests.QTreeTest;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Level {

    protected Handler handler;
    protected QuadTree qtree;
    int playerIndex;

    LevelTest test1, test2;

    public Level( Game game ) {
        handler = new Handler();
        qtree = new QuadTree( new Rectangle( Game.WIDTH, Game.HEIGHT ), 4 );

        test1 = new QTreeTest(handler);
        test2 = new CollisionTest(game, 2);
//        createTestLevel();

        findPlayer();
        game.addKeyListener( new KeyboardInput( (Player) handler.getItem(playerIndex) ));
    }

    public void tick() {
        handler.tick();

        qtree.flush();
        for ( int i = 0; i < handler.getSize(); i++ ) {
            qtree.insert( (GameObject) handler.getItem(i) );
        }

        test1.tick( handler, qtree );
        test2.tick( handler, qtree );
    }

    public void render( Graphics g ) {

        g.setColor(Color.black);
        g.fillRect(0,0, Game.WIDTH, Game.HEIGHT);

        handler.render(g);
//        qtree.display(g);

        test1.render(g);
        test2.render(g);
    }

    private void findPlayer() {
        for ( int i = 0; i < handler.getSize(); i++ ) {
            if ( ((GameObject) handler.getItem(i)).getType() == "Player" ) {
                playerIndex = i;
                break;
            }
        }
    }

    public void createTestLevel() {

        for ( int yy = 0; yy < Game.HEIGHT + 32; yy += 32 ) {
            GameObject item = new GameObject( 0, yy, 32, 32, "white", "brick" );
            GameObject item2 = new GameObject( Game.WIDTH - 32, yy, 32, 32, "white", "brick" );
            handler.addItem( item );
            qtree.insert( item );
            handler.addItem( item2 );
            qtree.insert( item2 );
        }

        for ( int xx = 0; xx < Game.WIDTH + 32; xx += 32 ) {
            GameObject item = new GameObject( xx, Game.HEIGHT - 32, 32, 32, "white", "brick" );
            handler.addItem( item );
            qtree.insert( item );
        }

        playerIndex = handler.getSize();
        Player player = new Player( 100, Game.HEIGHT - 200, 20, 50, "green" );
        handler.addItem( player );
        qtree.insert( player );
    }

}
