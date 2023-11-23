package com.focusshift.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.focusshift.handlers.AssetLoader;
import com.focusshift.handlers.Box;

public class DrawString extends Box {

	private TextureRegion[][] fontSheet;
	private String msg;
	private int size;
	private int space;
	public static int textSize = 22;
	public static int numSize = 22;

	public DrawString(String msg, float x, float y, int size, AssetLoader assetloader, boolean center) {
		this.size = size;
		space = 0;

		setText(msg);

		if (center) {
			this.x = x - (width + ((msg.length() - 1) * space)) / 2;
			this.y = y - height / 2;
		} else {
			this.x = x;
			this.y = y;
		}

		int numCols = assetloader.fontsheet.getWidth() / 30;
		int numRows = assetloader.fontsheet.getHeight() / 30;

		fontSheet = new TextureRegion[numRows][numCols];

		for (int row = 0; row < numRows; row++) {
			for (int col = 0; col < numCols; col++) {
				fontSheet[row][col] = new TextureRegion(assetloader.fontsheet, 30 * col, 30 * row, 30, 30);
				fontSheet[row][col].flip(false, true);
			}
		}
	}

	public void setText(String text) {
		this.msg = text;
		this.x = x + width / 2;
		width = size * msg.length();
		height = size;
		this.x = x - width / 2;
	}

	public void setPos(float x, float y, boolean center) {
		if (center) {
			this.x = x - width / 2;
		} else this.x = x;
		this.y = y;
	}
	
	public void changeX(float dec) {
		this.x += dec;
	}
	
	public float getxCenter() {
		return FocusShift.WIDTH / 2 - width / 2;
	}

	public void render(SpriteBatch sb) {
		for (int i = 0; i < msg.length(); i++) {
			char c = msg.charAt(i);
			if (c >= 'a' && c <= 'z') c -= 'a';
			else if (c >= '0' && c <= '9') {
				c -= '0';
				c += 26;
			} else if (c == '-' || c == '+') {
				c -= '-';
				c += 38;
			}
			int index = (int) c;
			int row = index / fontSheet[0].length;
			int col = index % fontSheet[0].length;
			sb.draw(fontSheet[row][col], x + (size + space) * i, y, size, size);
		}
	}
}
