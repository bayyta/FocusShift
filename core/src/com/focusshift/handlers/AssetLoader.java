package com.focusshift.handlers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AssetLoader {

	public Texture spritesheet, fontsheet, particle;

	public TextureRegion menubackground, particles, options, blackblock, backbutton, player, play, highscores, help, playlight, ground;
	private TextureRegion d1, d2, d3, d4, d5, d6, d7, d8, d9, d10, d11, d12, d13, d14, d15, d16;

	public Animation diamondAnimation;

	public AssetLoader() {
		load();
	}

	private void load() {
		spritesheet = new Texture(Gdx.files.internal("imgs/spritesheet.png"));
		spritesheet.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);

		fontsheet = new Texture(Gdx.files.internal("imgs/font.png"));
		fontsheet.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);

		particle = new Texture(Gdx.files.internal("imgs/particle.png"));
		particle.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);

		particles = new TextureRegion(particle, 0, 0);
		particles.flip(false, true);

		menubackground = new TextureRegion(spritesheet, 0, 0, 480, 800);
		menubackground.flip(false, true);

		options = new TextureRegion(spritesheet, 480, 735, 62, 65);
		options.flip(false, true);

		blackblock = new TextureRegion(spritesheet, 480, 239, 80, 80);
		blackblock.flip(false, true);

		backbutton = new TextureRegion(spritesheet, 560, 239, 47, 22);
		backbutton.flip(false, true);

		player = new TextureRegion(spritesheet, 480, 239 + 80, 32, 38);
		player.flip(false, true);

		play = new TextureRegion(spritesheet, 480, 0, 81, 23);
		play.flip(false, true);

		playlight = new TextureRegion(spritesheet, 480, 23, 141, 73); // 92, 35
		playlight.flip(false, true);

		highscores = new TextureRegion(spritesheet, 561, 0, 228, 24);
		highscores.flip(false, true);

		help = new TextureRegion(spritesheet, 789, 0, 85, 25);
		help.flip(false, true);
		
		ground = new TextureRegion(spritesheet, 560, 261, 114, 53);
		ground.flip(false, true);

		loadDiamond();
	}

	private void loadDiamond() {
		d1 = new TextureRegion(spritesheet, 480, 359, 32, 32);
		d1.flip(false, true);
		d2 = new TextureRegion(spritesheet, 480 + 32, 359, 32, 32);
		d2.flip(false, true);
		d3 = new TextureRegion(spritesheet, 480 + 32 * 2, 359, 32, 32);
		d3.flip(false, true);
		d4 = new TextureRegion(spritesheet, 480 + 32 * 3, 359, 32, 32);
		d4.flip(false, true);
		d5 = new TextureRegion(spritesheet, 480, 359 + 32, 32, 32);
		d5.flip(false, true);
		d6 = new TextureRegion(spritesheet, 480 + 32, 359 + 32, 32, 32);
		d6.flip(false, true);
		d7 = new TextureRegion(spritesheet, 480 + 32 * 2, 359  + 32, 32, 32);
		d7.flip(false, true);
		d8 = new TextureRegion(spritesheet, 480 + 32 * 3, 359 + 32, 32, 32);
		d8.flip(false, true);
		d9 = new TextureRegion(spritesheet, 480, 359 + 32 * 2, 32, 32);
		d9.flip(false, true);
		d10 = new TextureRegion(spritesheet, 480 + 32, 359 + 32 * 2, 32, 32);
		d10.flip(false, true);
		d11 = new TextureRegion(spritesheet, 480 + 32 * 2, 359 + 32 * 2, 32, 32);
		d11.flip(false, true);
		d12 = new TextureRegion(spritesheet, 480 + 32 * 3, 359 + 32 * 2, 32, 32);
		d12.flip(false, true);
		d13 = new TextureRegion(spritesheet, 480, 359 + 32 * 3, 32, 32);
		d13.flip(false, true);
		d14 = new TextureRegion(spritesheet, 480 + 32, 359 + 32 * 3, 32, 32);
		d14.flip(false, true);
		d15 = new TextureRegion(spritesheet, 480 + 32 * 2, 359 + 32 * 3, 32, 32);
		d15.flip(false, true);
		d16 = new TextureRegion(spritesheet, 480 + 32 * 3, 359 + 32 * 3, 32, 32);
		d16.flip(false, true);
		
		TextureRegion[] d = {d1, d2, d3, d4, d5, d6, d7, d8, d9, d10, d11, d12, d13, d14, d15, d16};
		
		diamondAnimation = new Animation(0.1f, d);
		diamondAnimation.setPlayMode(Animation.PlayMode.LOOP);
	}

	public void dispose() {
		spritesheet.dispose();
		fontsheet.dispose();
		particle.dispose();
	}
}
