package com.dethreeca.space_cleaner.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.dethreeca.space_cleaner.SpaceCleaner;
import com.dethreeca.space_cleaner.utils.PlaySoundManager;

public class GameOver extends State {
    Rectangle backGroundRectangle;
    TextureRegion backGroundTexture;
    PlaySoundManager playSoundManager;
    Rectangle gameOverRectangle;
    TextureRegion gameOverTextureRegion;

    public GameOver(GameStateManager gsm) {
        super(gsm);

        // init bg
        backGroundRectangle = new Rectangle();
        backGroundRectangle.width = SpaceCleaner.WIDTH;
        backGroundRectangle.height = SpaceCleaner.HEIGTH;
        backGroundRectangle.x = 0;
        backGroundRectangle.y = 0;
        backGroundTexture = new TextureRegion(new Texture("bg.png"));

        // init game over image
        gameOverRectangle = new Rectangle();
        gameOverRectangle.width = SpaceCleaner.WIDTH * 0.5f;
        gameOverRectangle.height = SpaceCleaner.HEIGTH * 0.5f;
        gameOverRectangle.x = SpaceCleaner.WIDTH / 2 - gameOverRectangle.width / 2;
        gameOverRectangle.y = SpaceCleaner.HEIGTH / 2 - gameOverRectangle.height / 2;
        gameOverTextureRegion = new TextureRegion(new Texture("gameover.png"));

        // init play sounds
        playSoundManager = new PlaySoundManager();
        playSoundManager.playCollisionSound();
    }

    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched()) {
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
    public void renderStatic(SpriteBatch sb) {
        super.renderStatic(sb);
        sb.begin();
        sb.draw(backGroundTexture, backGroundRectangle.x, backGroundRectangle.y, backGroundRectangle.width, backGroundRectangle.height);
        sb.draw(gameOverTextureRegion, gameOverRectangle.x, gameOverRectangle.y, gameOverRectangle.width, gameOverRectangle.height);
        sb.end();
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
