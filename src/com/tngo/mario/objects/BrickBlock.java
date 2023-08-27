package com.tngo.mario.objects;

import static com.tngo.mario.framework.Level.addItem;
import static com.tngo.mario.framework.Level.removeItem;

import com.tngo.mario.Game;
import com.tngo.mario.framework.Texture;

public class BrickBlock extends GameObject {

    Texture tex = Game.getTex();
    private float originalY;
    private int numberOfCoins;
    private String style;

    public BrickBlock( float x, float y, String style ) {
        super(x, y, 32, 32, "block", "white");
        setSprites( tex.get( "brick-" + style ) );
        originalY = y;
        this.style = style;
    }

    public BrickBlock( float x, float y, String style, int numberOfCoins ) {
        super(x, y, 32, 32, "block", "white");
        setSprites( tex.get( "brick-" + style ) );
        this.style = style;
        originalY = y;
        if ( numberOfCoins > 0 ) this.numberOfCoins = numberOfCoins;
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
            if ( numberOfCoins > 0 ) {
                numberOfCoins -= 1;
                addItem( new Coin( x + 10, y - 32 ) );
                if ( numberOfCoins == 0 ) {
                    setSprites( tex.get( "brick-empty-" + style ) );
                }
            } else {
//                removeItem(this);
            }
        }
    }

}