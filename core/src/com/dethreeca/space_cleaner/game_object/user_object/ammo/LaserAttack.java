package com.dethreeca.space_cleaner.game_object.user_object.ammo;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.dethreeca.space_cleaner.game_object.user_object.Ship;

public class LaserAttack extends Ammo {

    private float currentDt;

    private Ship ship;

    private TextureRegion textureRegion;

    /**
     *
     * @param x - position for this object by x
     * @param y - position for this object by x
     * @param width - width for this object
     * @param height - height for this object
     * @param image - image for this object
     */
    public LaserAttack(float x, float y, float width, float height, Texture image, Ship ship) {
        super(new Vector2(x, y), new Rectangle(x, y, width, height));
        textureRegion = new TextureRegion(image);
        this.ship = ship;
        position = new Vector2(x, y);
        currentDt = 0;
    }

    @Override
    public void startAnimation() {
    }

    @Override
    public TextureRegion getTextureRegion() {
        return textureRegion;
    }

    @Override
    public void update(float dt, Camera camera) {
        position.set(ship.getPosition().x + ship.getBoundsSheep().width / 2 - bounds.width / 2,
                ship.getPosition().y + ship.getBoundsSheep().height);
        bounds.setPosition(position);
        if (currentDt >  2) {
            remove();
        } else {
            currentDt += dt;
        }
    }
}
