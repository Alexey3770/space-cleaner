package com.dethreeca.space_cleaner.state;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dethreeca.space_cleaner.SpaceCleaner;

public class GamePlay extends State {
    private State.GameState stateGame = State.GameState.RUN;

    public GamePlay(GameStateManager gsm) {
        super(gsm);
        camera.setToOrtho(false, SpaceCleaner.WIDTH / 2,
                SpaceCleaner.HEIGTH / 2);
        stateGame = GameState.RUN;
    }

    @Override
    protected void handleInput() {
    }

    @Override
    public void handleAccelerometer() {
    }

    @Override
    public void update(float dt) {
        handleInput();
        handleAccelerometer();
        camera.update();
    }

    @Override
    public void render(SpriteBatch sb) {
    }

    @Override
    public void renderStatic(SpriteBatch staticBatch) {
        staticBatch.begin();
        staticBatch.end();
    }

    @Override
    public void dispose() {
    }
}
