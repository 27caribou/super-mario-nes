package com.tngo.mario.objects;

import com.tngo.mario.Game;

import java.awt.image.BufferedImage;

import static com.tngo.mario.framework.Level.removeItem;
import static com.tngo.mario.framework.Level.itemIsVisible;

public class Enemy extends GameObject {

    protected float defaultSpeed = 1;

    public Enemy( float x, float y, float width, float height, String color ) {
        super( x, y, width, height, "enemy", color );
    }

    public Enemy( float x, float y, float width, float height, BufferedImage... imgs ) {
        super( x, y, width, height, "enemy", imgs );
    }

    public void tick() {
        super.tick();

        if ( velocityX == 0 && itemIsVisible(this) ) moveLeft();
        if ( x < 0 ) moveRight();
        if ( y >= Game.HEIGHT ) removeItem(this);
    }

    public void moveLeft() { setVelocityX( -defaultSpeed ); }
    public void moveRight() { setVelocityX( defaultSpeed ); }

    public void handleCollision( int contactPoint, GameObject neighbor ) {
        super.handleCollision(contactPoint, neighbor);

        if ( contactPoint == 2 ) {
            moveLeft();
            if ( neighbor instanceof Enemy ) ((Enemy) neighbor).moveRight();
        } else if ( contactPoint == 4 ) {
            moveRight();
            if ( neighbor instanceof Enemy ) ((Enemy) neighbor).moveLeft();
        }
    }

}
