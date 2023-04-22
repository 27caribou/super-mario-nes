package com.tngo.mario.utils;

import com.tngo.mario.objects2.Player;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyboardInput extends KeyAdapter {

    Player player;

    public KeyboardInput( Player player ) {
        this.player = player;
    }

    public void keyPressed( KeyEvent e ) {
        int key = e.getKeyCode();

        if ( key == KeyEvent.VK_ESCAPE ) { System.exit(1); }
        if ( key == KeyEvent.VK_RIGHT ) player.setVelocityX(5);
        if ( key == KeyEvent.VK_LEFT ) player.setVelocityX(-5);

    }

    public void keyReleased( KeyEvent e ) {
        int key = e.getKeyCode();

        if ( key == KeyEvent.VK_RIGHT ) player.setVelocityX(0);
        if ( key == KeyEvent.VK_LEFT ) player.setVelocityX(0);
    }

}
