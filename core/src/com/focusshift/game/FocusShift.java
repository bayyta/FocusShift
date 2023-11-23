package com.focusshift.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.focusshift.handlers.AssetLoader;
import com.focusshift.handlers.InputHandler;

public class FocusShift extends ApplicationAdapter {

	public static final int WIDTH = 480;
	public static final int HEIGHT = 800;
	public static final String TTILE = "Focus Shift";

	private InputHandler inputhandler;
	private AssetLoader assetloader;
	private Game game;

	@Override
	public void create() {
		System.out.println("creating");
		
		Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
		float screenWidth = Gdx.graphics.getWidth();
		float screenHeight = Gdx.graphics.getHeight();
		float gameWidth = screenWidth / 2;
		float gameHeight = screenHeight / (screenWidth / gameWidth);

		inputhandler = new InputHandler();
		Gdx.input.setInputProcessor(inputhandler);
		assetloader = new AssetLoader();
		game = new Game(assetloader, (int) gameWidth, (int) gameHeight);
	}

	@Override
	public void resize(int width, int height) {
		System.out.println("resizing");
	}

	@Override
	public void render() {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		game.tick(Gdx.graphics.getDeltaTime());
		game.render();
	}

	@Override
	public void pause() {
		System.out.println("pause called");
	}

	@Override
	public void resume() {
		System.out.println("resume called");
	}

	@Override
	public void dispose() {
		System.out.println("dispose called");
		assetloader.dispose();
	}
}
