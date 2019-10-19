package com.dethreeca.space_cleaner.utils;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.dethreeca.space_cleaner.game_object.Background;
import com.dethreeca.space_cleaner.game_object.Earth;
import com.dethreeca.space_cleaner.game_object.GameObject;
import com.dethreeca.space_cleaner.game_object.Ship;
import com.dethreeca.space_cleaner.game_object.space_object.SpaceObject;

public class GameObjectMaker {
    private float width, height;

    private OnObjectGenerated objectGeneratedListener;

    private TextureManager textureManager;
    private SpaceObjectGenerator spaceObjectGenerator;

    public GameObjectMaker(float width, float height) {
        this.height = height;
        this.width = width;
        this.textureManager = new TextureManager();
        this.spaceObjectGenerator = new SpaceObjectGenerator(width, textureManager);
    }

    public GameObject createBackground() {
        return new Background(textureManager.getTexture(TextureManager.BACKGROUND),
                new Vector2(0,0), width * 2, height * 2);
    }

    public GameObject createShip() {
        return new Ship(width / 2, height, width * 0.09f, width * 0.09f,
                textureManager.getTexture(TextureManager.SHIP), height * 0.4f);
    }

    public GameObject createEarth() {
        return new Earth(new Rectangle(0.9f * width,
                0,
                height * 2, height * 2),
                textureManager.getTexture(TextureManager.EARTH));
    }

    public void update(Camera camera, float earthAngle, float dt) {
        SpaceObject gameObject = spaceObjectGenerator.generateSpaceObject(camera, earthAngle, dt);
        if (objectGeneratedListener != null && gameObject != null) {
            objectGeneratedListener.onSpaceObjectGenerated(gameObject);
        }
    }

    public void dispose() {
        textureManager.dispose();
    }

    public void setObjectGeneratedListener(OnObjectGenerated objectGeneratedListener) {
        this.objectGeneratedListener = objectGeneratedListener;
    }

    public interface OnObjectGenerated {
        void onSpaceObjectGenerated(SpaceObject gameObject);
    }
}
