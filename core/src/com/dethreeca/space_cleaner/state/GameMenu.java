package com.dethreeca.space_cleaner.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.dethreeca.space_cleaner.SpaceCleaner;

public class GameMenu extends State {
    SpriteBatch batch;
    Rectangle buttonRectangle;
    TextureRegion buttonTexture;
    Rectangle backGroundRectangle;
    TextureRegion backGroundTexture;

    public GameMenu(GameStateManager gsm){
        super(gsm);
        batch = new SpriteBatch();
        buttonRectangle = new Rectangle();
        buttonRectangle.width = SpaceCleaner.WIDTH %600;
        buttonRectangle.height = SpaceCleaner.HEIGTH%600;
        buttonRectangle.x = SpaceCleaner.WIDTH/2-buttonRectangle.width/2;
        buttonRectangle.y = SpaceCleaner.HEIGTH/2-buttonRectangle.height/2;
        buttonTexture = new TextureRegion(new Texture("MenuButton.png"));

        backGroundRectangle = new Rectangle();
        backGroundRectangle.width = SpaceCleaner.WIDTH;
        backGroundRectangle.height = SpaceCleaner.HEIGTH;
        backGroundRectangle.x=0;
        backGroundRectangle.y=0;
        backGroundTexture = new TextureRegion(new Texture("bg.png"));


    }

    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched()){
            float pressMenuButton = buttonRectangle.height/3 ;
            if(Gdx.input.getY()>buttonRectangle.y && Gdx.input.getY()<buttonRectangle.y+pressMenuButton){
                gsm.push(new GamePlay(gsm));
            }
            if(Gdx.input.getY()>buttonRectangle.y+pressMenuButton && Gdx.input.getY()<buttonRectangle.y+2*pressMenuButton){
                gsm.push(new GamePlay(gsm));
            }
            if(Gdx.input.getY()>buttonRectangle.y+2*pressMenuButton && Gdx.input.getY()<buttonRectangle.y+3*pressMenuButton){
                gsm.push(new GamePlay(gsm));
            }
            gsm.set(new GamePlay(gsm));
        }
    }

    @Override
    public void handleAccelerometer() {
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
        sb.draw(buttonTexture, buttonRectangle.x,buttonRectangle.y, 500, 500);
        sb.end();
    }

    @Override
    public void dispose() {
        super.dispose();
    }

}
