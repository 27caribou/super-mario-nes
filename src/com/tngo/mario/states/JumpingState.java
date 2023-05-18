package com.tngo.mario.states;

import com.tngo.mario.objects2.GameObject;

public class JumpingState extends State {

    private float velocity = -5;
    private float gravity = 0.5f;

    public JumpingState( GameObject object ) {
        super(object);
        this.obj.setVelocityY(velocity);
    }

    public JumpingState( GameObject object, int velocity ) {
        super(object);
        this.velocity = velocity;
        this.obj.setVelocityY(velocity);
    }

    public void tick() {
        velocity += gravity;
        this.obj.setVelocityY(velocity);

        // Check for collision

    }

}
