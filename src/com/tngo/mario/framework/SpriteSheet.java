package com.tngo.mario.framework;

import java.awt.image.BufferedImage;

public class SpriteSheet {

    private BufferedImage image;
    public SpriteSheet(BufferedImage img) {
        image = img;
    }

    public BufferedImage grabImage(int col, int row, int width, int height) {
        BufferedImage img = image.getSubimage((col*width) - width, (row*height) - height, width, height);
        return img;
    }
}
