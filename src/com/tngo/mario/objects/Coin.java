package com.tngo.mario.objects;

import static com.tngo.mario.framework.Level.removeItem;

import com.tngo.mario.Game;
import com.tngo.mario.framework.Texture;

public class Coin extends GameObject {

    Texture tex = Game.getTex();
    private float originalY;

    public Coin( float x, float y ) {
        super( x, y, 12, 32, "coin", "white" );
        setSprites( tex.get( "jumpingcoin" ) );
        setAnimationSpeed(3);
        originalY = y;
        velocityY = -8;
    }

    public void tick() {
        super.tick();
        if ( y >= originalY ) {
            removeItem(this);
        }
    }

    public void handleCollision( int contactPoint, GameObject neighbor ) {
        // Do nothing
    }
}
