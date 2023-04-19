package com.tngo.mario.window;

import com.tngo.mario.framework.GameObject;
import com.tngo.mario.objects.Player;

public class Camera {

    private float x, y;

    public Camera(float x, float y){
        this.x = x;
        this.y = y;
    }

    public void tick(GameObject player) {
        x = OldGame.WIDTH/2 - player.getX();
    }

    public float getX() { return x; }
    public float getY() { return y; }

    public void setX(float x) { this.x = x; }
    public void setY(float y) { this.y = y; }
}
