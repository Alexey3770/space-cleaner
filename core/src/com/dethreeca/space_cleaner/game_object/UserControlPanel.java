package com.dethreeca.space_cleaner.game_object;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.dethreeca.space_cleaner.model.User;
import com.dethreeca.space_cleaner.utils.TextureManager;
import com.dethreeca.space_cleaner.view_component.Button;
import com.dethreeca.space_cleaner.view_component.TextView;
import com.dethreeca.space_cleaner.view_component.View;

import java.util.ArrayList;
import java.util.List;

public class UserControlPanel implements View{
    //Constants
    private float textMargin;
    private float textHeight;
    private float buttonSize;
    private float buttonMargin;
    private float width, height;

    private List<View> views;

    private TextView scoreTextView, iceAmmoTextView, laserAmmoTextView,
            bucketTextView, fuelTextView, moneyTextView;

    private UserControlPanelListener listener;

    private TextureManager textureManager;

    public UserControlPanel(float width, float height, TextureManager textureManager) {
        this.width = width;
        this.height = height;
        this.textureManager = textureManager;
        this.views = new ArrayList<>();
        initConstants();
        initViewComponents();
    }

    public void setListener(UserControlPanelListener listener) {
        this.listener = listener;
    }

    @Override
    public void update(float dt) {
        laserAmmoTextView.setText(String.valueOf(User.getInstance().getCountLaserGun()));
        bucketTextView.setText(String.valueOf(User.getInstance().getCountBucketGun()));
        iceAmmoTextView.setText(String.valueOf(User.getInstance().getCountIceGun()));
        fuelTextView.setText(String.valueOf(User.getInstance().getCountFuel()));
        moneyTextView.setText(String.valueOf(User.getInstance().getCountMoney()));
        scoreTextView.setText(String.format("Current path: %.2f\nCurrent level: %d",
                User.getInstance().getCurrentPath(),
                User.getInstance().getCurrentLevel()));
        for (View v: views) {
            v.update(dt);
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        for (View view: views) {
            view.render(sb);
        }
    }

    @Override
    public void dispose() {
        for (View view: views) {
            view.dispose();
        }
    }

    private void initConstants() {
        textHeight = height * 0.05f;
        buttonSize = width * 0.1f;
        buttonMargin = height * 0.05f;
        textMargin = width * 0.02f;
    }
    
    private void initViewComponents() {
        initScoreTextView();
        initButtons();
        initPanel();
    }

    private void initScoreTextView() {
        scoreTextView = new TextView(new Vector2(textMargin, height - textMargin));
        scoreTextView.setTextHeight(textHeight);
        views.add(scoreTextView);
    }

    private void initButtons() {
        Button shootLaserBtn = new Button(textureManager
                .getTexture(TextureManager.BTN_SHOOT_LASER_TEXTURE),
                new Rectangle(buttonMargin, buttonMargin,
                buttonSize, buttonSize));
        shootLaserBtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick() {
                if(User.getInstance().getCountLaserGun() <= 0) {
                    return;
                }
                User.getInstance().removeLaserGun();
                if (listener != null) {
                    listener.onAddLaser();
                }
            }
        });
        Button shootIceBtn = new Button(textureManager
                .getTexture(TextureManager.BTN_SHOOT_ICE_TEXTURE), new Rectangle(
                width - buttonMargin - buttonSize,
                buttonMargin, buttonSize, buttonSize));
        shootIceBtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick() {
                if(User.getInstance().getCountIceGun() <= 0) {
                    return;
                }
                User.getInstance().removeIceGun();
                if (listener != null) {
                    listener.onAddIceAttack();
                }
            }
        });
        views.add(shootIceBtn);
        views.add(shootLaserBtn);
    }


    private void initPanel() {
        float x = width - textMargin - textHeight * 2;
        float y = height - textMargin;
        iceAmmoTextView = new TextView(new Vector2(x, y));
        iceAmmoTextView.setTextHeight(textHeight);
        iceAmmoTextView.setIcon(textureManager.getTexture(TextureManager.TXV_SHOOT_ICE_TEXTURE));

        x -= textHeight * 2;
        laserAmmoTextView = new TextView(new Vector2(x, y));
        laserAmmoTextView.setTextHeight(textHeight);
        laserAmmoTextView.setIcon(textureManager.getTexture(TextureManager.TXV_SHOOT_LASER_TEXTURE));

        x -= textHeight * 2;
        bucketTextView = new TextView(new Vector2(x, y));
        bucketTextView.setTextHeight(textHeight);
        bucketTextView.setIcon(textureManager.getTexture(TextureManager.TXV_SHOOT_BUCKET_TEXTURE));

        x -= textHeight * 2;
        fuelTextView = new TextView(new Vector2(x, y));
        fuelTextView.setTextHeight(textHeight);
        fuelTextView.setIcon(textureManager.getTexture(TextureManager.TXV_SHOOT_FUEL_TEXTURE));

        x -= textHeight * 2;
        moneyTextView = new TextView(new Vector2(x, y));
        moneyTextView.setTextHeight(textHeight);
        moneyTextView.setIcon(textureManager.getTexture(TextureManager.COIN_STAT));
        views.add(iceAmmoTextView);
        views.add(laserAmmoTextView);
        views.add(bucketTextView);
        views.add(fuelTextView);
        views.add(moneyTextView);
    }

    public interface UserControlPanelListener {
        void onAddIceAttack();
        void onAddLaser();
    }
}
