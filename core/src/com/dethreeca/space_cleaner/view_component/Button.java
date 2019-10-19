package com.dethreeca.space_cleaner.view_component;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class Button implements View {

    private TextureRegion textureRegion;
    private Rectangle bounds;
    private OnClickListener listener;

    public Button(Texture texture, Rectangle bounds) {
        this.textureRegion = new TextureRegion(texture);
        this.bounds = bounds;
    }

    public void setOnClickListener(OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void update(float dt) {
        if(listener != null && Gdx.input.justTouched() &&
                bounds.contains(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY())) {
            listener.onClick();
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.draw(textureRegion, bounds.x, bounds.y, bounds.width, bounds.height);
    }

    @Override
    public void dispose() {
    }

    public interface OnClickListener {
        void onClick();
    }
}
