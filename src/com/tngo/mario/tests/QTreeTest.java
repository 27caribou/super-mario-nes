package com.tngo.mario.tests;

import com.tngo.mario.Game;
import com.tngo.mario.framework2.Handler;
import com.tngo.mario.framework2.QuadTree;
import com.tngo.mario.objects2.GameObject;
import com.tngo.mario.objects2.Player;

import java.awt.*;
import java.util.Set;

public class QTreeTest extends LevelTest {

    Set<GameObject> query;
    int playerIndex;

    public QTreeTest( Handler handler ) {
        for ( int i = 0; i < 80; i++ ) {
            int randX = (int)( Math.random() * Game.WIDTH );
            int randY = (int)( Math.random() * Game.HEIGHT );
            GameObject object = new GameObject( randX, randY, 32, 32, "white", "brick" );
            handler.addItem( object );
        }

        int size = 75;
        int randX = (int)( (Math.random() * Game.WIDTH) - size );
        int randY = (int)( (Math.random() * Game.HEIGHT) - size );
        Player player = new Player( randX, randY, size, size, "red" );
        playerIndex = handler.getSize();
        handler.addItem( player );
    }

    public void tick( Handler handler, QuadTree tree ) {
        query = tree.query( ((Player) handler.getItem(playerIndex)).getBounds() );
    }

    public void render( Graphics g ) {
        g.setColor( Color.red );
        if ( query != null ) {
            for ( GameObject object : query ) {
                Rectangle rect = object.getBounds();
                g.drawRect( rect.x, rect.y, rect.width, rect.height );

                // Check where they were hit
            }
        }
    }
}
