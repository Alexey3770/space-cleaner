package com.dethreeca.space_cleaner.game_object;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.TimeUtils;

public class Ship implements GameObject {
    //позиция корабля
    private Vector3 position;

    //скорость корабля
    private Vector3 speed;
    private Texture sheep;
    private Rectangle boundsSheep;
    private Texture texture;
    private TextureRegion textureRegion;
    private float oldAccelerometerValue;
    private float movementDelta;
    private float cameraBottomMargin;

    public Ship(float x, float y, float width, float height, Texture texture,
                float cameraBottomMargin) {
        this.texture = texture;
        this.cameraBottomMargin = cameraBottomMargin;
        textureRegion = new TextureRegion(texture);
        boundsSheep = new Rectangle(0, 0, width, height);
        position = new Vector3(x - boundsSheep.width/2, 0,0);
        speed = new Vector3(0, 0, 0);
        speed.y = height * 1.7f;
        movementDelta = height * 2.3f;
    }

    public void move(float dx) {
        speed.x = dx;
    }

    public Vector3 getPosition() {
        return position;
    }

    public TextureRegion getSheep() {
        return textureRegion;
    }

    public Rectangle getBoundsSheep() {
        return boundsSheep;
    }

    @Override
    public void update(float dt, Camera camera) {
//        animation.update(dt);
        if (position.y > 0)
            speed.add(0, 0, 0);

        //доб новое положение птички
        position.add(speed.x * dt, speed.y * dt, 0);

        boundsSheep.setPosition(position.x, position.y);
        camera.position.y = position.y + cameraBottomMargin;
        handleInput(camera);
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.draw(textureRegion, position.x, position.y,
                boundsSheep.width, boundsSheep.height);
    }

    public void handleInput(Camera camera) {
        float ALPHA = 0.3F;
        float BETA = 0.7F;
        float value = ALPHA * Gdx.input.getAccelerometerY() + oldAccelerometerValue * BETA;
        oldAccelerometerValue = value;
        move(value * movementDelta);
        if (position.x < 0) {
            position.x = 0;
        } else if (boundsSheep.getX() +
                boundsSheep.width >=
                camera.viewportWidth) {
            position.x = camera.viewportWidth - boundsSheep.width;
        }
    }
}
