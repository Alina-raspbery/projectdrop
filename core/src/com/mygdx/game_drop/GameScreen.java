package com.mygdx.game_drop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.Iterator;
import java.util.concurrent.TimeUnit;

public class GameScreen implements Screen {
	final Drop game;
	Texture shakhta;
	Texture dropImage;
	Texture bucketImage;
	Sound dropSound;
	Sound bombMusic;
	Music rainMusic;
	SpriteBatch batch;
	OrthographicCamera camera;
	Rectangle bucket;
	Array<RaindropOrBomb> raindropsAndBombs;
	long lastDropTime;
	 int points = 0;
	Texture life;
	int lifes = 3;
	Texture bombImage;
	private static final int stepDrop = 5;
	private static final int stepVelosity = 100;
	//public static int highScore;
	//static Preferences prefs;
	public  GameScreen(final Drop gam) {
		this.game = gam;

		life = new Texture(Gdx.files.internal("life.png"));
		shakhta = new Texture(Gdx.files.internal("shakhta.png"));
		bucketImage = new Texture(Gdx.files.internal("box.png"));

		bombMusic = Gdx.audio.newSound(Gdx.files.internal("bomb.mp3"));
		dropSound = Gdx.audio.newSound(Gdx.files.internal("dropping.mp3"));
		rainMusic = Gdx.audio.newMusic(Gdx.files.internal("phon.mp3"));

		rainMusic.setLooping(true);
		rainMusic.play();

		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);
		batch = new SpriteBatch();

		bucket = new Rectangle();

		bucket.x = 800 / 2 - 64 / 2;

		bucket.y = 20;
		bucket.width = 64;
		bucket.height = 64;

		raindropsAndBombs = new Array<RaindropOrBomb>();
		lastDropTime = 0;
		lastDropTime = 0;

		//prefs = Gdx.app.getPreferences("My Preferences"); //получаем файл персональных данных
		//highScore = prefs.getInteger("highscore"); //получаем текущий лучший результат

	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		camera.update();

		game.batch.setProjectionMatrix(camera.combined);
		game.batch.begin();
		game.batch.draw(shakhta, 0, 0);
		game.f.draw(game.batch, "Diamonds Collected: "+ points, 0, 480);
		game.batch.draw(life, 20 , 400);

		game.f.draw(game.batch, ": "+ lifes, 85, 440);

		game.batch.draw(bucketImage, bucket.x, bucket.y);


		if(TimeUtils.nanoTime() - lastDropTime > 1000000000) {
			RaindropOrBomb item = RaindropOrBomb.spawnRaindrop();
			raindropsAndBombs.add(item);
			item.drawRaindrop(game.batch, bombImage, dropImage);
			lastDropTime = TimeUtils.nanoTime();

		}

			for (RaindropOrBomb raindropOrBomb : raindropsAndBombs) {

			raindropOrBomb.drawRaindrop(game.batch, bombImage, dropImage);
			}

		game.batch.end();
		if(Gdx.input.isTouched()) {
			Vector3 touchPos = new Vector3();
			touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			camera.unproject(touchPos);
			bucket.x = touchPos.x - 64 / 2;
		}
		if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) bucket.x -= 200 * Gdx.graphics.getDeltaTime();
		if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) bucket.x += 200 * Gdx.graphics.getDeltaTime();

		if(bucket.x < 0) bucket.x = 0;
		if(bucket.x > 800 - 64) bucket.x = 800 - 64;

		Iterator<RaindropOrBomb> iter = raindropsAndBombs.iterator();
		while(iter.hasNext()) {
			boolean doOverlap = false;
			RaindropOrBomb raindrop = iter.next();
			raindrop.y -= raindrop.getVelosityByPoints(points, stepDrop, stepVelosity)
					* Gdx.graphics.getDeltaTime();
			if (raindrop.y + 64 < 0) {
				iter.remove();
				continue;
			}
			if (raindrop.overlaps(bucket) && (int) raindrop.type == 1) {
				points++;
				dropSound.play();
				doOverlap = true;

			}

			if (raindrop.overlaps(bucket) && (int) raindrop.type == 0) {
				lifes--;
				bombMusic.play();
				doOverlap = true;
				//Gdx.input.vibrate(2000);
			}
			if (doOverlap){
				iter.remove();
			}
			if (TimeUtils.nanoTime() == 500000){
				RaindropOrBomb item = new RaindropOrBomb();
				while (delta>=1f){

					delta-=1f;
				}
			}
			if (lifes==0){
				game.setScreen(new GameOver(game, points));

			}
			//if(points>GameScreen.highScore) {
			//	GameScreen.highScore = points;
			//}


		}

	}

	@Override
	public void dispose() {
		dropImage.dispose();
		bucketImage.dispose();
		dropSound.dispose();
		rainMusic.dispose();
		batch.dispose();
		shakhta.dispose();
		//bomb.dispose();
		raindropsAndBombs.clear();
		raindropsAndBombs = null;
		//prefs.putInteger("highscore", highScore);
		//prefs.flush(); //убедиться, что настройки сохранены
		bombMusic.dispose();
	}


	@Override
	public void show() {
	rainMusic.play();
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


}