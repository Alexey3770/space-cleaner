package com.dethreeca.space_cleaner.utils;

import com.dethreeca.space_cleaner.game_object.space_object.Artifact;
import com.dethreeca.space_cleaner.game_object.space_object.SpaceObject;
import com.dethreeca.space_cleaner.game_object.user_object.Ship;
import com.dethreeca.space_cleaner.game_object.user_object.UserObject;

import java.util.List;

import static com.dethreeca.space_cleaner.game_object.space_object.Artifact.TypeSpaceObject.Medium;

public class CollisionService {

    private CollisionServiceListener listener;

    public void checkCollision(List<UserObject> userObjects, List<SpaceObject> spaceObjects) {
        for (UserObject uo: userObjects) {
            for (SpaceObject so: spaceObjects) {
                if (so.collides(uo.getBounds())) {
                    if (listener != null) {
                        collision(uo, so);
                    }
                }
            }
        }
    }

    public void setListener(CollisionServiceListener listener) {
        this.listener = listener;
    }

    private void collision(UserObject userObject, SpaceObject spaceObject) {
        if (userObject instanceof Ship) {
            collisionWithShip(spaceObject);
        }
    }

    private void collisionWithShip(SpaceObject spaceObject) {
        if (spaceObject instanceof Artifact) {
            if (((Artifact) spaceObject).getTypeSpaceObject() == Medium) {
                spaceObject.remove();
            }
        } else {
            if (listener != null) {
                listener.onGameOver();
            }
        }
    }

    public interface CollisionServiceListener {
        void onGameOver();
    }
}
