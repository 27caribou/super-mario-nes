package com.tngo.mario.framework;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Animation {

    private int speed = 5;
    private int frames;

    private int index = 0;
    private int count = 0;

    Color fallbackColor;
    private BufferedImage[] images;
    private BufferedImage currentImg;

    public Animation( String color ) {
        if ( color == "white" ) {
            fallbackColor = Color.white;
        } else if ( color == "blue" ) {
            fallbackColor = Color.blue;
        } else if ( color == "red" ) {
            fallbackColor = Color.red;
        } else if ( color == "green" ) {
            fallbackColor = Color.green;
        } else if ( color == "yellow" ) {
            fallbackColor = Color.yellow;
        }
    }

    public Animation( BufferedImage... args ){
        frames = args.length;
        images = new BufferedImage[frames];
        System.arraycopy( args, 0, images, 0, frames );
    }

    public void setAnimationSpeed( int speed ) { this.speed = speed; }

    public void runAnimation() {
        if ( fallbackColor == null ) {
            index++;
            if ( index > speed ) {
                index = 0;
                nextFrame();
            }
        }
    }

    private void nextFrame(){
        currentImg = images[count];
        count = ( count + 1 ) % frames;
    }

    public void drawAnimation( Graphics g, int x, int y, int width, int height ) {
        if ( fallbackColor == null ) {
            g.drawImage( currentImg, x, y, width, height, null );
        } else {
            g.setColor( fallbackColor );
            g.drawRect( x, y, width, height );
        }
    }

}
