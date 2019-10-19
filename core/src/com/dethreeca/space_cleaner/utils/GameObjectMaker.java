package com.dethreeca.space_cleaner.utils;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.dethreeca.space_cleaner.game_object.Background;
import com.dethreeca.space_cleaner.game_object.GameObject;
import com.dethreeca.space_cleaner.game_object.Ship;

import java.util.HashMap;
import java.util.Map;

public class GameObjectMaker {
    private float width, height;

    private TextureManager textureManager;

    public GameObjectMaker(float width, float height) {
        this.height = height;
        this.width = width;
        this.textureManager = new TextureManager();
    }

    public GameObject createBackground() {
        return new Background(textureManager.getTexture(TextureManager.BACKGROUND),
                new Vector2(0,0), width * 2, height * 2);
    }

    public GameObject createShip() {
        return new Ship(width / 2, height, width * 0.09f, width * 0.09f,
                textureManager.getTexture(TextureManager.SHIP), height * 0.4f);
    }

    public void dispose() {
        textureManager.dispose();
    }
}
