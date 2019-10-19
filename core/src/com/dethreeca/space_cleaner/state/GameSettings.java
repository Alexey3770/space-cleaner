package com.dethreeca.space_cleaner.state;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.dethreeca.space_cleaner.SpaceCleaner;

public class  GameSettings extends State {

    SpriteBatch batch;
    Rectangle backGroundRectangle;
    TextureRegion backGroundTexture;
    Rectangle checkRectangle;
    TextureRegion checkTexture;
    Rectangle crossRectangle;
    TextureRegion crossTexture;


    public GameSettings(GameStateManager gsm) {
        super(gsm);
        batch = new SpriteBatch();
        checkRectangle = new Rectangle();
        checkRectangle.width = Float.valueOf(SpaceCleaner.WIDTH * 0.15f);
        checkRectangle.height = Float.valueOf(SpaceCleaner.HEIGTH * 0.15f);
        checkRectangle.x = SpaceCleaner.WIDTH - checkRectangle.width  ;
        checkRectangle.y = checkRectangle.height ;
        checkTexture = new TextureRegion(new Texture("checkbox.png"));

        backGroundRectangle = new Rectangle();
        backGroundRectangle.width = SpaceCleaner.WIDTH;
        backGroundRectangle.height = SpaceCleaner.HEIGTH;
        backGroundRectangle.x=0;
        backGroundRectangle.y=0;
        backGroundTexture = new TextureRegion(new Texture("bg.png"));

    }

    @Override
    public void handleAccelerometer() {


    }

    @Override
    protected void handleInput() {

    }
    @Override
    public void renderStatic(SpriteBatch sb) {
        sb.begin();
        sb.draw(backGroundTexture, backGroundRectangle.x,backGroundRectangle.y,backGroundRectangle.width,backGroundRectangle.height);
        sb.draw(checkTexture,checkRectangle.x,checkRectangle.y,checkRectangle.width,checkRectangle.height);
        sb.end();
    }
}
