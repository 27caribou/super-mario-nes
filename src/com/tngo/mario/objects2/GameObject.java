package com.tngo.mario.objects2;

import java.awt.*;

public class GameObject extends CanvasItem {

    protected String type;
    protected float velocityX = 0, velocityY = 0;
    private float gravity = 0.5f;

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

    public void tick() {
        super.tick();

        x += velocityX;
        y += velocityY;
    }

}
