package com.tngo.mario.framework2;

import java.awt.image.BufferedImage;

public class SpriteSheet {

    private BufferedImage image;
    public SpriteSheet( BufferedImage img ) {
        image = img;
    }

    public BufferedImage grabImage( int col, int row, int width, int height ) {
        return image.getSubimage( ( col * width ) - width, ( row * height ) - height, width, height );
    }
}
