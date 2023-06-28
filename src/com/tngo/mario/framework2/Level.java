package com.tngo.mario.framework2;

import com.tngo.mario.Game;
import com.tngo.mario.objects.CanvasItem;
import com.tngo.mario.objects.GameObject;
import com.tngo.mario.objects.Player;
import com.tngo.mario.utils.Camera;
import com.tngo.mario.utils.KeyboardInput;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Level {

    protected Handler handler;
    protected Camera cam;
    protected static QuadTree qtree;
    protected BufferedImage stageImage;
    int playerIndex;

    public Level( Game game ) {
        handler = new Handler();
        cam = new Camera(0, 0);
        qtree = new QuadTree( new Rectangle( Game.WIDTH, Game.HEIGHT ), 4 );

        // Load stage from image
        BufferedImageLoader loader = new BufferedImageLoader();
        stageImage = loader.loadImage("/level.png");
        loadStage();

        findPlayer();
        game.addKeyListener( new KeyboardInput( (Player) handler.getItem(playerIndex) ));
    }

    public void tick() {
        qtree.flush();
        System.out.println( handler.getItem(playerIndex).getX() + ", " + cam.getX());
        qtree.setX( handler.getItem(playerIndex).getX() - Game.WIDTH/2 );
        for ( int i = 0; i < handler.getSize(); i++ ) { qtree.insert( (GameObject) handler.getItem(i) ); }

        handler.tick();
        cam.tick( (Player) handler.getItem(playerIndex) );
    }

    public void render( Graphics g ) {
        Graphics2D g2d = (Graphics2D) g;

        // Canvas
        g.setColor(Color.black);
        g.fillRect(0,0, Game.WIDTH, Game.HEIGHT);

        // Start of cam
        g2d.translate( cam.getX(), cam.getY() );

        // Anything within the camera is going to be affected by the translation
        handler.render(g);
//        qtree.display(g);

        // End of cam
        g2d.translate(-cam.getX(), -cam.getY());
    }

    private void findPlayer() {
        for ( int i = 0; i < handler.getSize(); i++ ) {
            if ( ((GameObject) handler.getItem(i)).getType() == "Player" ) {
                playerIndex = i;
                break;
            }
        }
    }

    public static QuadTree getQTree() { return qtree; }

    public void loadStage() {
        int w = stageImage.getWidth();
        int h = stageImage.getHeight();

        for ( int i = 0; i < h; i++ ) {
            for ( int j = 0; j < w; j++ ) {
                int pixel = stageImage.getRGB(i,j);
                int red = ( pixel >> 16 ) &0xff;
                int green = ( pixel >> 8 ) &0xff;
                int blue = ( pixel ) &0xff;

                if ( red == 255 && green == 255 && blue == 255 ) {
                    handler.addItem( new GameObject( i*32, j*32, 32, 32, "white", "brick" ) );
                } else if ( red == 0 && green == 0 && blue == 255 ) {
                    playerIndex = handler.getSize();
                    handler.addItem( new Player( i*32, j*32, 32, 50, "green" ) );
                }
            }
        }
    }

}
