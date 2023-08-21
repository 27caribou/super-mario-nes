package com.tngo.mario.utils;

import com.tngo.mario.Game;
import com.tngo.mario.objects.Player;

public class Camera {

    private float x, y;

    public Camera( float x, float y ){
        this.x = x;
        this.y = y;
    }

    public void tick( Player player ) { x = (float) Game.WIDTH/2 - player.getX() - player.getWidth(); }

    public float getX() { return x; }
    public float getY() { return y; }

    public void setX( float x ) { this.x = x; }
    public void setY( float y ) { this.y = y; }

}
