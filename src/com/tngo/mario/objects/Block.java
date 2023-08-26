package com.tngo.mario.objects;

import com.tngo.mario.Game;
import com.tngo.mario.framework.Texture;

public class Block extends GameObject {

    Texture tex = Game.getTex();
    private int numberOfItems;
//    private boolean containsCoins = false;
    private float originalY;

    public Block( float x, float y, String type, String style ) {
        super(x, y, 32, 32, "block", "white");
        originalY = y;
        setSprites( tex.get( type + "-" + style ) );
        if ( type == "brick" ) {
            numberOfItems = 0;
        } else {
            numberOfItems = 1;
        }
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
