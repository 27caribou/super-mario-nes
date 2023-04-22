package com.tngo.mario.framework2;

import com.tngo.mario.Game;
import com.tngo.mario.objects2.CanvasItem;
import com.tngo.mario.objects2.GameObject;
import com.tngo.mario.objects2.Player;
import com.tngo.mario.utils.KeyboardInput;

import java.awt.*;
import java.util.Set;

public class Level {

    protected Handler handler;
    protected QuadTree qtree;
    int playerIndex;
    Rectangle query;
    Set<GameObject> queryResult;

    public Level( Game game ) {

        handler = new Handler();
        qtree = new QuadTree( new Rectangle( Game.WIDTH, Game.HEIGHT ), 4 );

        testQTree();
        testQTreeQuery();
//        createTestLevel();
//        game.addKeyListener( new KeyboardInput( (Player) handler.getItem(playerIndex) ));
    }

    public void tick() {
        handler.tick();
    }

    public void render( Graphics g ) {

        g.setColor(Color.black);
        g.fillRect(0,0, Game.WIDTH, Game.HEIGHT);

        handler.render(g);
        qtree.display(g);

        if ( query != null ) {
            g.setColor( Color.green );
            g.drawRect( query.x, query.y, query.width, query.height );
            for ( GameObject object : queryResult ) {
                Rectangle rect = object.getBounds();
                g.drawRect( rect.x, rect.y, rect.width, rect.height );
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
        Player player = new Player( 100, Game.HEIGHT - 200, 20, 50, "green", "Player" );
        handler.addItem( player );
        qtree.insert( player );
    }

    public void testQTree() {
        for ( int i = 0; i < 80; i++ ) {
            int randX = (int)( Math.random() * Game.WIDTH );
            int randY = (int)( Math.random() * Game.HEIGHT );
            GameObject object = new GameObject( randX, randY, 32, 32, "white", "brick" );
            handler.addItem( object );
            qtree.insert( object );
        }
    }

    public void testQTreeQuery() {
        int size = 150;
        int randX = (int)( (Math.random() * Game.WIDTH) - size );
        int randY = (int)( (Math.random() * Game.HEIGHT) - size );
        query = new Rectangle( randX, randY, size, size );

        queryResult = qtree.query( query );
    }

}
