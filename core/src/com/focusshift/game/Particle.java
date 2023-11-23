package com.focusshift.game;

import java.util.Random;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.focusshift.handlers.AssetLoader;
import com.focusshift.handlers.Box;

public class Particle extends Box {

	private AssetLoader assetloader;
	private float alpha;
	private float maxAlpha;
	private Random random;
	private double dir;
	private double nx, ny;
	private double timer;
	private int resetTimer;
	private double speedX, speedY;
	private boolean switchdir;
	private double turn;
	private boolean shouldTurn;
	private boolean black;

	public Particle(AssetLoader assetloader) {
		this.assetloader = assetloader;
		random = new Random();
		x = random.nextInt(480);
		y = random.nextInt(800);
		width = height = random.nextInt(3) + 1;
		alpha = 0;
		maxAlpha = random.nextFloat();
		if (maxAlpha > 0.5) {
			if (random.nextInt(3) == 2) maxAlpha = 1;
			else maxAlpha = 0.5f;
		}
		if (random.nextBoolean()) switchdir = true;
		else switchdir = false;
		timer = 0;
		resetTimer = random.nextInt(4) + 3;
		speedX = random.nextDouble();
		speedY = random.nextDouble();
		dir = random.nextDouble() * 6 - 3;
		nx = speedX * Math.cos(dir);
		ny = speedY * Math.sin(dir);
		turn = random.nextDouble() * 0.05;
		if (random.nextBoolean()) shouldTurn = false;
		else shouldTurn = true;
		if (random.nextInt(6) == 3) {
			black = true;
		} else black = false;
	}

	public void tick(float dt) {
		timer += dt;
		if (alpha < maxAlpha) {
			alpha += dt / (random.nextInt(30) + 30);
			if (alpha > maxAlpha) alpha = maxAlpha;
		}
		x += nx;
		y += ny;
		nx = speedX * Math.cos(dir);
		ny = speedY * Math.sin(dir);
		if (shouldTurn) {
			if (switchdir) dir += turn;
			else dir -= turn;
		}
		if (timer > resetTimer) {
			timer = 0;
			resetTimer = random.nextInt(4) + 3;
			if (random.nextBoolean()) switchdir = !switchdir;
			shouldTurn = !shouldTurn;
		}
	}

	public void render(SpriteBatch sb) {
		if (black) sb.setColor(0.0f, 0.0f, 0.0f, alpha);
		else sb.setColor(1.0f, 1.0f, 1.0f, alpha);
		sb.draw(assetloader.particle, x, y, width, height);
		sb.setColor(1, 1, 1, 1);
	}
}
