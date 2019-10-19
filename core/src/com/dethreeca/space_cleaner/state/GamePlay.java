package com.dethreeca.space_cleaner.state;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dethreeca.space_cleaner.SpaceCleaner;
import com.dethreeca.space_cleaner.game_object.GameObject;
import com.dethreeca.space_cleaner.utils.GameObjectMaker;

import java.util.ArrayList;
import java.util.List;

public class GamePlay extends State {
    private State.GameState stateGame = State.GameState.RUN;

    private List<GameObject> gameObjects;
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
        camera.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        super.render(sb);
        sb.begin();
        for(GameObject go: gameObjects) {
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

    private void updateRunState(float dt) {
        for(GameObject go: gameObjects) {
            go.update(dt, camera);
        }
    }

    private void initGameObjects() {
        this.gameObjectMaker = new GameObjectMaker(camera.viewportWidth, camera.viewportHeight);
        this.gameObjects = new ArrayList<>();
        this.gameObjects.add(gameObjectMaker.createBackground());
        this.gameObjects.add(gameObjectMaker.createShip());
    }
}
