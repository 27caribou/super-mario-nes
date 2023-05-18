package com.tngo.mario.tests;

import com.tngo.mario.Game;
import com.tngo.mario.framework2.Handler;
import com.tngo.mario.framework2.QuadTree;
import com.tngo.mario.objects2.GameObject;
import com.tngo.mario.objects2.Player;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.List;

public class CollisionTest extends LevelTest {

    Line2D testLine;
    List<Rectangle> lineQuery;

    public CollisionTest( Game game ) {
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

    public void tick( Handler handler, QuadTree tree ) {
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
}
