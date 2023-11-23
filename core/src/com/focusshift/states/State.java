package com.focusshift.states;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.focusshift.game.FocusShift;
import com.focusshift.handlers.AssetLoader;

public abstract class State {
	
	protected GSM gsm;
	protected OrthographicCamera cam;
	protected OrthographicCamera hudCam;
	protected AssetLoader assetloader;
	protected Vector3 mouse;
	
	protected boolean entering;
	protected boolean leaving;
	protected State nextState;
	
	protected State(GSM gsm, AssetLoader assetloader) {
		this.gsm = gsm;
		this.assetloader = assetloader;
		cam = new OrthographicCamera();
		cam.setToOrtho(true, FocusShift.WIDTH, FocusShift.HEIGHT);
		hudCam = new OrthographicCamera();
		hudCam.setToOrtho(true, FocusShift.WIDTH, FocusShift.HEIGHT);
		mouse = new Vector3();
		entering = true;
	}
	
	public abstract void handleInput();
	public abstract void tick(float dt);
	public abstract void render(SpriteBatch sb);
}
