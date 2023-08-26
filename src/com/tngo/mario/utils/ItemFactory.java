package com.tngo.mario.utils;

import com.tngo.mario.Game;
import com.tngo.mario.framework.Texture;
import com.tngo.mario.objects.*;

public class ItemFactory {

    Texture tex = Game.getTex();

    public CanvasItem create_object( int x, int y, String itemType ) {
        CanvasItem object;

        switch ( itemType ){
            case "ground":
                object = new GameObject( x, y, 32, 32, itemType, tex.get( "ground-normal" ) );
                break;
            case "ground2":
                object = new GameObject( x, y, 32, 32, itemType, tex.get( "ground2-normal" ) );
                break;
            case "mystery-block":
                object = new Block( x, y, "mysteryblock", "normal" );
                break;
            case "brick":
                object = new Block( x, y, "brick", "normal" );
                break;
            case "pipe-head-v":
                object = new PipeHead( x, y, true );
                break;
            case "pipe-piece":
                object = new GameObject( x + 4, y, 56, 32, itemType, tex.get( "pipe-piece-v" ) );
                break;
            case "hill-small":
                object = new CanvasItem( x, y - 6, 96, 38, tex.get( itemType ) );
                break;
            case "hill-large":
                object = new CanvasItem( x, y, 160, 64, tex.get( itemType ) );
                break;
            case "cloud-small":
                object = new CanvasItem( x, y, 64, 48, tex.get( itemType ) );
                break;
            case "cloud-medium":
                object = new CanvasItem( x, y, 96, 48, tex.get( itemType ) );
                break;
            case "cloud-large":
                object = new CanvasItem( x, y, 128, 48, tex.get( itemType ) );
                break;
            case "bush-small":
                object = new CanvasItem( x, y, 64, 32, tex.get( itemType ) );
                break;
            case "bush-medium":
                object = new CanvasItem( x + 9, y, 96, 32, tex.get( itemType ) );
                break;
            case "bush-large":
                object = new CanvasItem( x + 9, y, 128, 32, tex.get( itemType ) );
                break;
            case "castle-normal":
                object = new CanvasItem( x, y, 160, 160, tex.get( itemType ) );
                break;
            case "goomba":
                object = new GameObject( x, y, 32, 32, itemType, tex.get( "goomba-normal" ) );
                break;
            case "koopa-g":
                object = new GameObject( x, y, 32, 32, "koopa", tex.get( "koopa-g-left" ) );
                break;
            case "mario-small-idle-right-normal":
                object = new Player( x, y );
                break;
            default:
                object = new CanvasItem( x, y, 64, 64, "red" );
        }

        return object;
    }

}
