package com.tngo.mario.objects;

import com.tngo.mario.Game;
import com.tngo.mario.framework.Texture;

import static com.tngo.mario.framework.Level.removeItem;

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
        // Right now, player does not fall automatically if what object beneath it moves, but it doesn't
        // This can be tweaked, but I doubt this use case will happen naturally in the game

        if ( x < 0 ) {
            x = 0;
            stopMoveLeft();
        }
        if ( y + height >= Game.HEIGHT ) {
            // lose
        }
    }

    public boolean canDestroyBrick() { return size == "big"; }

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
        falling = true;
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
        super.handleCollision( contactPoint, neighbor );

        if ( neighbor instanceof Mushroom ) {
            removeItem(neighbor);
            return;
        }

        if ( contactPoint == 1 ) {
            if ( neighbor.getType() == "block" ) {
                neighbor.handleCollision( 3, this );
            }
        } else if ( contactPoint == 2 ) {
            stopMoveRight();
        } else if ( contactPoint == 3 ) {
            if ( velocityX == 0 ) {
                state = "idle";
            } else {
                state = "running";
            }
            updateSprites();
        } else {
            stopMoveLeft();
        }

    }

}
