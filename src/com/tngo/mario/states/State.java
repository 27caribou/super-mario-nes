package com.tngo.mario.states;

import com.tngo.mario.objects2.GameObject;

public abstract class State {

    protected GameObject obj;

    public State( GameObject object ) { obj = object; }

    abstract public void tick();

}
