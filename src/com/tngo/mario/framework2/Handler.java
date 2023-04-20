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

    public void createTestLevel() {

        addCanvasItem( new CanvasItem( 100, Game.HEIGHT - 200, 20, 50, "blue" ));

        for ( int yy = 0; yy < Game.HEIGHT + 32; yy += 32 ) {
            addCanvasItem( new CanvasItem( 0, yy, 32, 32, "white" ));
            addCanvasItem( new CanvasItem( Game.WIDTH - 32, yy, 32, 32, "white" ));
        }

        for ( int xx = 0; xx < Game.WIDTH + 32; xx += 32 ) {
            addCanvasItem( new CanvasItem( xx, Game.HEIGHT - 32, 32, 32, "white" ));
        }

//        for (int xx = 200; xx < 600; xx += 32) {
//            addGameObject(new Block(xx, 400, ObjectId.Block));
//        }
    }

}
