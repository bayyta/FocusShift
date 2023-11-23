package com.focusshift.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.focusshift.handlers.AssetLoader;
import com.focusshift.states.GSM;
import com.focusshift.states.MenuState;

public class Game {

	private GSM gsm;

	private SpriteBatch sb;

	public static float time;
	private float fpstime;

	public Game(AssetLoader assetloader, int gameWidth, int gameHeight) {
		gsm = new GSM();
		gsm.push(new MenuState(gsm, assetloader));
		
		sb = new SpriteBatch();

		time = 0;
		fpstime = 0;
	}

	public void tick(float dt) {
		gsm.tick(dt);
		if (time >= 1000000) time = 0;
		time += dt;
		fpstime += dt;
		if (fpstime > 1) {
			System.out.println("fps: " + (int) (1 / dt));
			fpstime = 0;
		}
	}

	public void render() {
		gsm.render(sb);
	}
}
