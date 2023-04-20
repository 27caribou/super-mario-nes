package com.tngo.mario.framework2;

import java.awt.*;

public class Animation {

    Color blockColor;

    public Animation( String color ) {
//        blockColor = color;
        if ( color == "white" ) {
            blockColor = Color.white;
        } else if ( color == "blue" ) {
            blockColor = Color.blue;
        } else if ( color == "red" ) {
            blockColor = Color.red;
        } else if ( color == "green" ) {
            blockColor = Color.green;
        } else if ( color == "yellow" ) {
            blockColor = Color.yellow;
        }
    }

    public void runAnimation() {

    }

    public void drawAnimation( Graphics g, int x, int y, int width, int height ) {
        g.setColor(blockColor);
        g.drawRect( x, y, width, height );
    }

//    public void drawAnimation(Graphics g, int x, int y, int scaleX, int scaleY){
//        g.drawImage(currentImg, x, y, scaleX, scaleY, null);
//    }

}
