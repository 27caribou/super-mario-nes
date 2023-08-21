package com.tngo.mario.framework;

import com.tngo.mario.Game;
import com.tngo.mario.objects.CanvasItem;
import com.tngo.mario.objects.GameObject;
import com.tngo.mario.objects.Player;
import com.tngo.mario.utils.BufferedImageLoader;
import com.tngo.mario.utils.Camera;
import com.tngo.mario.utils.KeyboardInput;
import com.tngo.mario.utils.QuadTree;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Level {

    protected Handler gameObjectHandler, backgroundItemsHandler;
    protected Camera cam;
    protected static QuadTree qtree;
    protected BufferedImage stageImage;
    protected int playerIndex;
    protected Color backgroundColor;

    Texture tex = Game.getTex();

    public Level( Game game, String model, String backgroundColor ) {
        gameObjectHandler = new Handler();
        backgroundItemsHandler = new Handler();
        cam = new Camera(0, 0);

        if ( backgroundColor == "skyblue" ) {
            this.backgroundColor = new Color( 92, 148, 252 );
        } else {
            this.backgroundColor = new Color( 0, 0, 0);
        }

        // Load stage from image
        BufferedImageLoader loader = new BufferedImageLoader();
        stageImage = loader.loadImage( "/" + model );
        loadStage();

        game.addKeyListener( new KeyboardInput( (Player) gameObjectHandler.getItem(playerIndex) ));
    }

    public void tick() {
        qtree.flush();
        for ( int i = 0; i < gameObjectHandler.getSize(); i++ ) { qtree.insert( (GameObject) gameObjectHandler.getItem(i) ); }

        backgroundItemsHandler.tick();
        gameObjectHandler.tick();
        cam.tick( (Player) gameObjectHandler.getItem(playerIndex) );
    }

    public void render( Graphics g ) {
        Graphics2D g2d = (Graphics2D) g;

        // Canvas
        g.setColor(this.backgroundColor);
        g.fillRect(0,0, Game.WIDTH, Game.HEIGHT);

        // Start of cam
        g2d.translate( Math.min(0, cam.getX()), cam.getY() );

        // Anything within the camera is going to be affected by the translation
        backgroundItemsHandler.render(g);
        gameObjectHandler.render(g);
        qtree.display(g);

        // End of cam
        g2d.translate( -cam.getX(), -cam.getY() );
    }

    public static QuadTree getQTree() { return qtree; }

    public void loadStage() {
        int w = stageImage.getWidth();
        int h = stageImage.getHeight();
        qtree = new QuadTree( new Rectangle( 32 * w, 32 * h ), 4 );

        for ( int i = 0; i < w; i++ ) {
            for ( int j = 0; j < h; j++ ) {
                int pixel = stageImage.getRGB(i,j);
                int red = ( pixel >> 16 ) &0xff;
                int green = ( pixel >> 8 ) &0xff;
                int blue = ( pixel ) &0xff;

                if ( red == 200 && green == 76 && blue == 12 ) {
                    gameObjectHandler.addItem( new GameObject( i * 32, j * 32, 32, 32, "block", tex.get( "block-normal" ) ) );
                } else if ( red == 252 && green == 152 && blue == 56 ) {
                    gameObjectHandler.addItem( new GameObject( i * 32, j * 32, 32, 32, "mystery-block", tex.get( "mysteryblock-normal" ) ) );
                } else if ( red == 150 && green == 76 && blue == 12 ) {
                    gameObjectHandler.addItem( new GameObject( i * 32, j * 32, 32, 32, "brick", tex.get( "brick-normal" ) ) );
                } else if ( red == 0 && green == 168 && blue == 0 ) {
                    gameObjectHandler.addItem( new GameObject( i * 32, j * 32, 64, 64, "pipe-up", tex.get( "pipe-up" ) ) );
                } else if ( red == 24 && green == 134 && blue == 24 ) {
                    backgroundItemsHandler.addItem( new CanvasItem( i * 32, j * 32, 160, 64, tex.get( "hill-large" ) ) );
                } else if ( red == 25 && green == 140 && blue == 25 ) {
                    backgroundItemsHandler.addItem( new CanvasItem( i * 32, j * 32 - 6, 96, 38, tex.get( "hill-small" ) ) );
                } else if ( red == 252 && green == 252 && blue == 252 ) {
                    backgroundItemsHandler.addItem( new CanvasItem( i * 32, j * 32, 64, 48, tex.get( "cloud-small" ) ) );
                } else if ( red == 234 && green == 234 && blue == 234 ) {
                    backgroundItemsHandler.addItem( new CanvasItem( i * 32, j * 32, 128, 48, tex.get( "cloud-large" ) ) );
                } else if ( red == 128 && green == 208 && blue == 16 ) {
                    backgroundItemsHandler.addItem( new CanvasItem( i * 32 + 9, j * 32, 128, 32, tex.get( "bush-large" ) ) );
                } else if ( red == 125 && green == 198 && blue == 15 ) {
                    backgroundItemsHandler.addItem( new CanvasItem( i * 32, j * 32, 64, 32, tex.get( "bush-small" ) ) );
                } else if ( red == 0 && green == 0 && blue == 255 ) {
                    playerIndex = gameObjectHandler.getSize();
                    gameObjectHandler.addItem( new Player( i * 32, j * 32, 32, 32, tex.get( "mario-small-idle-right-normal" ) ) );
                }
            }
        }
    }

}
