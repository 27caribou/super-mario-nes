package com.tngo.mario.framework;

import com.tngo.mario.framework2.BufferedImageLoader;
import com.tngo.mario.framework2.SpriteSheet;

import java.awt.image.BufferedImage;

public class Texture {

    SpriteSheet bs;
    public BufferedImage[] block = new BufferedImage[1];
    public BufferedImage[] player = new BufferedImage[2];

    public Texture(){

        BufferedImageLoader loader = new BufferedImageLoader();
        BufferedImage block_sheet = null;
        try {
            block_sheet = loader.loadImage("/inanimateObjects.png");
        } catch (Exception e){
            e.printStackTrace();
        }

        bs = new SpriteSheet(block_sheet);

        getTextures();
    }

    private void getTextures() {
        block[0] = bs.grabImage( 8,2,32,32 ); // brick
        player[0] = bs.grabImage( 1,1,32,32 ); // idle
    }

}
