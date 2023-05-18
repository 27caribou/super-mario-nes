package com.tngo.mario.tests;

import com.tngo.mario.framework2.Handler;
import com.tngo.mario.framework2.QuadTree;

import java.awt.*;

public abstract class LevelTest {
    abstract public void tick( Handler handler, QuadTree tree );
    abstract public void render( Graphics g );
}
