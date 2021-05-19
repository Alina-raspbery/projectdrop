package com.mygdx.game_drop;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Drop extends Game {
    SpriteBatch batch;
     BitmapFont f;
    @Override
    public void create() {
        batch = new SpriteBatch();
        f = new BitmapFont();
        this.setScreen(new MenuScreen(this));
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        super.dispose();
        batch.dispose();
        f.dispose();
    }
}
