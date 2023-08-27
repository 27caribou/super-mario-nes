package com.tngo.mario.objects;

import static com.tngo.mario.framework.Level.addItem;

import com.tngo.mario.Game;
import com.tngo.mario.framework.Handler;
import com.tngo.mario.framework.Texture;

public class MysteryBlock extends GameObject {

    Texture tex = Game.getTex();
    private float originalY;
    private String item;

    public MysteryBlock( float x, float y, String style, String item ) {
        super( x, y, 32, 32, "block", "white" );
        setSprites( tex.get( "mysteryblock-" + style ) );
        originalY = y;
    }

    public void tick() {
        super.tick();
        if ( y >= originalY ) {
            y = originalY;
            velocityY = 0;
        }
    }

    public void handleCollision( int contactPoint, GameObject neighbor ) {
        super.handleCollision( contactPoint, neighbor );
        if ( contactPoint == 3 && neighbor.getType() == "player" ) {
            setVelocityY(-3);
            addItem( new Coin( x + 10, y - 32 ) );
        }
    }

}
