package com.tngo.mario.objects2;

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

    public void tick() {
        sprites.runAnimation();
    }

    public void render( Graphics g ) {
        sprites.drawAnimation(g, (int)x, (int)y, (int)width, (int)height);
    }

}
