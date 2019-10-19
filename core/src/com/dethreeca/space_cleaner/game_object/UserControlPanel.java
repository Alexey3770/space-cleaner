package com.dethreeca.space_cleaner.game_object;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.dethreeca.space_cleaner.model.User;
import com.dethreeca.space_cleaner.utils.TextureManager;
import com.dethreeca.space_cleaner.view_component.Button;
import com.dethreeca.space_cleaner.view_component.TextView;
import com.dethreeca.space_cleaner.view_component.View;

import java.util.ArrayList;
import java.util.List;

public class UserControlPanel {
    //Constants
    private float bottomMargin;
    private float movementDelta;
    private float textLeftMargin;
    private float textHeight;
    private float buttonSize;
    private float buttonMargin;
    private float iceAttackSize;
    private float laserAttackSize;
    private float width, height;

    private List<View> views;

    private TextView scoreTextView, iceAmmoTextView, laserAmmoTextView,
            bucketTextView, fuelTextView;

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

    public List<View> getViews() {
        return views;
    }

    public void setListener(UserControlPanelListener listener) {
        this.listener = listener;
    }

    public void update() {
        laserAmmoTextView.setText(String.valueOf(User.getInstance().getCountLaserGun()));
        bucketTextView.setText(String.valueOf(User.getInstance().getCountBucketGun()));
        iceAmmoTextView.setText(String.valueOf(User.getInstance().getCountIceGun()));
        fuelTextView.setText(String.valueOf(User.getInstance().getCountFuel()));
    }

    private void initConstants() {
        textHeight = height * 0.05f;
        buttonSize = width * 0.1f;
        buttonMargin = height * 0.05f;
    }
    
    private void initViewComponents() {
        initScoreTextView();
        initButtons();
        initPanel();
    }

    private void initScoreTextView() {
        scoreTextView = new TextView(new Vector2(textLeftMargin, height - textHeight));
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
        float x = width - textLeftMargin - textHeight * 2;
        float y = height - textLeftMargin;
        iceAmmoTextView = new TextView(new Vector2(x, y));
        iceAmmoTextView.setTextHeight(textHeight);
        iceAmmoTextView.setIcon(textureManager.getTexture(TextureManager.TXV_SHOOT_ICE_TEXTURE));

        x -= textLeftMargin + textHeight * 2;
        laserAmmoTextView = new TextView(new Vector2(x, y));
        laserAmmoTextView.setTextHeight(textHeight);
        laserAmmoTextView.setIcon(textureManager.getTexture(TextureManager.TXV_SHOOT_LASER_TEXTURE));

        x -= textLeftMargin + textHeight * 2;
        bucketTextView = new TextView(new Vector2(x, y));
        bucketTextView.setTextHeight(textHeight);
        bucketTextView.setIcon(textureManager.getTexture(TextureManager.TXV_SHOOT_BUCKET_TEXTURE));

        x -= textLeftMargin + textHeight * 2;
        fuelTextView = new TextView(new Vector2(x, y));
        fuelTextView.setTextHeight(textHeight);
        fuelTextView.setIcon(textureManager.getTexture(TextureManager.TXV_SHOOT_FUEL_TEXTURE));
        views.add(iceAmmoTextView);
        views.add(laserAmmoTextView);
        views.add(bucketTextView);
        views.add(fuelTextView);
    }

    public interface UserControlPanelListener {
        void onAddIceAttack();
        void onAddLaser();
    }
}
