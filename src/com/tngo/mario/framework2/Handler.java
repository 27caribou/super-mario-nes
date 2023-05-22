package com.tngo.mario.framework2;

import com.tngo.mario.objects2.CanvasItem;

import java.awt.*;
import java.util.LinkedList;

public class Handler {

    private LinkedList<CanvasItem> items = new LinkedList<>();
    private CanvasItem tempItem;

    // Update
    public void tick() {
        for ( int i = 0; i < items.size(); i++ ) {
            tempItem = items.get(i);
            tempItem.tick();
        }
    }

    public void render( Graphics g ) {
        for ( int i = 0; i < items.size(); i++ ) {
            tempItem = items.get(i);
            tempItem.render(g);
        }
    }

    public int getSize() { return items.size(); }

    public CanvasItem getItem( int i ) {
        return items.get(i);
    }

    public void addItem( CanvasItem newItem ) {
        items.add(newItem);
    }

    public void removeItem( CanvasItem newItem ) {
        items.remove(newItem);
    }

}
