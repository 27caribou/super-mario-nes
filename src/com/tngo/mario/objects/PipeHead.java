package com.tngo.mario.objects;

import java.awt.*;
import java.awt.image.BufferedImage;

public class PipeHead extends GameObject {

    // Head is vertical if width > height; else horizontal

    public PipeHead( float x, float y, float width, float height, String color ) {
        super( x, y, width, height, "pipe-head", color );
    }

    public PipeHead( float x, float y, float width, float height, BufferedImage... imgs ) {
        super( x, y, width, height, "pipe-head", imgs );
    }

    public Rectangle getBounds(){
        return new Rectangle( (int)x + 4, (int)y, (int)width - 8, (int)height );
    }

    public void tick() {
        super.tick();
    }

}
