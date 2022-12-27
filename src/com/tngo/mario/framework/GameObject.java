package com.tngo.mario.framework;

import java.awt.*;
import java.util.LinkedList;

public abstract class GameObject {

    protected float x, y;
    protected ObjectId id;
    protected float velocityX = 0, velocityY = 0;
    protected boolean falling = true;
    protected boolean jumping = false;

    public GameObject(float x, float y, ObjectId id) {
        this.x = x;
        this.y = y;
        this.id = id;
    }

    public abstract void tick(LinkedList<GameObject> object);
    public abstract void render(Graphics g);

    public float getX() { return x; };
    public float getY() { return y; };
    public void setX(float x) { this.x = x; };
    public void setY(float y) {
        this.y = y;
    }

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

    public ObjectId getId() { return id; }

    public abstract Rectangle getBounds();

    public boolean isFalling() { return falling; }
    public boolean isJumping() { return jumping; }

    public void setFalling(boolean falling) { this.falling = falling; }
    public void setJumping(boolean jumping) { this.jumping = jumping; }

}
