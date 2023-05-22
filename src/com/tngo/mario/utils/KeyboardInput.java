package com.tngo.mario.utils;

import com.tngo.mario.objects2.Player;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyboardInput extends KeyAdapter {

    Player player;
    boolean movedLeft = false, movedRight = false;

    public KeyboardInput( Player player ) {
        this.player = player;
    }

    public void keyPressed( KeyEvent e ) {
        int key = e.getKeyCode();

        if ( key == KeyEvent.VK_ESCAPE ) { System.exit(1); }
        if ( key == KeyEvent.VK_RIGHT ) {
            player.setVelocityX(5);
            movedRight = true;
        }
        if ( key == KeyEvent.VK_LEFT ) {
            player.setVelocityX(-5);
            movedLeft = true;
        }
        if ( key == KeyEvent.VK_SPACE && !player.isFalling() ) player.setVelocityY(-10);
//        if ( key == KeyEvent.VK_DOWN ) player.setVelocityY(5);
    }

    public void keyReleased( KeyEvent e ) {
        int key = e.getKeyCode();

        if ( key == KeyEvent.VK_RIGHT ) {
            movedRight = false;
            if ( movedLeft ) {
                player.setVelocityX(-5);
            } else {
                player.setVelocityX(0);
            }
        }
        if ( key == KeyEvent.VK_LEFT ) {
            movedLeft = false;
            if ( movedRight ) {
                player.setVelocityX(5);
            } else {
                player.setVelocityX(0);
            }
        }
//        if ( key == KeyEvent.VK_SPACE ) player.setVelocityY(0);
//        if ( key == KeyEvent.VK_DOWN ) player.setVelocityY(0);
    }

}
