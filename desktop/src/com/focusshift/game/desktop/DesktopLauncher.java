package com.focusshift.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.focusshift.game.FocusShift;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = FocusShift.TTILE;
		config.width = FocusShift.WIDTH;
		config.height = FocusShift.HEIGHT;
		new LwjglApplication(new FocusShift(), config);
	}
}
