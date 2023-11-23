package com.focusshift.states;

import static com.focusshift.handlers.B2DProperties.*;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.focusshift.entities.PlayerBottom;
import com.focusshift.entities.PlayerTop;
import com.focusshift.game.FocusShift;
import com.focusshift.game.Game;
import com.focusshift.handlers.AssetLoader;
import com.focusshift.handlers.MyContactListener;

public class PlayState extends State {

	private boolean debug = false;

	private World world;
	private Box2DDebugRenderer b2dr;

	private OrthographicCamera b2dCam;

	private MyContactListener cl;

	private PlayerTop playertop;
	private PlayerBottom playerbottom;

	public PlayState(GSM gsm, AssetLoader assetloader) {
		super(gsm, assetloader);

		world = new World(new Vector2(0, 0), true);
		cl = new MyContactListener();
		world.setContactListener(cl);

		b2dr = new Box2DDebugRenderer();

		playertop = new PlayerTop(assetloader, world);
		playerbottom = new PlayerBottom(assetloader, world);

		// static body - doesn't move
		// kinematic body - set forces individually
		// dynamic body - always affected by world forces

		// create platform
		BodyDef bdef = new BodyDef();
		bdef.position.set(FocusShift.WIDTH / 2 / PPM, (FocusShift.HEIGHT / 2) / PPM);
		bdef.type = BodyType.StaticBody;
		Body body = world.createBody(bdef);

		PolygonShape shape = new PolygonShape();
		shape.setAsBox(FocusShift.WIDTH / 2 / PPM, 0 / PPM);

		FixtureDef fdef = new FixtureDef();
		fdef.shape = shape;
		fdef.filter.categoryBits = BIT_GROUND;
		fdef.filter.maskBits = BIT_PLAYER;
		body.createFixture(fdef).setUserData("ground");

		// set up box2d cam
		b2dCam = new OrthographicCamera();
		b2dCam.setToOrtho(true, FocusShift.WIDTH / PPM, FocusShift.HEIGHT / PPM);
	}

	public void handleInput() {
		if (Gdx.input.isKeyJustPressed(Keys.W) || Gdx.input.isKeyJustPressed(Keys.UP)) {
			if (cl.isPlayerOnGround()) {
				playertop.getBody().applyForceToCenter(0, -200, true);
				playerbottom.getBody().applyForceToCenter(0, 200, true);
			}
		}
		if (Gdx.input.isKeyPressed(Keys.A) || Gdx.input.isKeyPressed(Keys.LEFT)) {
			playertop.getBody().applyForceToCenter(-4, 0, true);
			playerbottom.getBody().applyForceToCenter(-4, 0, true);
			if (!assetloader.player.isFlipX()) assetloader.player.flip(true, false);
		}
		if (Gdx.input.isKeyPressed(Keys.D) || Gdx.input.isKeyPressed(Keys.RIGHT)) {
			playertop.getBody().applyForceToCenter(4, 0, true);
			playerbottom.getBody().applyForceToCenter(4, 0, true);
			if (assetloader.player.isFlipX()) assetloader.player.flip(true, false);
		}
	}

	public void tick(float dt) {
		handleInput();

		world.step(dt, 1, 1);
		playertop.tick(dt);
		playerbottom.tick(dt);
	}

	public void render(SpriteBatch sb) {
		cam.position.set(playertop.getX() * PPM, FocusShift.HEIGHT / 2, 0);
		cam.update();
		b2dCam.position.set(playertop.getX(), FocusShift.HEIGHT / 2 / PPM, 0);
		b2dCam.update();
		sb.setProjectionMatrix(cam.combined);
		sb.begin();
		sb.draw(assetloader.ground, 0, FocusShift.HEIGHT / 2);
		sb.draw(assetloader.ground, 114, FocusShift.HEIGHT / 2);
		sb.draw(assetloader.ground, 114 * 2, FocusShift.HEIGHT / 2);
		sb.draw(assetloader.ground, 114 * 3, FocusShift.HEIGHT / 2);
		playertop.render(sb);
		playerbottom.render(sb);
		sb.draw(assetloader.diamondAnimation.getKeyFrame(Game.time), 10, 10, 16, 16);
		sb.end();
		if (debug) b2dr.render(world, b2dCam.combined);
	}
}
