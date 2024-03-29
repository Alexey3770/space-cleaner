package com.dethreeca.space_cleaner.utils;

import com.badlogic.gdx.graphics.Texture;

import java.util.HashMap;
import java.util.Map;

public class TextureManager {
    private static TextureManager instance;

    public static final String BACKGROUND = "background";
    public static final String SHIP = "ship";

    public static final String GARBAGE1 = "garbage1";
    public static final String GARBAGE2 = "garbage2";
    public static final String GARBAGE3 = "garbage3";
    public static final String GARBAGE4 = "garbage4";

    public static final String SPUTNIK1 = "sputnik1";
    public static final String SPUTNIK2 = "sputnik2";
    public static final String SPUTNIK3 = "sputnik3";

    public static final String ARTIFACT_MIN = "artifact_min";

    public static final String ARTIFACT_MEDIUM1 = "artifact_medium1";
    public static final String ARTIFACT_MEDIUM2 = "artifact_medium2";
    public static final String ARTIFACT_MEDIUM3 = "artifact_medium3";

    public static final String ARTIFACT_LARGE1 = "artifact_large1";
    public static final String ARTIFACT_LARGE2 = "artifact_large2";
    public static final String ARTIFACT_LARGE3 = "artifact_large3";
    public static final String STATION = "station";

    public static final String EARTH = "earth";

    public static final String ICE_ATTACK = "ice_attack";
    public static final String LASER_ATTACK = "laser_attack";

    public static final String BTN_SHOOT_LASER_TEXTURE = "btn_shoot_laser_texture";
    public static final String BTN_SHOOT_ICE_TEXTURE = "btn_shoot_ice_texture";

    public static final String TXV_SHOOT_LASER_TEXTURE = "txv_shoot_laser_texture";
    public static final String TXV_SHOOT_ICE_TEXTURE = "txv_shoot_ice_texture";
    public static final String TXV_SHOOT_BUCKET_TEXTURE = "txv_shoot_bucket_texture";
    public static final String TXV_SHOOT_FUEL_TEXTURE = "txv_shoot_fuel_texture";

    public static final String BTN_BUY_ICE = "btn_buy_ice";
    public static final String BTN_BUY_LASER = "btn_buy_laser";
    public static final String BTN_BUY_FUEL = "btn_buy_fuel";
    public static final String BTN_SALE_GARBAGE = "btn_sale_garbage";

    public static final String COIN_STAT = "coin_stat";

    public static final String BTN_BACK = "btn_back";

    private Map<String, Texture> textures;

    private TextureManager() {
        textures = new HashMap<>();
        textures.put(BACKGROUND, new Texture("bg.png"));
        textures.put(SHIP, new Texture("ship.png"));

        textures.put(GARBAGE1, new Texture("stone.png"));
        textures.put(GARBAGE2, new Texture("stone1.png"));
        textures.put(GARBAGE3, new Texture("stone2.png"));
        textures.put(GARBAGE4, new Texture("stone3.png"));

        textures.put(SPUTNIK1, new Texture("sputnik.png"));
        textures.put(SPUTNIK2, new Texture("sputnik1.png"));
        textures.put(SPUTNIK3, new Texture("sputnik2.png"));

        textures.put(ARTIFACT_MIN, new Texture("garbage5.png"));

        textures.put(ARTIFACT_MEDIUM1, new Texture("garbage1.png"));
        textures.put(ARTIFACT_MEDIUM2, new Texture("garbage2.png"));
        textures.put(ARTIFACT_MEDIUM3, new Texture("garbage4.png"));

        textures.put(ARTIFACT_LARGE1, new Texture("garbage3.png"));
        textures.put(ARTIFACT_LARGE2, new Texture("garbage7.png"));
        textures.put(ARTIFACT_LARGE3, new Texture("garbage8.png"));

        textures.put(STATION, new Texture("station.png"));

        textures.put(EARTH, new Texture("earth.png"));

        textures.put(BTN_SHOOT_LASER_TEXTURE, new Texture("lazer_button.png"));
        textures.put(BTN_SHOOT_ICE_TEXTURE, new Texture("fire_button.png"));
        textures.put(TXV_SHOOT_ICE_TEXTURE, new Texture("water.png"));
        textures.put(TXV_SHOOT_LASER_TEXTURE, new Texture("lazer.png"));
        textures.put(TXV_SHOOT_BUCKET_TEXTURE, new Texture("trash.png"));
        textures.put(TXV_SHOOT_FUEL_TEXTURE, new Texture("toplivo.png"));
        textures.put(ICE_ATTACK, new Texture("push_water.png"));
        textures.put(LASER_ATTACK, new Texture("laser_attack.png"));

        textures.put(BTN_BUY_ICE, new Texture("buy_ice.png"));
        textures.put(BTN_BUY_LASER, new Texture("buy_laser.png"));
        textures.put(BTN_BUY_FUEL, new Texture("buy_fuel.png"));
        textures.put(BTN_SALE_GARBAGE, new Texture("buy_coin.png"));

        textures.put(BTN_BACK, new Texture("back.png"));

        textures.put(COIN_STAT, new Texture("coin_stat.png"));
    }

    public Texture getTexture(String textureName) {
        return textures.get(textureName);
    }

    public void dispose() {
        for(Texture texture: textures.values()) {
            texture.dispose();
        }
    }

    public static TextureManager getInstance() {
        return new TextureManager();
    }
}
