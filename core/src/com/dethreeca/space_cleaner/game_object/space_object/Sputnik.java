package com.dethreeca.space_cleaner.game_object.space_object;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Sputnik extends SpaceObject {

    public Sputnik(float x, float y, float width, float height, float angleSpeed, Texture image) {
        super(x, y, width, height, angleSpeed, image);
    }

    @Override
    public boolean collides(Rectangle player) {
        return super.collides(player);
    }

}
