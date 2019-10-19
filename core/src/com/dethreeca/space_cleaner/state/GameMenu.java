package com.dethreeca.space_cleaner.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameMenu extends State {

    public GameMenu(GameStateManager gsm){
        super(gsm);
    }

    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched()){
            gsm.set(new GamePlay(gsm));
        }
    }

    @Override
    public void update(float dt) {
        super.update(dt);
        camera.update();
    }

    @Override
    public void render(SpriteBatch sb) {
    }

    @Override
    public void renderStatic(SpriteBatch staticBatch) {
        super.renderStatic(staticBatch);
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
