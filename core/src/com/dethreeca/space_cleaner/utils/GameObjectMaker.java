package com.dethreeca.space_cleaner.utils;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.dethreeca.space_cleaner.game_object.Background;
import com.dethreeca.space_cleaner.game_object.Earth;
import com.dethreeca.space_cleaner.game_object.GameObject;
import com.dethreeca.space_cleaner.game_object.user_object.Ship;
import com.dethreeca.space_cleaner.game_object.space_object.SpaceObject;
import com.dethreeca.space_cleaner.game_object.user_object.UserObject;
import com.dethreeca.space_cleaner.game_object.user_object.ammo.IceAttack;
import com.dethreeca.space_cleaner.game_object.user_object.ammo.LaserAttack;

public class GameObjectMaker {
    private float width, height;

    private OnObjectGenerated objectGeneratedListener;

    private TextureManager textureManager;
    private SpaceObjectGenerator spaceObjectGenerator;

    private Ship ship;

    private Earth earth;

    public GameObjectMaker(float width, float height, TextureManager textureManager) {
        this.height = height;
        this.width = width;
        this.textureManager = textureManager;
        this.spaceObjectGenerator = new SpaceObjectGenerator(width, textureManager);
    }

    public GameObject createBackground() {
        return new Background(textureManager.getTexture(TextureManager.BACKGROUND),
                new Vector2(0,0), width * 2, height * 2);
    }

    public UserObject createShip() {
        ship =  new Ship(width / 2, height, width * 0.09f, width * 0.09f,
                textureManager.getTexture(TextureManager.SHIP), height * 0.4f);
        return ship;
    }

    public GameObject createEarth() {
        earth = new Earth(new Rectangle(0.9f * width,
                0,
                height * 2, height * 2),
                textureManager.getTexture(TextureManager.EARTH));
        return earth;
    }

    public UserObject createLaserAttack() {
        float size = width * 0.14f;
        return new LaserAttack(ship.getPosition().x, ship.getPosition().y, size, size,
                textureManager.getTexture(TextureManager.LASER_ATTACK), ship);
    }

    public UserObject createIceAttack() {
        float size = width * 0.09f;
        return new IceAttack(ship.getPosition().x, ship.getPosition().y, size, size,
                textureManager.getTexture(TextureManager.ICE_ATTACK));
    }

    public void update(Camera camera, float dt) {
        SpaceObject gameObject = spaceObjectGenerator.generateSpaceObject(camera, earth.getAngle(), dt);
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
