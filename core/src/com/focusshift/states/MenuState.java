package com.focusshift.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.focusshift.game.DrawString;
import com.focusshift.game.FocusShift;
import com.focusshift.game.Particle;
import com.focusshift.handlers.AssetLoader;

public class MenuState extends State {

	private DrawString easy, normal, hard, insane;

	private Array<Particle> particles;

	private float rot;
	private boolean playdown;

	public MenuState(GSM gsm, AssetLoader assetloader) {
		super(gsm, assetloader);
		easy = new DrawString("easy", FocusShift.WIDTH, 470, DrawString.textSize, assetloader, false);
		normal = new DrawString("normal", FocusShift.WIDTH, 525, DrawString.textSize, assetloader, false);
		hard = new DrawString("hard", FocusShift.WIDTH, 580, DrawString.textSize, assetloader, false);
		insane = new DrawString("insane", FocusShift.WIDTH, 635, DrawString.textSize, assetloader, false);

		particles = new Array<Particle>();
		for (int i = 0; i < 100; i++) {
			particles.add(new Particle(assetloader));
		}

		rot = 0;
		playdown = false;
	}

	public void handleInput() {
		playdown = false;
//		if (InputHandler.touchDown) {
//			mouse.x = Gdx.input.getX();
//			mouse.y = Gdx.input.getY();
//			cam.unproject(mouse);
//
//			if (contains(200, 491, mouse.x, mouse.y, assetloader.play)) {
//				playdown = true;
//			}
//		}
		if (Gdx.input.justTouched()) {
			mouse.x = Gdx.input.getX();
			mouse.y = Gdx.input.getY();
			cam.unproject(mouse);
			
			if (contains(200, 491, mouse.x, mouse.y, assetloader.play)) {
				System.out.println("play");
				gsm.set(new PlayState(gsm, assetloader));
			}
		}
		
		if (Gdx.input.justTouched()) {
			mouse.x = Gdx.input.getX();
			mouse.y = Gdx.input.getY();
			cam.unproject(mouse);

			if (contains(200, 491, mouse.x, mouse.y, assetloader.play)) {
				System.out.println("play");
			} else if (contains(132, 581, mouse.x, mouse.y, assetloader.highscores)) {
				System.out.println("highscores");
			} else if (contains(200, 680, mouse.x, mouse.y, assetloader.help)) {
				System.out.println("whatever");
			}
			if (easy.contains(mouse.x, mouse.y)) {
				System.out.println("easy");
				gsm.set(new TransitionState(gsm, assetloader, this, new PlayState(gsm, assetloader)));
			} else if (normal.contains(mouse.x, mouse.y)) {
				System.out.println("normal");
			} else if (hard.contains(mouse.x, mouse.y)) {
				System.out.println("hard");
			} else if (insane.contains(mouse.x, mouse.y)) {
				System.out.println("insane");
			} else if (mouse.x >= 216 && mouse.x < 216 + assetloader.backbutton.getRegionWidth() && mouse.y >= 716 && mouse.y < 716 + assetloader.backbutton.getRegionHeight()) {
				System.out.println("back");
			}
			if (mouse.x >= 407 && mouse.x < 407 + assetloader.options.getRegionWidth() && mouse.y >= 28 && mouse.y < 28 + assetloader.options.getRegionHeight()) {
				System.out.println("options");
			}
		}
	}

	private boolean contains(float x, float y, float mouseX, float mouseY, TextureRegion r) {
		return mouseX >= x && mouseX < x + r.getRegionWidth() && mouseY >= y && mouseY < y + r.getRegionHeight();
	}

	public void tick(float dt) {
		rot += dt * 60;
		handleInput();
		for (int i = 0; i < particles.size; i++) {
			particles.get(i).tick(dt);
			if (particles.get(i).getX() < -150 || particles.get(i).getX() > FocusShift.WIDTH + 150 || particles.get(i).getY() < -150 || particles.get(i).getY() > FocusShift.HEIGHT + 150) {
				particles.removeIndex(i);
			}
			if (particles.size < 50) particles.add(new Particle(assetloader));
		}
	}

	public void render(SpriteBatch sb) {
		sb.setProjectionMatrix(cam.combined);
		sb.begin();
		sb.enableBlending();
		sb.draw(assetloader.menubackground, 0, 0);
		if (!playdown) sb.draw(assetloader.play, 200, 491);
		else sb.draw(assetloader.playlight, 200 - 28, 491 - 27);
		sb.draw(assetloader.highscores, 132, 581);
		sb.draw(assetloader.help, 200, 680);
		
		sb.draw(assetloader.options, 407, 28, assetloader.options.getRegionWidth() / 2 + 1, assetloader.options.getRegionHeight() / 2 + 1, assetloader.options.getRegionWidth(),
				assetloader.options.getRegionHeight(), 1, 1, rot);	
		for (int i = 0; i < particles.size; i++) {
			particles.get(i).render(sb);
		}		
		sb.end();
	}
}
