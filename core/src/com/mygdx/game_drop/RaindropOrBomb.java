package com.mygdx.game_drop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.TimeUtils;


public class RaindropOrBomb extends Rectangle {
    public java.lang.Object type;
     private static final int firstVelosity = 200;
    static public RaindropOrBomb spawnRaindrop() {

        RaindropOrBomb item = new RaindropOrBomb();
        item.width = 64;
        item.height = 64;
        item.x = MathUtils.random(0, 800 - 64);
        item.y = 480;
        item.type = MathUtils.random(0, 1);
        return item;
    }
    public void drawRaindrop(SpriteBatch batch, Texture bombImage, Texture dropImage){
        Texture image = new Texture(((int)type==0)? Gdx.files.internal("b.png")
                : Gdx.files.internal("diamond.png"));
        batch.draw(image, x, y);
    }
    public int getVelosityByPoints(int points, int stepDrop, int stepVelosity){
               return points / stepDrop * stepVelosity + firstVelosity;

    }
}

