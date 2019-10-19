package com.dethreeca.space_cleaner.view_component;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class TextView implements View {
    private BitmapFont font;
    private Vector2 position;
    private String text;
    private TextureRegion textureRegion;
    private float textHeight;
    private float iconSize;

    public TextView(Vector2 position) {
        font = new BitmapFont();
        font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        this.position = position;
    }

    public void setIcon(Texture texture) {
        this.textureRegion = new TextureRegion(texture);
    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void render(SpriteBatch sb) {
        if (text != null) {
            if (textureRegion != null) {
                sb.draw(textureRegion, position.x, position.y - iconSize, iconSize, iconSize);
                font.draw(sb, text, position.x + textHeight, position.y);

            } else {
                font.draw(sb, text, position.x, position.y);
            }
        }
    }

    @Override
    public void dispose() {
        font.dispose();
    }

    public void setTextHeight(float height) {
        this.textHeight = height;
        this.iconSize = height * 0.6f;
        font.getData().setScale(height * font.getScaleY() / font.getLineHeight());
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setColor(Color color) {
        this.font.setColor(color);
    }
}
