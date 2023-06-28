package com.tngo.mario.objects;

import com.tngo.mario.framework2.Animation;

import java.awt.*;

public class CanvasItem {

    protected float x, y;
    protected float width, height;
    protected Animation sprites;

    public CanvasItem( float x, float y, float width, float height, String color ) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        sprites = new Animation(color);
    }

    public float getX() { return x; }
    public float getY() { return y; }
    public void setX( float x ) { this.x = x; }
    public void setY( float y ) { this.y = y; }

    public void tick() {
        sprites.runAnimation();
    }

    public void render( Graphics g ) {
        sprites.drawAnimation( g, (int)x, (int)y, (int)width, (int)height );
    }

}
