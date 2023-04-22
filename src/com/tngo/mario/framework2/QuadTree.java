package com.tngo.mario.framework2;

import com.tngo.mario.Game;
import com.tngo.mario.objects2.GameObject;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class QuadTree {

    private int capacity; // determines the max number of objects that can be stored in a single node before it is split
    private Rectangle boundary;
    boolean isDivided = false;
    private QuadTree[] children;
    private List<GameObject> objects;

    public QuadTree( Rectangle boundary, int n ) {
        this.boundary = boundary;
        this.capacity = n;
        objects = new ArrayList<>();
    }

    public boolean insert( GameObject object ) {
        if ( !boundary.intersects( object.getBounds() ) ) return false;

        if ( !isDivided ) {
            objects.add(object);
            if ( objects.size() > capacity ) subDivide();

        } else {
            if ( children[0].insert( object ) ) return true;
            if ( children[1].insert( object ) ) return true;
            if ( children[2].insert( object ) ) return true;
            children[3].insert( object );
        }

        return true;
    }

    public void subDivide() {
        System.out.println("Subdivide");
        int subWidth = boundary.width / 2;
        int subHeight = boundary.height / 2;
        int x = boundary.x;
        int y = boundary.y;

        children = new QuadTree[4];
        children[0] = new QuadTree( new Rectangle( x, y, subWidth, subHeight ), capacity );
        children[1] = new QuadTree( new Rectangle( x + subWidth, y, subWidth, subHeight ), capacity );
        children[2] = new QuadTree( new Rectangle( x, y + subHeight, subWidth, subHeight ), capacity );
        children[3] = new QuadTree( new Rectangle( x + subWidth, y + subHeight, subWidth, subHeight ), capacity );

        for ( int i = 0; i <= capacity; i++ ) {
            GameObject obj = objects.get(i);

            if ( children[0].insert( obj ) ) continue;
            if ( children[1].insert( obj ) ) continue;
            if ( children[2].insert( obj ) ) continue;
            children[3].insert( obj );
        }

        objects = new ArrayList<>();
        isDivided = true;
    }

    public void display( Graphics g ) {
        g.setColor( Color.pink );
        g.drawRect( boundary.x, boundary.y, boundary.width, boundary.height );

        if ( isDivided ) {
            for ( int i = 0; i < 4; i++ ) {
                children[i].display(g);
            }
        }
    }

}
