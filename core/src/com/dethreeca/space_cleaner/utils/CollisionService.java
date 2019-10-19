package com.dethreeca.space_cleaner.utils;

import com.dethreeca.space_cleaner.game_object.space_object.Artifact;
import com.dethreeca.space_cleaner.game_object.space_object.SpaceObject;
import com.dethreeca.space_cleaner.game_object.user_object.Ship;
import com.dethreeca.space_cleaner.game_object.user_object.UserObject;
import com.dethreeca.space_cleaner.game_object.user_object.ammo.Ammo;
import com.dethreeca.space_cleaner.game_object.user_object.ammo.IceAttack;
import com.dethreeca.space_cleaner.game_object.user_object.ammo.LaserAttack;

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
        } else if(userObject instanceof Ammo) {
            collisionWithAmmo((Ammo) userObject, spaceObject);
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

    private void collisionWithAmmo(Ammo ammo, SpaceObject spaceObject) {
        if (spaceObject instanceof Artifact) {
            switch (((Artifact) spaceObject).getTypeSpaceObject()) {
                case Large:
                    if(ammo instanceof IceAttack) {
                        spaceObject.remove();
                        break;
                    }
                case Min:
                    if(ammo instanceof LaserAttack) {
                        spaceObject.remove();
                        break;
                    }
            }
        }
        ammo.startAnimation();
    }

    public interface CollisionServiceListener {
        void onGameOver();
    }
}
