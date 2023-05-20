package com.tngo.mario.tests;

import com.tngo.mario.Game;
import com.tngo.mario.framework2.Handler;
import com.tngo.mario.framework2.QuadTree;
import com.tngo.mario.objects2.GameObject;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.List;

public class CollisionTest extends LevelTest {

    int type;
    List<Rectangle> query;
    List<double[]> contactPoints;
    Line2D testLine;
    Rectangle testRect;
    int[] velocity;

    public CollisionTest( Game game, int type ) {
        this.type = type;
        query = new ArrayList<>();
        contactPoints = new ArrayList<>();

        if ( type == 1 ) {
            testLine = new Line2D.Double();
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

        if ( type == 2 ) {
            testRect = new Rectangle(20,20,40,40);
            testLine = new Line2D.Double();
            velocity = new int[]{0, 0};
            game.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    // clicked in rect
                    if ( testRect.contains(e.getX(), e.getY()) ){
                        velocity[0] = 0;
                        velocity[1] = 0;
                    }
                    if ( e.getX() > testRect.x + testRect.width )
                        velocity[0] = 2;
                    else if ( e.getX() < testRect.x )
                        velocity[0] = -2;
                    else velocity[0] = 0;

                    if ( e.getY() > testRect.y + testRect.height )
                        velocity[1] = 2;
                    else if ( e.getY() < testRect.y )
                        velocity[1] = -2;
                    else velocity[1] = 0;
                }
            });
        }
    }

    public void tick( Handler handler, QuadTree tree ) {
        query.clear();
        contactPoints.clear();

        if ( type == 1 && testLine != null ) {
            for ( int i = 0; i < handler.getSize(); i++ ) {
                Rectangle bounds = ((GameObject) handler.getItem(i)).getBounds();
                if ( collides(bounds, false) ) {
                    query.add(bounds);
                }
            }
        }

        if ( type == 2 & testRect != null ) {
            testRect.translate( velocity[0], velocity[1] );
            for ( int i = 0; i < handler.getSize(); i++ ) {
                Rectangle bounds = ((GameObject) handler.getItem(i)).getBounds();
                if ( collides2(bounds) ) {
                    query.add(bounds);
                    // Change this to move only a portion back
//                    testRect.translate( -velocity[0], -velocity[1] );
                    velocity[0] = 0;
                    velocity[1] = 0;
                }
            }
        }
    }

    public void render( Graphics g ) {
        if ( type == 1 && testLine != null ) {
            g.setColor( Color.yellow );
            Graphics2D g2 = (Graphics2D) g;
            g2.draw(testLine);
        }

        if ( type == 2 && testRect != null ) {
            g.setColor( Color.yellow );
            g.drawRect( testRect.x, testRect.y, testRect.width, testRect.height );

            Graphics2D g2 = (Graphics2D) g;
            g2.draw(testLine);
        }

        if ( query.size() > 0 ) {
            for ( int i = 0; i < query.size(); i++) {
                Rectangle rect = query.get(i);
                g.drawRect( rect.x, rect.y, rect.width, rect.height );
                if ( contactPoints.size() > 0 ){
                    double[] contact = contactPoints.get(i);
                    g.fillOval( (int)contact[0] - 5, (int)contact[1] - 5, 10, 10 );
                }
            }
        }
    }

    private boolean collides( Rectangle rect, boolean addPoints ) {
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

        if ( t_hit_near <= 1 ){
             if ( addPoints ) contactPoints.add(contact_point);
            return true;
        }
        return false;
    }

    private boolean collides2( Rectangle rect ) {
        // Assuming that there was no collision unless movement occurred
        if ( velocity[0] == 0 && velocity[1] == 0 ) return false;

        Rectangle expanded_target = new Rectangle(
            rect.x - (testRect.width/2),
            rect.y - (testRect.height/2),
            rect.width + testRect.width,
            rect.height + testRect.height
        );
        // Setting line from center of the rectangle
        int line_x = testRect.x + ( testRect.width / 2 );
        int line_y = testRect.y + ( testRect.height / 2 );
        testLine.setLine( line_x, line_y, line_x + velocity[0], line_y + velocity[1] );

        return collides(expanded_target, true);
    }
}
