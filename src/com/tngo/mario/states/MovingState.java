package com.tngo.mario.states;

import com.tngo.mario.objects2.GameObject;

public class MovingState extends State {

    private int velocity = 5;

    public MovingState( GameObject object, int velocity ) {
        super(object);
        this.velocity = velocity;
        this.obj.setVelocityX(velocity);
    }

    public void tick() {}

}
