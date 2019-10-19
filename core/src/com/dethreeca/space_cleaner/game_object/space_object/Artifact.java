package com.dethreeca.space_cleaner.game_object.space_object;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

import java.util.HashMap;
import java.util.Map;

/**
 * То, что собираем.
 */
public class Artifact extends SpaceObject {

    private TypeSpaceObject mTypeSpaceObject;

    public Artifact(float x, float y, float width, float height, float angleSpeed, Texture image) {
        super(x, y, width, height, angleSpeed, image);
    }

    public Artifact(float x, float y, float width, float height, float angleSpeed, Texture image, TypeSpaceObject type) {
        this(x, y, width, height, angleSpeed, image);
        mTypeSpaceObject = type;
    }

    @Override
    public boolean collides(Rectangle player) {
        return super.collides(player);
    }

    public TypeSpaceObject getTypeSpaceObject() {
        return mTypeSpaceObject;
    }

    public enum TypeSpaceObject {
        Min (0),
        Medium (1),
        Large (2);

        private int mTypeSpaceObject;

        private static Map<Integer, TypeSpaceObject> map = new HashMap<Integer, TypeSpaceObject>();

        static {
            for (TypeSpaceObject legEnum : TypeSpaceObject.values()) {
                map.put(legEnum.mTypeSpaceObject, legEnum);
            }
        }

        TypeSpaceObject(final int leg) { mTypeSpaceObject = leg; }

        public static TypeSpaceObject valueOf(int legNo) {
            return map.get(legNo);
        }
    }
}
