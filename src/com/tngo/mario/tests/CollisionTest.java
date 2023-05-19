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
    List<double[]> contactPoints;

    public CollisionTest( Game game ) {
        testLine = new Line2D.Double();
        lineQuery = new ArrayList<>();
        contactPoints = new ArrayList<>();
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
            contactPoints.clear();
            for ( int i = 0; i < handler.getSize(); i++ ) {
                Rectangle bounds = ((GameObject) handler.getItem(i)).getBounds();
//                if ( testLine.intersects(bounds) ) {
//                    lineQuery.add(bounds);
//                }
                if ( collides(bounds) ) {
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
                for ( int i = 0; i < lineQuery.size(); i++) {
                    Rectangle rect = lineQuery.get(i);
                    g.drawRect( rect.x, rect.y, rect.width, rect.height );
                    double[] contact = contactPoints.get(i);
                    g.fillOval( (int)contact[0] - 5, (int)contact[1] - 5, 10, 10 );
                }
            }
        }
    }

    private boolean collides( Rectangle rect ) {
        double[] lineStart = { testLine.getX1(), testLine.getY1() };
        double[] lineVector = { testLine.getX2() - lineStart[0], testLine.getY2() - lineStart[1] };
        double length = Math.sqrt( (lineVector[0] * lineVector[0]) + (lineVector[1] * lineVector[1]) );
        if ( length == 0 ) return false;
        // lineEnd = lineStart + 1 * lineVector

        double[] rectStart = { rect.x, rect.y };
        double[] rectEnd = { rect.x + rect.width, rect.y + rect.height };

        // Find at what value of t will I intersect with the rectangle
        double[] t_near = { (rectStart[0] - lineStart[0])/lineVector[0], (rectStart[1] - lineStart[1])/lineVector[1] };
        double[] t_far = { (rectEnd[0] - lineStart[0])/lineVector[0], (rectEnd[1] - lineStart[1])/lineVector[1] };

        // Rules for intersection: t_near[0] < t_far[1], t_near[1] < t_far[0]
        if ( t_near[0] > t_far[0] ) {
            double temp = t_far[0];
            t_far[0] = t_near[0];
            t_near[0] = temp;
        }
        if ( t_near[1] > t_far[1] ) {
            double temp = t_far[1];
            t_far[1] = t_near[1];
            t_near[1] = temp;
        }
        if ( t_near[0] > t_far[1] || t_near[1] > t_far[0] ) return false;

        double t_hit_near = Math.max( t_near[0], t_near[1] );
        double t_hit_far = Math.min( t_far[0], t_far[1] );

        if ( t_hit_far < 0 ) return false; // Disregard collisions behind the line

        // Collision point
        double[] contact_point = { lineStart[0] + (lineVector[0] * t_hit_near), lineStart[1] + (lineVector[1] * t_hit_near) };
        double[] contact_normal;
        if ( t_near[0] > t_near[1] ){
            if ( lineVector[0] < 0 )
                contact_normal = new double[]{1, 0};
            else
                contact_normal = new double[]{-1, 0};
        } else if ( t_near[0] < t_near[1] ){
            if ( lineVector[1] < 0 )
                contact_normal = new double[]{0, 1};
            else
                contact_normal = new double[]{0, -1};
        }

        if ( t_hit_near < 1 ){
            contactPoints.add(contact_point);

            return true;
        }
        return false;
    }
}
