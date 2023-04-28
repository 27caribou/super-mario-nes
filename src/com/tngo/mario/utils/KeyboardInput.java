package com.tngo.mario.utils;

import com.tngo.mario.objects2.Player;
import com.tngo.mario.states.IdleState;
import com.tngo.mario.states.JumpingState;
import com.tngo.mario.states.MovingState;

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
        if ( key == KeyEvent.VK_RIGHT ) player.setState( new MovingState(player, 5) );
        if ( key == KeyEvent.VK_LEFT ) player.setState( new MovingState(player, -5) );
        if ( key == KeyEvent.VK_SPACE ) player.setState( new JumpingState(player) );
//        if ( key == KeyEvent.VK_DOWN ) player.setVelocityY(5);
    }

    public void keyReleased( KeyEvent e ) {
        int key = e.getKeyCode();

        if ( key == KeyEvent.VK_RIGHT ) player.setState( new IdleState(player) );
        if ( key == KeyEvent.VK_LEFT ) player.setState( new IdleState(player) );
//        if ( key == KeyEvent.VK_SPACE ) player.setVelocityY(0);
//        if ( key == KeyEvent.VK_DOWN ) player.setVelocityY(0);
    }

}
