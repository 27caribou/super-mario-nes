package com.tngo.mario.objects;

import com.tngo.mario.framework.GameObject;
import com.tngo.mario.framework.ObjectId;
import com.tngo.mario.framework.Texture;
import com.tngo.mario.window.Animation;
import com.tngo.mario.window.OldGame;
import com.tngo.mario.window.Handler;

import java.awt.*;
import java.util.LinkedList;

public class Player extends GameObject {

    private float width = 48, height = 96;
    private float gravity = 0.5f;
    private final float MAX_SPEED = 10;

    private Handler handler;

    Texture tex = OldGame.getInstance();

    private Animation playerWalk;

    public Player(float x, float y, Handler handler, ObjectId id){
        super(x, y, id);
        this.handler = handler;

//        playerWalk = new Animation(10, tex.player[1], tex.player[2], ...)
    }

    public void tick(LinkedList<GameObject> object) {
        x += velocityX;
        y += velocityY;

        if ( jumping || falling ){
            velocityY += gravity;

            if ( velocityY > MAX_SPEED ){
                velocityY = MAX_SPEED;
            }
        }
        Collision(object);

//        playerWalk.runAnimation();
    }

    private void Collision(LinkedList<GameObject> object) {
        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);
            if (tempObject.getId() == ObjectId.Block) {

                // Top collision
                if (getBoundsTop().intersects(tempObject.getBounds())) {
                    y = tempObject.getY() + (height/2);
                    velocityY = 0;
                }

                if (getBounds().intersects(tempObject.getBounds())) {
                    y = tempObject.getY() - height;
                    velocityY = 0;
                    jumping = false;
                    falling = false;
                } else {
                    falling = true;
                }

                // lEFT collision
                if (getBoundsLeft().intersects(tempObject.getBounds())) {
                    x = tempObject.getX() + 40;
                }

                // Right collision
                if (getBoundsRight().intersects(tempObject.getBounds())) {
                    x = tempObject.getX() - 40;
                }
            }
        }
    }

    public void render(Graphics g) {
        g.setColor(Color.blue);
        g.fillRect((int) x, (int) y, (int) width, (int) height);

        if ( velocityX != 0) {
//            playerWalk.drawAnimation(g, (int) x, (int) y);
        } else {
//            draw normal
        }

//        Graphics2D g2d = (Graphics2D) g;
//        g.setColor(Color.red);
//        g2d.draw(getBounds());
//        g2d.draw(getBoundsRight());
//        g2d.draw(getBoundsLeft());
//        g2d.draw(getBoundsTop());
    }

    public Rectangle getBounds() {
        return new Rectangle((int)( x + (width / 2) - (width / 4) ), (int)( y + (height / 2) ), (int)(width / 2), (int) (height / 2));
    }

    public Rectangle getBoundsTop() {
        return new Rectangle((int)( x + (width / 2) - (width / 4) ), (int) y, (int)(width / 2), (int)(height / 2));
    }

    public Rectangle getBoundsRight() {
        return new Rectangle((int) ( x + width - 5), (int) y + 5, 5, (int) height - 10);
    }
    public Rectangle getBoundsLeft() {
        return new Rectangle((int) x, (int) y + 5, 5, (int) height - 10);
    }
}
