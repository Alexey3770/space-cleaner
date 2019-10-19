package com.dethreeca.space_cleaner.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.dethreeca.space_cleaner.SpaceCleaner;

public class GameMenu extends State {
    OrthographicCamera camera;
    SpriteBatch batch;
    Rectangle buttonRectangle;
    Texture buttonTexture;
    public GameMenu(GameStateManager gsm){
        super(gsm);
        batch = new SpriteBatch();
        buttonRectangle = new Rectangle();
        buttonRectangle.width = 64;
        buttonRectangle.height = 64;
        buttonRectangle.x = SpaceCleaner.WIDTH/2-buttonRectangle.width/2;
        buttonRectangle.y = SpaceCleaner.HEIGTH/2-buttonRectangle.height/2;
        buttonTexture = new Texture("button1.png");

    }

    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched()){
            gsm.set(new GamePlay(gsm));
        }
    }

    @Override
    public void handleAccelerometer() {
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
