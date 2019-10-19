package com.dethreeca.space_cleaner.game_object;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class Earth implements GameObject {

    private final float ANGLE_SPEED = 7f;

    private float angle = 0f;
    private Rectangle bounds;
    private TextureRegion texture;

    public Earth(Rectangle bounds, Texture texture) {
        this.texture = new TextureRegion(texture);
        this.bounds = bounds;
    }

    public float getAngle() {
        return angle;
    }

    @Override
    public void update(float dt, Camera camera) {
        bounds.setY(camera.position.y -  camera.viewportHeight);
        angle += ANGLE_SPEED * dt;

        if(angle > 360) {
            angle -= 360;
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.draw(texture, bounds.x, bounds.y,
                bounds.width / 2, bounds.height / 2,
                bounds.width, bounds.height, 1, 1, angle);
    }
}
