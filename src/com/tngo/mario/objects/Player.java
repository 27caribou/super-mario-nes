package com.tngo.mario.objects;

import java.awt.image.BufferedImage;

public class Player extends GameObject {

    public Player( float x, float y, float width, float height, String color ) {
        super( x, y, width, height, "player", color );
    }

    public Player( float x, float y, float width, float height, BufferedImage... imgs ) {
        super( x, y, width, height, "player", imgs );
    }

}
