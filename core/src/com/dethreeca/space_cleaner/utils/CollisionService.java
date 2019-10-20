package com.dethreeca.space_cleaner.utils;

import com.dethreeca.space_cleaner.game_object.space_object.Artifact;
import com.dethreeca.space_cleaner.game_object.space_object.SpaceObject;
import com.dethreeca.space_cleaner.game_object.space_object.Station;
import com.dethreeca.space_cleaner.game_object.user_object.Ship;
import com.dethreeca.space_cleaner.game_object.user_object.UserObject;
import com.dethreeca.space_cleaner.game_object.user_object.ammo.Ammo;
import com.dethreeca.space_cleaner.game_object.user_object.ammo.IceAttack;
import com.dethreeca.space_cleaner.game_object.user_object.ammo.LaserAttack;
import com.dethreeca.space_cleaner.model.User;

import java.util.List;

import static com.dethreeca.space_cleaner.game_object.space_object.Artifact.TypeSpaceObject.Medium;

public class CollisionService {

    private CollisionServiceListener listener;
    private PlaySoundManager soundManager;

    public CollisionService() {
        soundManager = new PlaySoundManager();
    }

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
            if (spaceObject instanceof Station) {
                collisionWithStation();
            } else {
                collisionWithShip(spaceObject);
            }
        } else if (userObject instanceof Ammo) {
            collisionWithAmmo((Ammo) userObject, spaceObject);
        }
    }

    private void collisionWithShip(SpaceObject spaceObject) {
        if (spaceObject instanceof Artifact) {
            if (((Artifact) spaceObject).getTypeSpaceObject() == Medium) {
                spaceObject.remove();
                soundManager.playCleanGarbage();
                User.getInstance().removePlaceInBucket();
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
                        User.getInstance().addBigGarbage();
                        soundManager.playCleanGarbage();
                    }
                    break;
                case Min:
                    if(ammo instanceof LaserAttack) {
                        spaceObject.remove();
                        User.getInstance().addLittleGarbage();
                        soundManager.playCleanGarbage();
                    }
                    break;
            }
        }
        ammo.startAnimation();
    }

    private void collisionWithStation() {
        if (listener != null) {
            listener.onPauseForStation();
        }
    }

    public interface CollisionServiceListener {
        void onGameOver();
        void onPauseForStation();
    }
}
