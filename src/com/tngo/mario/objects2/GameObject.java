package com.tngo.mario.objects2;

import java.awt.*;

public abstract class GameObject extends CanvasItem {

    protected String type;
    protected float velocityX = 0, velocityY = 0;
//    private final float MAX_SPEED = 10;
    protected float gravity = 0.5f;

    public GameObject(float x, float y, float width, float height, String color, String type) {
        super(x, y, width, height, color);
        this.type = type;
    }

    public String getType() { return type; }
    public float getVelocityX() { return velocityX; }
    public float getVelocityY() {
        return velocityY;
    }
    public void setVelocityX(float x) {
        this.velocityX = x;
    }
    public void setVelocityY(float y) {
        this.velocityY = y;
    }

    protected Rectangle getBounds(){
        return new Rectangle( (int)x, (int)y, (int)width, (int)height );
    }

    public void tick() {
        super.tick();

        x += velocityX;
        y += velocityY;

    }

}
