package com.tngo.mario.objects;

import com.tngo.mario.Game;
import com.tngo.mario.framework.Texture;

public class Brick extends GameObject {

    Texture tex = Game.getTex();
    private boolean containsCoins = false;
    private float originalY;

//    public Brick( float x, float y, String color ) {
//        super( x, y, 32, 32, "brick", color );
//    }

    public Brick( float x, float y, String style ) {
        super(x, y, 32, 32, "brick", "white");
        originalY = y;
        setSprites( tex.get( "brick-" + style ) );
    }

    public Brick( float x, float y, String style, boolean containsCoins ) {
        super(x, y, 32, 32, "brick", "white");
        originalY = y;
        setSprites( tex.get( "brick-" + style ) );
        this.containsCoins = containsCoins;
    }

    public void tick() {
        super.tick();
        if ( y >= originalY ) {
            y = originalY;
            velocityY = 0;
        }
    }

    public void handleCollision( int contactPoint, GameObject neighbor ) {
        if ( contactPoint == 3 && neighbor.getType() == "player" ) {
            setVelocityY(-3);
        }
    }
}
