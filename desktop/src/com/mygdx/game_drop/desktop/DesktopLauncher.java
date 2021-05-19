package com.mygdx.game_drop.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game_drop.Drop;
import com.mygdx.game_drop.GameScreen;

public class DesktopLauncher {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "Drop";
		cfg.width = 800;
		cfg.height = 480;
		new LwjglApplication(new Drop(), cfg);
	}
}
