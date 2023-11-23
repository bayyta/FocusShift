package com.focusshift.entities;

import static com.focusshift.handlers.B2DProperties.*;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.focusshift.game.FocusShift;
import com.focusshift.handlers.AssetLoader;

public class PlayerTop {

	private Body body;
	private AssetLoader assetloader;

	private float maxSpeed = 1f;
	private float gravity = 9.81f;

	public PlayerTop(AssetLoader assetloader, World world) {
		this.assetloader = assetloader;
		
		// player
		BodyDef bdef = new BodyDef();
		bdef.position.set((FocusShift.WIDTH / 2) / PPM, 200 / PPM);
		bdef.type = BodyType.DynamicBody;
		body = world.createBody(bdef);

		PolygonShape shape = new PolygonShape();
		shape.setAsBox(10 / PPM, 10 / PPM);

		FixtureDef fdef = new FixtureDef();
		fdef.shape = shape;
		fdef.filter.categoryBits = BIT_PLAYER;
		fdef.filter.maskBits = BIT_GROUND;
		fdef.isSensor = false;
		body.createFixture(fdef).setUserData("player");

		// create foot sensor
		shape.setAsBox(10 / PPM, 2 / PPM, new Vector2(0, 10 / PPM), 0);
		fdef.shape = shape;
		fdef.filter.categoryBits = BIT_PLAYER;
		fdef.filter.maskBits = BIT_GROUND;
		fdef.isSensor = true;
		body.createFixture(fdef).setUserData("foot");
	}

	public void tick(float dt) {
		
		body.applyForceToCenter(0, gravity, true);

		float xspeed = body.getLinearVelocity().x;
		float yspeed = body.getLinearVelocity().y;

		if (xspeed > maxSpeed) {
			body.setLinearVelocity(maxSpeed, yspeed);
		}
		if (xspeed < -maxSpeed) {
			body.setLinearVelocity(-maxSpeed, yspeed);
		}
	}

	public void render(SpriteBatch sb) {
		sb.draw(assetloader.player, body.getPosition().x * PPM - assetloader.player.getRegionWidth() / 2, body.getPosition().y * PPM - assetloader.player.getRegionHeight() / 2 - 8);
	}
	
	public Body getBody() {
		return body;
	}
	
	public float getX() {
		return body.getPosition().x;
	}
	
	public float getY() {
		return body.getPosition().y;
	}
}
