package com.focusshift.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.focusshift.game.FocusShift;
import com.focusshift.handlers.AssetLoader;

public class TransitionState extends State {

	private State prev;
	private State next;

	// black fade stuff
	private float maxTime;
	private float timer;
	private float alpha;

	// private int size;
	// private int r;
	// private Random random;
	// private Array<Integer> xValues;
	// private Array<Integer> yValues;
	// private Array<Integer> xR;
	// private Array<Integer> yR;

	public TransitionState(GSM gsm, AssetLoader assetloader, State prev, State next) {
		super(gsm, assetloader);
		this.prev = prev;
		this.next = next;

		maxTime = 1;
		// size = 80;
		// random = new Random();
		// xValues = new Array<Integer>();
		// yValues = new Array<Integer>();
		// xR = new Array<Integer>();
		// yR = new Array<Integer>();
		// for (int y = 0; y < TestApp.HEIGHT / size; y++) {
		// for (int x = 0; x < TestApp.WIDTH / size; x++) {
		// xValues.add(x * size);
		// yValues.add(y * size);
		// }
		// }

		// r = random.nextInt(xValues.size) - 1;
		// xR.add(xValues.get(r));
		// yR.add(yValues.get(r));
		// xValues.removeIndex(r);
		// yValues.removeIndex(r);
	}

	public void handleInput() {
	}

	public void tick(float dt) {
		timer += dt;

		if (timer < maxTime / 2) prev.tick(dt);
		else next.tick(dt);

		if (timer >= maxTime) gsm.set(next);
	}

	public void render(SpriteBatch sb) {
		if (timer < maxTime / 2) {
			alpha = timer / (maxTime / 2);
			prev.render(sb);
		} else {
			alpha = 2 - (timer / (maxTime / 2));
			next.render(sb);
		}
		if (alpha < 0) alpha = 0;
		sb.setProjectionMatrix(cam.combined);
		sb.begin();
		sb.setColor(0, 0, 0, alpha);
		sb.draw(assetloader.blackblock, 0, 0, FocusShift.WIDTH, FocusShift.HEIGHT);
		sb.setColor(1, 1, 1, 1);
		sb.end();
		// if (!done) {
		// if (timer > 0.01) {
		// r = random.nextInt(xValues.size);
		// xR.add(xValues.get(r));
		// yR.add(yValues.get(r));
		// xValues.removeIndex(r);
		// yValues.removeIndex(r);
		// if (xValues.size == 0) done = true;
		// timer = 0;
		// }
		// }
		// for (int i = 0; i < yR.size; i++) {
		// sb.draw(assetloader.blackblock, xR.get(i), yR.get(i));
		// }
	}
}
