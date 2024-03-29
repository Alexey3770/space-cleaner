package com.dethreeca.space_cleaner.state;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dethreeca.space_cleaner.SpaceCleaner;
import com.dethreeca.space_cleaner.game_object.GameObject;
import com.dethreeca.space_cleaner.game_object.UserControlPanel;
import com.dethreeca.space_cleaner.game_object.space_object.SpaceObject;
import com.dethreeca.space_cleaner.game_object.user_object.StationShopPanel;
import com.dethreeca.space_cleaner.game_object.user_object.UserObject;
import com.dethreeca.space_cleaner.model.User;
import com.dethreeca.space_cleaner.utils.CollisionService;
import com.dethreeca.space_cleaner.utils.GameObjectMaker;
import com.dethreeca.space_cleaner.utils.PlaySoundManager;
import com.dethreeca.space_cleaner.utils.Settings;
import com.dethreeca.space_cleaner.utils.TextureManager;

import java.util.ArrayList;
import java.util.List;

public class GamePlay extends State implements GameObjectMaker.OnObjectGenerated,
        CollisionService.CollisionServiceListener, UserControlPanel.UserControlPanelListener,
        StationShopPanel.OnResume {
    private State.GameState stateGame = State.GameState.RUN;

    //game objects
    private List<GameObject> gameObjects;
    //user's controllable objects
    private List<UserObject> userObjects;
    //auto generated space object
    private List<SpaceObject> spaceObjects;

    private GameObjectMaker gameObjectMaker;
    private TextureManager textureManager;
    private CollisionService collisionService;
    private UserControlPanel userControlPanel;
    private PlaySoundManager soundManager;
    private StationShopPanel stationShopPanel;

    public GamePlay(GameStateManager gsm) {
        super(gsm);
        camera.setToOrtho(false, SpaceCleaner.WIDTH / 2,
                SpaceCleaner.HEIGTH / 2);
        stateGame = GameState.RUN;
        textureManager = TextureManager.getInstance();
        collisionService = new CollisionService();
        collisionService.setListener(this);
        userControlPanel = new UserControlPanel(SpaceCleaner.WIDTH, SpaceCleaner.HEIGTH, textureManager);
        addView(userControlPanel);
        userControlPanel.setListener(this);
        soundManager = new PlaySoundManager();
        initGameObjects();
    }

    @Override
    protected void handleInput() {
    }

    @Override
    public void update(float dt) {
        super.update(dt);
        switch (stateGame) {
            case RUN:
                updateRunState(dt);
                break;
            case PAUSE:
                break;
            case RESUME:
                break;
            case STOPPED:
                break;
        }
        cleanSpaceObject();
        camera.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        super.render(sb);
        sb.begin();
        renderObjects(sb, gameObjects);
        renderObjects(sb, spaceObjects);
        renderObjects(sb, userObjects);
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

    @Override
    public void onGameOver() {
        gsm.set(new GameOver(gsm));
    }

    @Override
    public void onPauseForStation() {
        stateGame = GameState.PAUSE;
        stationShopPanel = new StationShopPanel(textureManager);
        stationShopPanel.setOnResumeListener(this);
        addView(stationShopPanel);
        Settings.getInstance().save();
    }

    @Override
    public void onAddIceAttack() {
        userObjects.add(gameObjectMaker.createIceAttack());
        soundManager.playIceAttackSound();
    }

    @Override
    public void onAddLaser() {
        userObjects.add(gameObjectMaker.createLaserAttack());
        soundManager.playLaserAttackSound();
    }

    @Override
    public void onResume() {
        removeView(stationShopPanel);
        gameObjectMaker.resetShipPosition();
        stationShopPanel = null;
        stateGame = GameState.RUN;
    }

    private void renderObjects(SpriteBatch sb, List<? extends GameObject> objects) {
        for (GameObject go : objects) {
            go.render(sb);
        }
    }

    private void updateRunState(float dt) {
        updateObjects(dt, gameObjects);
        updateObjects(dt, spaceObjects);
        updateObjects(dt, userObjects);
        collisionService.checkCollision(userObjects, spaceObjects);
        gameObjectMaker.update(camera, dt);
        checkFuel();
    }

    private void checkFuel() {
        if (User.getInstance().getCountFuel() <= 0) {
            gsm.set(new GameOver(gsm));
        }
    }

    private void updateObjects(float dt, List<? extends GameObject> objects) {
        for (GameObject go : objects) {
            go.update(dt, camera);
        }
    }

    private void initGameObjects() {
        this.gameObjectMaker = new GameObjectMaker(camera.viewportWidth, camera.viewportHeight,
                textureManager);
        this.gameObjectMaker.setObjectGeneratedListener(this);

        this.gameObjects = new ArrayList<>();
        this.spaceObjects = new ArrayList<>();
        this.userObjects = new ArrayList<>();

        this.gameObjects.add(gameObjectMaker.createBackground());
        this.gameObjects.add(gameObjectMaker.createEarth());

        this.userObjects.add(gameObjectMaker.createShip());
    }

    private void cleanSpaceObject() {
        for (int i = 0; i < spaceObjects.size(); i++) {
            if (spaceObjects.get(i).canRemove()) {
                spaceObjects.remove(i);
                i--;
            }
        }
        for (int i = 0; i < userObjects.size(); i++) {
            if (userObjects.get(i).canRemove()) {
                userObjects.remove(i);
                i--;
            }
        }
    }
}
