package com.tngo.mario.framework;

import com.tngo.mario.utils.BufferedImageLoader;
import com.tngo.mario.utils.SpriteSheet;

import java.awt.image.BufferedImage;

public class Texture {

    SpriteSheet bs;
    public BufferedImage[] block = new BufferedImage[1];
    public BufferedImage[] player = new BufferedImage[4];

    public Texture(){

        BufferedImageLoader loader = new BufferedImageLoader();
        BufferedImage block_sheet = null;
        try {
            block_sheet = loader.loadImage("/items.png");
        } catch (Exception e){
            e.printStackTrace();
        }

        bs = new SpriteSheet(block_sheet);

        getTextures();
    }

    private void getTextures() {
        block[0] = bs.grabImage( 1,1,32,32 ); // brick

        player[0] = bs.grabImage( 1,4,32,32 ); // idle
        player[1] = bs.grabImage( 2,4,32,32 ); // idle
        player[2] = bs.grabImage( 3,4,32,32 ); // idle
        player[3] = bs.grabImage( 4,4,32,32 ); // idle
    }

}
