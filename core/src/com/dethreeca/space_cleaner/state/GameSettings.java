package com.dethreeca.space_cleaner.state;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.dethreeca.space_cleaner.SpaceCleaner;
import com.dethreeca.space_cleaner.utils.PlaySoundManager;
import com.dethreeca.space_cleaner.utils.Settings;
import com.dethreeca.space_cleaner.view_component.Button;
import com.dethreeca.space_cleaner.view_component.TextView;

public class  GameSettings extends State {

    Rectangle backGroundRectangle;
    Rectangle checkBoxRectangle;
    Rectangle backMenuRectangle;
    TextureRegion backGroundTexture;
    Button checkBoxButton;
    Button backMenuButton;
    BitmapFont font;
    boolean isClick;
    PlaySoundManager playSoundManager;


    public GameSettings(final GameStateManager gsm) {
        super(gsm);

        //init bg
        backGroundRectangle = new Rectangle();
        backGroundRectangle.width = SpaceCleaner.WIDTH;
        backGroundRectangle.height = SpaceCleaner.HEIGTH;
        backGroundRectangle.x=0;
        backGroundRectangle.y=0;
        backGroundTexture = new TextureRegion(new Texture("bg.png"));
        isClick = Settings.getInstance().isSoundsOn();

        //init button checkbox
        playSoundManager = new PlaySoundManager();
        checkBoxRectangle = new Rectangle();
        checkBoxRectangle.width = SpaceCleaner.WIDTH * 0.06f;
        checkBoxRectangle.height = checkBoxRectangle.width;
        checkBoxRectangle.x = SpaceCleaner.WIDTH - checkBoxRectangle.width - SpaceCleaner.WIDTH * 0.05f;
        checkBoxRectangle.y = SpaceCleaner.HEIGTH - checkBoxRectangle.height - SpaceCleaner.HEIGTH * 0.05f ;
        checkBoxButton = new Button(new Texture("checkbox.png"), checkBoxRectangle);
        checkBoxButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick() {
                removeView(checkBoxButton);
                if (isClick) {
                    checkBoxButton.setTexture(new Texture("crossBox.png"));
                } else {
                    checkBoxButton.setTexture(new Texture("checkbox.png"));
                }
                isClick = !isClick;
                addView(checkBoxButton);
                Settings.getInstance().setSoundsOn(isClick);
                playSoundManager.playClickSound();
            }
        });

        //init button Back
        backMenuRectangle = new Rectangle();
        backMenuRectangle.width = SpaceCleaner.WIDTH * 0.08f;
        backMenuRectangle.height = backMenuRectangle.width;
        backMenuRectangle.x = SpaceCleaner.WIDTH / 2 - backMenuRectangle.width / 2;
        backMenuRectangle.y = SpaceCleaner.HEIGTH / 10;
        backMenuButton = new Button(new Texture("back.png"),backMenuRectangle);
        backMenuButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick() {
                playSoundManager.playClickSound();
                gsm.push(new GameMenu(gsm));
            }
        });
        addView(backMenuButton);
        addView(checkBoxButton);
        font = new BitmapFont();
        font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        font.setColor(Color.WHITE);
        font.getData().setScale(checkBoxRectangle.height *0.9f * font.getScaleY() / font.getLineHeight());
    }

    @Override
    protected void handleInput() {

    }

    @Override
    public void update(float dt) {
        super.update(dt);
    }

    @Override
    public void renderStatic(SpriteBatch sb) {
        GlyphLayout glyphLayout = new GlyphLayout();
        glyphLayout.setText(font, "Music" );
        sb.begin();
        sb.draw(backGroundTexture, backGroundRectangle.x,backGroundRectangle.y,backGroundRectangle.width,backGroundRectangle.height);
        font.draw(sb, glyphLayout,SpaceCleaner.WIDTH * 0.05f , checkBoxRectangle.y + checkBoxRectangle.height/1.5f);
        sb.end();
        super.renderStatic(sb);
    }
}
