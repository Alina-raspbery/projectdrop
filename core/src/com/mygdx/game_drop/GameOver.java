package com.mygdx.game_drop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class GameOver implements Screen {
    Texture shakhta;
    final Drop game;
    OrthographicCamera camera;
    int points;

    public GameOver(Drop game, int points) {
        this.game = game;
        this.points = points;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        shakhta = new Texture(Gdx.files.internal("shakhta.png"));

    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        game.batch.draw(shakhta, 0, 0);
        game.f.draw(game.batch, "Game over!", 360,340);
        game.f.draw(game.batch, "Your collection: "+ points, 100,120);
        Pref prefs = new Pref(points);
        int score = prefs.getLevel();
        game.f.draw(game.batch, "Best result: "+ score, 103,100);     //readResults()
        game.batch.end();

        if (Gdx.input.justTouched()){
            game.setScreen(new GameScreen(game));
            dispose();
        }

    }
    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
