package com.dethreeca.space_cleaner.game_object.user_object.ammo;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.dethreeca.space_cleaner.game_object.user_object.UserObject;

public abstract class Ammo implements UserObject {

    private boolean canRemove = false;

    protected Vector2 position;
    protected Rectangle bounds;

    public Ammo(Vector2 position, Rectangle bounds) {
        this.position = position;
        this.bounds = bounds;
    }

    public abstract void startAnimation();

    public abstract TextureRegion getTextureRegion();

    @Override
    public void render(SpriteBatch sb) {
        sb.draw(getTextureRegion(), position.x, position.y, bounds.width, bounds.height);
    }

    public boolean canRemove() {
        return canRemove;
    }

    public void remove() {
        canRemove = true;
    }

    @Override
    public Rectangle getBounds() {
        return bounds;
    }
}
