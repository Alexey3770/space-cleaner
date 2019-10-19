package com.dethreeca.space_cleaner.game_object.user_object.ammo;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.dethreeca.space_cleaner.utils.Animation;

public class IceAttack extends Ammo {

    private Animation animation;
    private float speed;
    private boolean isAnimated = false;

    /**
     *
     * @param x - position for this object by x
     * @param y - position for this object by x
     * @param width - width for this object
     * @param height - height for this object
     * @param image - image for this object
     */
    public IceAttack(float x, float y, float width, float height, Texture image) {
        super(new Vector2(x, y), new Rectangle(x, y, width, height));
        animation = new Animation(new TextureRegion(image), 8, 0.4f);
        speed = height * 3.7f;
    }

    @Override
    public void startAnimation() {
        isAnimated = true;
    }

    @Override
    public TextureRegion getTextureRegion() {
        return animation.getFrame();
    }

    @Override
    public void update(float dt, Camera camera) {
        if (isAnimated) {
            animation.update(dt);
        } else {
            position.y += speed * dt;
            bounds.setPosition(position);
        }
        if (animation.isAnimationFinished()) {
            remove();
        }
    }
}
