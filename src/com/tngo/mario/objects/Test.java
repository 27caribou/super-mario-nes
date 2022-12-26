package com.tngo.mario.objects;

import com.tngo.mario.framework.GameObject;
import com.tngo.mario.framework.ObjectId;

import java.awt.*;
import java.util.LinkedList;

public class Test extends GameObject {
    public Test(float x, float y, ObjectId id) {
        super(x, y, id);
    }

    public void tick(LinkedList<GameObject> object) {

    }

    public void render(Graphics g) {
        g.setColor(Color.red);
        g.fillRect((int) x, (int) y, 32, 32);
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getVelocityX() {
        return velocityX;
    }

    public float getVelocityY() {
        return velocityY;
    }

    public void setVelocityX(float x) {
        this.velocityX = x;
    }

    public void setVelocityY(float y) {
        this.velocityY = y;
    }

    public ObjectId getId() {
        return id;
    }
}
