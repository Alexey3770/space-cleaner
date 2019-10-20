package com.dethreeca.space_cleaner.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.dethreeca.space_cleaner.SpaceCleaner;
import com.dethreeca.space_cleaner.utils.PlaySoundManager;
import com.dethreeca.space_cleaner.view_component.Button;

public class GameMenu extends State {
    Rectangle backGroundRectangle;
    Rectangle startPlayGameRectangle;
    Rectangle openSettingsGameGroundRectangle;
    TextureRegion backGroundTexture;
    Button startPlayGameButton;
    Button openSettingsGameButton;
    PlaySoundManager playSoundManager;

    public GameMenu(final GameStateManager gsm){
        super(gsm);

        // init bg
        backGroundRectangle = new Rectangle();
        backGroundRectangle.width = SpaceCleaner.WIDTH;
        backGroundRectangle.height = SpaceCleaner.HEIGTH;
        backGroundRectangle.x=0;
        backGroundRectangle.y=0;
        backGroundTexture = new TextureRegion(new Texture("bg.png"));

        // init play sounds
        playSoundManager = new PlaySoundManager();

        // init button start play
        startPlayGameRectangle = new Rectangle();
        startPlayGameRectangle.width = SpaceCleaner.WIDTH * 0.2f;
        startPlayGameRectangle.height = SpaceCleaner.HEIGTH * 0.2f;
        startPlayGameRectangle.x = SpaceCleaner.WIDTH / 2 - startPlayGameRectangle.width / 2;
        startPlayGameRectangle.y = SpaceCleaner.HEIGTH / 2;
        startPlayGameButton = new Button(new Texture("play.png"), startPlayGameRectangle);
        startPlayGameButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick() {
                playSoundManager.playClickSound();
                gsm.push(new GamePlay(gsm));
            }
        });

        // init button settings game
        openSettingsGameGroundRectangle = new Rectangle();
        openSettingsGameGroundRectangle.width = SpaceCleaner.WIDTH * 0.2f;
        openSettingsGameGroundRectangle.height = SpaceCleaner.HEIGTH * 0.2f;
        openSettingsGameGroundRectangle.x = SpaceCleaner.WIDTH / 2 - openSettingsGameGroundRectangle.width / 2;
        openSettingsGameGroundRectangle.y = SpaceCleaner.HEIGTH / 2 - startPlayGameRectangle.width * 0.6f;
        openSettingsGameButton = new Button(new Texture("settings.png"), openSettingsGameGroundRectangle);
        openSettingsGameButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick() {
                playSoundManager.playClickSound();
                gsm.push(new GameSettings(gsm));
            }
        });

        addView(openSettingsGameButton);
        addView(startPlayGameButton);
    }

    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched()){

        }
    }

    @Override
    public void update(float dt) {
        super.update(dt);
    }

    @Override
    public void render(SpriteBatch sb) {
    }

    @Override
    public void renderStatic(SpriteBatch sb) {
        sb.begin();
        sb.draw(backGroundTexture, backGroundRectangle.x,backGroundRectangle.y,backGroundRectangle.width,backGroundRectangle.height);
        sb.end();
        super.renderStatic(sb);
    }

    @Override
    public void dispose() {
        super.dispose();
    }

}
