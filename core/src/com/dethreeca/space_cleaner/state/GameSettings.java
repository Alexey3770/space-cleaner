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
import com.dethreeca.space_cleaner.view_component.Button;
import com.dethreeca.space_cleaner.view_component.TextView;

public class  GameSettings extends State {

    Rectangle backGroundRectangle;
    Rectangle checkRectangle;
    Rectangle backMenuRectangle;
    TextureRegion backGroundTexture;
    TextureRegion checkTexture;
    Button backMenuButton;
 //   TextView textView;
    BitmapFont font;
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

        //init button checkbox
        checkRectangle = new Rectangle();
        checkRectangle.width = Float.valueOf(SpaceCleaner.WIDTH * 0.09f);
        checkRectangle.height = Float.valueOf(SpaceCleaner.HEIGTH * 0.09f);
        checkRectangle.x = SpaceCleaner.WIDTH - checkRectangle.width - SpaceCleaner.WIDTH * 0.05f;
        checkRectangle.y = SpaceCleaner.HEIGTH - checkRectangle.height - SpaceCleaner.HEIGTH * 0.05f ;
        checkTexture = new TextureRegion(new Texture("checkbox.png"));

        //init button Back
        playSoundManager = new PlaySoundManager();
        backMenuRectangle = new Rectangle();
        backMenuRectangle.width = SpaceCleaner.WIDTH * 0.12f;
        backMenuRectangle.height = SpaceCleaner.HEIGTH * 0.12f;
        backMenuRectangle.x = SpaceCleaner.WIDTH / 2 - backMenuRectangle.width / 2;
        backMenuRectangle.y = SpaceCleaner.HEIGTH / 10;
        backMenuButton = new Button(new Texture("backMenu.png"),backMenuRectangle);
        backMenuButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick() {
                playSoundManager.playClickSound();
                gsm.push(new GameMenu(gsm));
            }
        });
        addView(backMenuButton);

        font = new BitmapFont();
        font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        //font.getData().setScale(100 * font.getScaleY() / font.getLineHeight());
        font.setColor(Color.WHITE);
        // масштабируем размер шрифта в соответсвии с шириной экрана
        font.getData().setScale(checkRectangle.height* font.getScaleY() / font.getLineHeight());
    }

    @Override
    protected void handleInput() {
       /* if(Gdx.input.justTouched()){
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
        */
    }
    @Override
    public void renderStatic(SpriteBatch sb) {
        GlyphLayout glyphLayout = new GlyphLayout();
        glyphLayout.setText(font, "Music" );
        sb.begin();
        sb.draw(backGroundTexture, backGroundRectangle.x,backGroundRectangle.y,backGroundRectangle.width,backGroundRectangle.height);
        sb.draw(checkTexture,checkRectangle.x,checkRectangle.y,checkRectangle.width,checkRectangle.height);
        font.draw(sb, glyphLayout,SpaceCleaner.WIDTH * 0.05f , checkRectangle.y + checkRectangle.height/1.5f);
        sb.end();
        super.renderStatic(sb);
    }
}
