package com.tngo.mario.objects;

import com.tngo.mario.Game;
import com.tngo.mario.framework.Texture;

import java.awt.image.BufferedImage;

public class Player extends GameObject {

    Texture tex = Game.getTex();

    private boolean movingLeft = false;
    private boolean movingRight = false;
    private String size = "small";
    private String state = "idle";
    private String style = "normal";
    private String direction = "right";

//    public Player( float x, float y, String color ) {
//        super( x, y, 32, 32, "player", color );
//    }

    public Player( float x, float y ) {
        super( x, y, 32, 32, "player", "white" );
        updateSprites();
    }

    public void tick() {
        super.tick();
        if ( x < 0 ) {
            x = 0;
            stopMoveLeft();
        }
    }

    public void moveLeft() {
        setVelocityX(-5);
        if ( !movingLeft ) {
            movingLeft = true;
            direction = "left";
            if ( !isFalling() ) state = "running";
            updateSprites();
        }
    }

    public void moveRight() {
        setVelocityX(5);
        if ( !movingRight ) {
            movingRight = true;
            direction = "right";
            if ( !isFalling() ) state = "running";
            updateSprites();
        }
    }

    public void jump() {
        if ( isFalling() ) return;
        setVelocityY(-12);
        state = "jumping";
        updateSprites();
    }

    public void stopMoveLeft() {
        movingLeft = false;
        if ( movingRight ) {
            setVelocityX(5);
            direction = "right";
        } else {
            setVelocityX(0);
            if ( !isFalling() ) state = "idle";
        }
        updateSprites();
    }

    public void stopMoveRight() {
        movingRight = false;
        if ( movingLeft ) {
            setVelocityX(-5);
            direction = "left";
        } else {
            setVelocityX(0);
            if ( !isFalling() ) state = "idle";
        }
        updateSprites();
    }

    private void updateSprites() {
        setSprites( tex.get( "mario-" + size  + "-" + state + "-" + direction + "-" + style ) );
        setAnimationSpeed(2);
    }

    public void handleCollision( int contactPoint, GameObject neighbor ) {
        if ( contactPoint == 3 ) {
            if ( velocityX == 0 ) {
                state = "idle";
            } else {
                state = "running";
            }
            updateSprites();
        } else if ( contactPoint == 2 ) {
            stopMoveRight();
        } else if ( contactPoint == 4 ) {
            stopMoveLeft();
        }

        neighbor.handleCollision( 4 - contactPoint, this );
    }

}
