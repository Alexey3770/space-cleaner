package com.dethreeca.space_cleaner.game_object.space_object;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.dethreeca.space_cleaner.game_object.GameObject;
import com.dethreeca.space_cleaner.utils.Animation;

public abstract class SpaceObject implements GameObject {

    private Vector3 position;
    private Vector2 garbagePos;
    private Rectangle bounds;
    private TextureRegion textureRegion;
    private float angle = 0;
    private float angleSpeed = 0;
    private boolean canRemove = false;

    public SpaceObject(float x, float y, float width, float height, float angleSpeed, Texture image) {
        position = new Vector3(x, y, 0);
        garbagePos = new Vector2(x, y);
        bounds = new Rectangle(garbagePos.x, garbagePos.y, width, height);
        textureRegion = new TextureRegion(image);
        this.angleSpeed = angleSpeed;
    }

    public boolean collides(Rectangle player) {
        double length = Math.sqrt(Math.pow(((player.x + player.width /2) - (bounds.x + bounds.width / 2)), 2)
                + Math.pow(((player.y + player.height / 2) - (bounds.y + bounds.height / 2)), 2));
        return length * 1.2 < player.width / 2 + bounds.width/ 2;
    }

    @Override
    public void update(float dt, Camera camera) {
        angle += angleSpeed * dt;
        if(angle > 360) {
            angle -= 360;
        }
        if(angle < 0) {
            angle = 360 + angle;
        }

        canRemove = camera.position.y - camera.viewportHeight / 2 > position.y + bounds.height;
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.draw(textureRegion, position.x, position.y
                , bounds.width / 2
                , bounds.height / 2
                , bounds.width, bounds.height
                , 1F, 1F, angle);
    }

    public Rectangle getBoundsSpace() {
        return bounds;
    }

    public Vector3 getPosition() {
        return position;
    }

    public float getAngle() {
        return angle;
    }

    public boolean canRemove() {
        return canRemove;
    }

    public void remove() {
        canRemove = true;
    }
}
