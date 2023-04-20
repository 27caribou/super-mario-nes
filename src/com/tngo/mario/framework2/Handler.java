package com.tngo.mario.framework2;

import com.tngo.mario.Game;
import com.tngo.mario.objects2.CanvasItem;

import java.awt.*;
import java.util.LinkedList;

public class Handler {

    public LinkedList<CanvasItem> items = new LinkedList<>();
    private CanvasItem tempItem;


    // Update
    public void tick() {
        for ( int i = 0; i < items.size(); i++ ) {
            tempItem = items.get(i);
            tempItem.tick();
        }
    }

    public void render(Graphics g) {
        for ( int i = 0; i < items.size(); i++ ) {
            tempItem = items.get(i);
            tempItem.render(g);
        }
    }

    public void addCanvasItem(CanvasItem newItem) {
        items.add(newItem);
    }

    public void removeItem(CanvasItem newItem) {
        items.remove(newItem);
    }

}
