package com.tngo.mario.framework2;

import com.tngo.mario.Game;
import com.tngo.mario.objects2.CanvasItem;
import com.tngo.mario.objects2.GameObject;
import com.tngo.mario.objects2.Player;
import com.tngo.mario.utils.KeyboardInput;

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
    Set<GameObject> query;
    Line2D testLine;
    List<Rectangle> lineQuery;

    public Level( Game game ) {
        handler = new Handler();
        qtree = new QuadTree( new Rectangle( Game.WIDTH, Game.HEIGHT ), 4 );

        testQTree();
//        createTestLevel();
        testCollision(game);
        game.addKeyListener( new KeyboardInput( (Player) handler.getItem(playerIndex) ));
    }

    public void tick() {
        handler.tick();

        qtree.flush();
        for ( int i = 0; i < handler.getSize(); i++ ) {
            qtree.insert( (GameObject) handler.getItem(i) );
        }
        query = qtree.query( ((Player) handler.getItem(playerIndex)).getBounds() );

        if ( testLine != null ) {
            lineQuery.clear();
            for ( int i = 0; i < handler.getSize(); i++ ) {
                Rectangle bounds = ((GameObject) handler.getItem(i)).getBounds();
                if ( testLine.intersects(bounds) ) {
                    lineQuery.add(bounds);
                }
            }
        }
    }

    public void render( Graphics g ) {

        g.setColor(Color.black);
        g.fillRect(0,0, Game.WIDTH, Game.HEIGHT);

        handler.render(g);
//        qtree.display(g);

        if ( query != null ) {
            g.setColor( Color.red );
            for ( GameObject object : query ) {
                Rectangle rect = object.getBounds();
                g.drawRect( rect.x, rect.y, rect.width, rect.height );

                // Check where they were hit
            }
        }

        if ( testLine != null ) {
            g.setColor( Color.yellow );
            Graphics2D g2 = (Graphics2D) g;
            g2.draw(testLine);

            if ( lineQuery.size() > 0 ) {
                for ( Rectangle rect : lineQuery ) {
                    g.drawRect( rect.x, rect.y, rect.width, rect.height );
                }
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
        }

        int size = 75;
        int randX = (int)( (Math.random() * Game.WIDTH) - size );
        int randY = (int)( (Math.random() * Game.HEIGHT) - size );
        playerIndex = handler.getSize();
        Player player = new Player( randX, randY, size, size, "red", "Player" );
        handler.addItem( player );
    }

    public void testCollision( Game game ) {

        testLine = new Line2D.Double();
        lineQuery = new ArrayList<>();
        testLine.setLine(20,20,20,20);

        game.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                testLine.setLine(e.getX(),e.getY(),e.getX(),e.getY());
            }
        });
        game.addMouseMotionListener(new MouseAdapter() {
            public void mouseDragged(MouseEvent e) {
                testLine.setLine(testLine.getX1(),testLine.getY1(),e.getX(),e.getY());
            }
        });
    }

}
