package com.tngo.mario.window;

import com.tngo.mario.framework.GameObject;
import com.tngo.mario.framework.ObjectId;
import com.tngo.mario.objects.Block;

import java.awt.*;
import java.util.LinkedList;

public class Handler {

    public LinkedList<GameObject> object = new LinkedList<GameObject>();
    private GameObject tempObject;

    // Update
    public void tick() {
        for ( int i = 0; i < object.size(); i++ ) {
            tempObject = object.get(i);
            tempObject.tick(object);
        }
    }

    public void render(Graphics g) {
        for ( int i = 0; i < object.size(); i++ ) {
            tempObject = object.get(i);
            tempObject.render(g);
        }
    }

    public void addGameObject(GameObject object) {
        this.object.add(object);
    }

    public void removeObject(GameObject object) {
        this.object.remove(object);
    }

    public void createLevel() {
        for (int xx = 0; xx < Game.WIDTH + 32; xx += 32) {
            addGameObject(new Block(xx, Game.HEIGHT - 32, ObjectId.Block));
        }
    }
}
