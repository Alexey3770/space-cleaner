package com.dethreeca.space_cleaner.view_component;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface View {
    void update(float dt);
    void render(SpriteBatch sb);
    void dispose();
}
