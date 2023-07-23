package com.tngo.mario.utils;

import java.awt.image.BufferedImage;
import java.awt.Color;

public class SpriteSheet {

    private BufferedImage image;
    public SpriteSheet( BufferedImage img ) {
        image = img;
    }

    public BufferedImage grabImage( int col, int row, int width, int height ) {
        // +1 and -2 because the sprites are surrounded by a 1px black outline
        BufferedImage img = image.getSubimage(
            ( col * width ) - width + 1,
            ( row * height ) - height + 1,
            width - 2,
            height - 2
        );
        return huecoMundo(img);
    }

    /**
     * Performs some calculations to determine the boundaries of a region within the image that contains colored pixels.
     * It then returns a cropped version of the input image that includes only that region.
     */
    private BufferedImage huecoMundo( BufferedImage img ) {
        int width = img.getWidth();
        int height = img.getHeight();
        int imgWidth = 0, imgHeight = 0, imgX = width, imgY = height;

        int noColorRowCounter = 0;
        boolean startedParsing = false;
        for ( int y = 0; y < height; y++ ) {
            boolean rowHasColor = false;
            int noColorColumnCounter = 0;
            int tempWidth = 0;

            for ( int x = 0; x < width; x++ ) {
                int pixel = img.getRGB(x, y);

                if ( isTransparent(pixel) && !rowHasColor ) continue; // Check transparency
                rowHasColor = true;

                // I did this here because I want to get when we actually start counting for picture
                if ( x < imgX ) imgX = x;
                if ( y < imgY ) imgY = y;

                if ( isTransparent(pixel) ) {
                    noColorColumnCounter++;
                } else {
                    tempWidth += noColorColumnCounter + 1; // Add back pixels that were not counted in tempWidth
                    noColorColumnCounter = 0;
                }
            }

            if ( tempWidth > imgWidth ) {
                imgWidth = tempWidth;
                startedParsing = true; // Useful to know when to start counting the height
            }
            if ( startedParsing ) {
                if ( tempWidth == 0 ) {
                    noColorRowCounter++;
                } else {
                    imgHeight += noColorRowCounter + 1;
                    noColorRowCounter = 0;
                }
            }
        }
//        System.out.println("width: " + imgWidth + "   height:  " + imgHeight + "   x:    " + imgX + "   y:    " + imgY);

        /* At this stage we have all we need. Just grab a more accurate image*/
        return img.getSubimage( imgX, imgY, imgWidth, imgHeight );
    }

    private boolean isTransparent( int pixel ) { return ( pixel >> 24 ) == 0x00; }
}
