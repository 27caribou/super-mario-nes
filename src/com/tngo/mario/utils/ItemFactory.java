package com.tngo.mario.utils;

import com.tngo.mario.Game;
import com.tngo.mario.framework.Texture;
import com.tngo.mario.objects.CanvasItem;
import com.tngo.mario.objects.GameObject;
import com.tngo.mario.objects.PipeHead;
import com.tngo.mario.objects.Player;

public class ItemFactory {

    Texture tex = Game.getTex();

    public CanvasItem create_object( int x, int y, String itemType ) {
        CanvasItem object;

        switch ( itemType ){
            case "block":
                object = new GameObject( x, y, 32, 32, itemType, tex.get( "block-normal" ) );
                break;
            case "mystery-block":
                object = new GameObject( x, y, 32, 32, itemType, tex.get( "mysteryblock-normal" ) );
                break;
            case "brick":
                object = new GameObject( x, y, 32, 32, itemType, tex.get( "brick-normal" ) );
                break;
            case "pipe-head-v":
                object = new PipeHead( x, y, 64, 32, tex.get( "pipe-head-v" ) );
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
            case "cloud-large":
                object = new CanvasItem( x, y, 128, 48, tex.get( itemType ) );
                break;
            case "bush-small":
                object = new CanvasItem( x, y, 64, 32, tex.get( itemType ) );
                break;
            case "bush-large":
                object = new CanvasItem( x + 9, y, 128, 32, tex.get( itemType ) );
                break;
            case "mario-small-idle-right-normal":
                object = new Player( x, y, 32, 32, tex.get( "mario-small-idle-right-normal" ) );
                break;
            default:
                object = new CanvasItem( x, y, 32, 32, "red" );
        }

        return object;
    }

}
