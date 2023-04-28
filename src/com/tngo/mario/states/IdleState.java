package com.tngo.mario.states;

import com.tngo.mario.objects2.GameObject;

public class IdleState extends State {

    public IdleState( GameObject object ) {
        super(object);
        this.obj.setVelocityX(0);
        this.obj.setVelocityY(0);
    }

    public void tick() {}
}
