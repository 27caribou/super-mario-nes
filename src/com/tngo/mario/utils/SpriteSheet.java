package com.tngo.mario.utils;

import java.awt.image.BufferedImage;

public class SpriteSheet {

    private BufferedImage image;
    public SpriteSheet( BufferedImage img ) {
        image = img;
    }

    public BufferedImage grabImage( int col, int row, int width, int height ) {
        BufferedImage img = image.getSubimage( ( col * width ) - width, ( row * height ) - height, width, height );
        return huecoMundo(img);
//        return img;
    }

    /**
     * Performs some calculations to determine the boundaries of a region within the image that contains colored pixels.
     * It then returns a cropped version of the input image that includes only that region.
     */
    private BufferedImage huecoMundo( BufferedImage img ) {
        int width = img.getWidth();
        int height = img.getHeight();

        int imgWidth = 0, n = 0, tempWidth = 0, imgHeight = 0, imgX = img.getWidth(), imgY = img.getHeight();
        int pixel, red, green, blue;
        boolean lineTouched = false;

        for ( int y = 0; y < height; y++ ) {
            for ( int x = 0; x < width; x++ ) {

                pixel = img.getRGB(x, y);

                red = ( pixel >> 16 ) & 0xff;
                green = ( pixel >> 8 ) & 0xff;
                blue = ( pixel ) & 0xff;

                if ( (( red == 255 ) && ( green == 255 ) && ( blue == 255 )) && !lineTouched ) continue;
                else {
                    // I did this here because I want to get when we actually start counting for picture
                    if ( x < imgX ) imgX = x;
                    if ( y < imgY ) imgY = y;

                    lineTouched = true;
                    // I did this here so that if we count and go above 10 white pixels, then we reached the max right already
                    if ((red == 255) && (green == 255) && (blue == 255))
                        n++;
                    else {
                        n = 0;
                        tempWidth++;
                    }

                    if (n >= 10){
                        //w2 = w2 - 10;
                        break;
                    }
                }
            }
            // This is done here so that you can update w if you find a bigger width
            if (tempWidth > imgWidth) imgWidth = tempWidth;
            if (lineTouched) {
                // Adding a height pixel if you could find even one colored pixel on that row
                imgHeight++;
                lineTouched = false;
                tempWidth = 0;
            }
        }
//        System.out.println("width: " + imgWidth + "   height:  " + imgHeight + "   x:    " + imgX + "   y:    " + imgY);

        /* At this stage we have all we need. Just grab a more accurate image*/
        return img.getSubimage( imgX, imgY, imgWidth, imgHeight );
    }
}
