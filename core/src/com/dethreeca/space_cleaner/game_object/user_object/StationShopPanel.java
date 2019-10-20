package com.dethreeca.space_cleaner.game_object.user_object;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.dethreeca.space_cleaner.SpaceCleaner;
import com.dethreeca.space_cleaner.model.User;
import com.dethreeca.space_cleaner.utils.PlaySoundManager;
import com.dethreeca.space_cleaner.utils.Settings;
import com.dethreeca.space_cleaner.utils.TextureManager;
import com.dethreeca.space_cleaner.view_component.Button;
import com.dethreeca.space_cleaner.view_component.View;

import java.util.ArrayList;
import java.util.List;

public class StationShopPanel implements View {

    private final Float SIZE_BUTTON = 0.15F;
    Button saleGarbage;
    Button buyFuel;
    Button buyLaser;
    Button buyIce;
    Rectangle buyIceRectangle;
    Rectangle buyLaserRectangle;
    Rectangle buyFuelRectangle;
    Rectangle saleGarbageRectangle;
    PlaySoundManager playSoundManager;
    private TextureManager textureManager;

    private List<View> views;
    private OnResume onResumeListener;

    public StationShopPanel(TextureManager textureManager) {
        this.textureManager = textureManager;
        this.views = new ArrayList<>();

        //init play sounds
        playSoundManager = new PlaySoundManager();

        // init button bay ice
       initFuelBuy();
       initIceBuy();
       initLaserBuy();
       initSaleGarbage();
    }

    @Override
    public void update(float dt) {
        for (View v: views) {
            v.update(dt);
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        for (View v: views) {
            v.render(sb);
        }
    }

    @Override
    public void dispose() {
        for (View v: views) {
            v.dispose();
        }
    }

    public void setOnResumeListener(OnResume onResumeListener) {
        this.onResumeListener = onResumeListener;
    }

    private void initIceBuy() {
        float size = SpaceCleaner.WIDTH * SIZE_BUTTON;
        buyIceRectangle = new Rectangle(SpaceCleaner.WIDTH / 5f - size / 2,
                SpaceCleaner.HEIGTH / 2f - size / 2f,
                size,
                size);
//        buyIceRectangle.width = ;
//        buyIceRectangle.height = ;
//        buyIceRectangle.x = ;
//        buyIceRectangle.y = ;
        buyIce = new Button(textureManager.getTexture(TextureManager.BTN_BUY_ICE), buyIceRectangle);
        buyIce.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick() {
                playSoundManager.playMoneySound();
                User.getInstance().exchangeMoneyToIceGun();
            }
        });
        views.add(buyIce);
    }

    private void initLaserBuy() {
        float size = SpaceCleaner.WIDTH * SIZE_BUTTON;
        buyLaserRectangle = new Rectangle();
        buyLaserRectangle.width = size;
        buyLaserRectangle.height = size;
        buyLaserRectangle.x = SpaceCleaner.WIDTH * 2 / 5f - size / 2;
        buyLaserRectangle.y = SpaceCleaner.HEIGTH / 2f - size / 2f;
        buyLaser = new Button(textureManager.getTexture(TextureManager.BTN_BUY_LASER), buyLaserRectangle);
        buyLaser.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick() {
                playSoundManager.playMoneySound();
                User.getInstance().exchangeMoneyToLaserGun();
            }
        });
        views.add(buyLaser);
    }

    private void initFuelBuy() {
        float size = SpaceCleaner.WIDTH * SIZE_BUTTON;
        buyFuelRectangle = new Rectangle();
        buyFuelRectangle.width = size;
        buyFuelRectangle.height = size;
        buyFuelRectangle.x = SpaceCleaner.WIDTH  * 3 / 5f - size / 2;
        buyFuelRectangle.y = SpaceCleaner.HEIGTH / 2f - size / 2f;
        buyFuel = new Button(textureManager.getTexture(TextureManager.BTN_BUY_FUEL), buyFuelRectangle);
        buyFuel.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick() {
                playSoundManager.playMoneySound();
                User.getInstance().exchangeMoneyToFuel();
            }
        });
        views.add(buyFuel);
    }

    private void initSaleGarbage() {
        float size = SpaceCleaner.WIDTH * SIZE_BUTTON;
        saleGarbageRectangle = new Rectangle();
        saleGarbageRectangle.width = size;
        saleGarbageRectangle.height = size;
        saleGarbageRectangle.x = SpaceCleaner.WIDTH  * 4 / 5f - size / 2;
        saleGarbageRectangle.y = SpaceCleaner.HEIGTH / 2f - size / 2f;
        saleGarbage = new Button(textureManager.getTexture(TextureManager.BTN_SALE_GARBAGE), saleGarbageRectangle);
        saleGarbage.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick() {
                playSoundManager.playMoneySound();
                User.getInstance().exchangeGarbageToMoney();
            }
        });
        views.add(saleGarbage);
    }

    public interface OnResume {
        void onResume();
    }
}
