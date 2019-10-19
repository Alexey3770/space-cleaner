package com.dethreeca.space_cleaner.game_object;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface GameObject {
    void update(float dt, Camera camera);
    void render(SpriteBatch sb);
}
