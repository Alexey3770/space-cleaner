package com.dethreeca.space_cleaner.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.dethreeca.space_cleaner.SpaceCleaner;
import com.dethreeca.space_cleaner.utils.PlaySoundManager;
import com.dethreeca.space_cleaner.view_component.Button;

public class GameOver extends State {
    Rectangle backGroundRectangle;
    TextureRegion backGroundTexture;
    PlaySoundManager playSoundManager;
    Rectangle gameOverRectangle;
    Rectangle playAgainRectangle;
    Rectangle backMenuRectangle;
    TextureRegion gameOverTextureRegion;
    Button playAgain;
    Button backMenu;

    public GameOver(final GameStateManager gsm) {
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

        // init buttons
        playAgainRectangle = new Rectangle();
        playAgainRectangle.width = SpaceCleaner.WIDTH * 0.06f;
        playAgainRectangle.height = playAgainRectangle.width;
        playAgainRectangle.x = SpaceCleaner.WIDTH / 2 + playAgainRectangle.width;
        playAgainRectangle.y = SpaceCleaner.HEIGTH / 2  - gameOverRectangle.height / 2;
        playAgain = new Button(new Texture("back.png"), playAgainRectangle);
        playAgain.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick() {
                playSoundManager.playClickSound();
                gsm.push(new GamePlay(gsm));
            }
        });

        backMenuRectangle = new Rectangle();
        backMenuRectangle.width = SpaceCleaner.WIDTH * 0.06f;
        backMenuRectangle.height = backMenuRectangle.width;
        backMenuRectangle.x = SpaceCleaner.WIDTH / 2 - backMenuRectangle.width * 1.5f;
        backMenuRectangle.y = SpaceCleaner.HEIGTH / 2 - gameOverRectangle.height / 2;
        backMenu = new Button(new Texture("cancel.png"), backMenuRectangle);
        backMenu.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick() {
                playSoundManager.playClickSound();
                gsm.push(new GameMenu(gsm));
            }
        });
        addView(playAgain);
        addView(backMenu);

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
        sb.begin();
        sb.draw(backGroundTexture, backGroundRectangle.x, backGroundRectangle.y, backGroundRectangle.width, backGroundRectangle.height);
        sb.draw(gameOverTextureRegion, gameOverRectangle.x, gameOverRectangle.y, gameOverRectangle.width, gameOverRectangle.height);
        sb.end();
        super.renderStatic(sb);
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
