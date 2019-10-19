package com.dethreeca.space_cleaner.state;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dethreeca.space_cleaner.SpaceCleaner;
import com.dethreeca.space_cleaner.game_object.GameObject;
import com.dethreeca.space_cleaner.game_object.space_object.SpaceObject;
import com.dethreeca.space_cleaner.utils.GameObjectMaker;

import java.util.ArrayList;
import java.util.List;

public class GamePlay extends State implements GameObjectMaker.OnObjectGenerated {
    private State.GameState stateGame = State.GameState.RUN;

    //user's controllable objects
    private List<GameObject> gameObjects;
    //auto generated space object
    private List<SpaceObject> spaceObjects;
    private GameObjectMaker gameObjectMaker;

    public GamePlay(GameStateManager gsm) {
        super(gsm);
        camera.setToOrtho(false, SpaceCleaner.WIDTH / 2,
                SpaceCleaner.HEIGTH / 2);
        stateGame = GameState.RUN;
        initGameObjects();
    }

    @Override
    protected void handleInput() {
    }

    @Override
    public void update(float dt) {
        super.update(dt);
        switch (stateGame) {
            case RUN: updateRunState(dt); break;
            case PAUSE: break;
            case RESUME: break;
            case STOPPED: break;
        }
        cleanSpaceObject();
        camera.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        super.render(sb);
        sb.begin();
        for(GameObject go: gameObjects) {
            go.render(sb);
        }
        for(GameObject go: spaceObjects) {
            go.render(sb);
        }
        sb.end();
    }

    @Override
    public void renderStatic(SpriteBatch staticBatch) {
        super.renderStatic(staticBatch);
    }

    @Override
    public void dispose() {
        super.dispose();
        gameObjectMaker.dispose();
    }

    @Override
    public void onSpaceObjectGenerated(SpaceObject spaceObject) {
        this.spaceObjects.add(spaceObject);
    }

    private void updateRunState(float dt) {
        for(GameObject go: gameObjects) {
            go.update(dt, camera);
        }
        for (SpaceObject so: spaceObjects) {
            so.update(dt, camera);
        }
        gameObjectMaker.update(camera, 40, dt);
    }

    private void initGameObjects() {
        this.gameObjectMaker = new GameObjectMaker(camera.viewportWidth, camera.viewportHeight);
        this.gameObjectMaker.setObjectGeneratedListener(this);
        this.gameObjects = new ArrayList<>();
        this.spaceObjects = new ArrayList<>();
        this.gameObjects.add(gameObjectMaker.createBackground());
        this.gameObjects.add(gameObjectMaker.createShip());
        this.gameObjects.add(gameObjectMaker.createEarth());
    }

    private void cleanSpaceObject() {
        for (int i = 0; i < spaceObjects.size(); i++) {
            if(spaceObjects.get(i).canRemove()) {
                spaceObjects.remove(i);
                i--;
            }
        }
    }
}
