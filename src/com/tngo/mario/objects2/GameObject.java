package com.tngo.mario.objects2;

import static com.tngo.mario.framework2.Level.getQTree;

import java.awt.*;
import java.awt.geom.Line2D;
import java.util.Set;


public class GameObject extends CanvasItem {

    protected String type;
    protected float velocityX = 0, velocityY = 0;
    protected boolean falling = false;
//    private final float MAX_SPEED = 10;
    protected final float gravity = 0.5f;

    Set<GameObject> neighbors;

    public GameObject(float x, float y, float width, float height, String color, String type) {
        super(x, y, width, height, color);
        this.type = type;
    }

    public String getType() { return type; }
    public boolean isFalling() { return falling; }
    public float getVelocityX() { return velocityX; }
    public float getVelocityY() { return velocityY; }
    public void setVelocityX(float x) { this.velocityX = x; }
    public void setVelocityY(float y) { this.velocityY = y; }

    public Rectangle getBounds(){
        return new Rectangle( (int)x, (int)y, (int)width, (int)height );
    }

    public void tick() {
        // run animation
        super.tick();

        x += velocityX;
        y += velocityY;

        if ( velocityY != 0 && !falling ) falling = true;
        if ( falling ) velocityY += gravity;

        checkCollisions();
    }

    public void render( Graphics g ) {
        super.render(g);

        if ( neighbors != null && neighbors.size() > 0 ) {
            g.setColor(Color.ORANGE);
            for ( GameObject object : neighbors ) {
                Rectangle rect = object.getBounds();
                g.drawRect( rect.x, rect.y, rect.width, rect.height );
            }
        }
    }

    protected void checkCollisions() {
        neighbors = getQTree().query( this );
        if ( neighbors.size() == 0 ) return;

        for ( GameObject neighbor : neighbors ) {
            Rectangle neighborRect = neighbor.getBounds();
            int contactPoint = rectIntersection( neighborRect );
            if ( contactPoint == 0 ) continue;

            float diff;
            switch ( contactPoint ){
                case 1:
                    diff = neighborRect.y + neighborRect.height - y;
                    y += diff;
                    velocityY = 0;
                    break;
                case 2:
                    diff = x + width - neighborRect.x;
                    x -= diff;
                    velocityX = 0;
                    break;
                case 3:
                    diff = y + height - neighborRect.y;
                    y -= diff;
                    falling = false;
                    velocityY = 0;
                    break;
                case 4:
                    diff = neighborRect.x + neighborRect.width - x;
                    x += diff;
                    velocityX = 0;
                    break;
            }
        }
    }

    /**
     * Helper function for calculating collisions
     * 0 => no collision
     * 1 => top collision
     * 2 => right collision
     * 3 => bottom collision
     * 4 => left collision
     **/
    protected int lineIntersection( Line2D line, Rectangle rect ) {
        double[] lineStart = { line.getX1(), line.getY1() };
        double[] lineVector = { line.getX2() - lineStart[0], line.getY2() - lineStart[1] };
        double length = Math.sqrt( (lineVector[0] * lineVector[0]) + (lineVector[1] * lineVector[1]) );
        if ( length == 0 ) return 0;
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
        if ( t_near[0] > t_far[1] || t_near[1] > t_far[0] ) return 0;

        double t_hit_near = Math.max( t_near[0], t_near[1] );
        double t_hit_far = Math.min( t_far[0], t_far[1] );

        if ( t_hit_far < 0 || t_hit_near > 1 ) return 0; // Disregard collisions behind the line

        // Collision point
        if ( t_near[0] > t_near[1] ){
            if ( lineVector[0] < 0 )
                return 4;
            return 2;
        } else {
            if ( lineVector[1] < 0 )
                return 1;
            return 3;
        }

    }

    protected int rectIntersection( Rectangle rect ) {
        // Assuming that there was no collision unless movement occurred
        if ( velocityX == 0 && velocityY == 0 ) return 0;

        Rectangle objectRect = getBounds();
        Rectangle expanded_target = new Rectangle(
            rect.x - (objectRect.width/2),
            rect.y - (objectRect.height/2),
            rect.width + objectRect.width,
            rect.height + objectRect.height
        );
        // Setting line from center of the rectangle
        int line_x = objectRect.x + ( objectRect.width / 2 );
        int line_y = objectRect.y + ( objectRect.height / 2 );
        Line2D testLine = new Line2D.Double();
        testLine.setLine( line_x, line_y, line_x + velocityX, line_y + velocityY );

        return lineIntersection( testLine, expanded_target );
    }

}
