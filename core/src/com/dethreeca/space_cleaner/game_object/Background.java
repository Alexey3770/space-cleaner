package com.dethreeca.space_cleaner.game_object;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Background implements GameObject {

    private Vector2 firstPosition,
            secondPosition;
    private float width, height;

    private Texture texture;

    public Background(Texture texture, Vector2 position, float width, float height) {
        this.width = width;
        this.height = height;
        this.texture = texture;
        this.firstPosition = position;
        this.secondPosition = new Vector2(position);
        this.secondPosition.add(0, height);

    }

    @Override
    public void update(float dt, Camera camera) {
        if (camera.position.y - (camera.viewportHeight) > firstPosition.y + height)
            firstPosition.add( 0, height * 2);
        if (camera.position.y - (camera.viewportHeight) > secondPosition.y + height)
            secondPosition.add(0, height * 2);
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.draw(texture, firstPosition.x, firstPosition.y, width, height);
        sb.draw(texture, secondPosition.x, secondPosition.y, width, height);
    }
}
