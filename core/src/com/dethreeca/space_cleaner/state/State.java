package com.dethreeca.space_cleaner.state;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dethreeca.space_cleaner.view_component.View;

import java.util.ArrayList;
import java.util.List;

//управление состоянием объектов
public abstract class State {

    protected OrthographicCamera camera;
    public GameStateManager gsm;

    private List<View> components;

    public State(GameStateManager gsm){
        this.components = new ArrayList<>();
        this.gsm = gsm;
        camera = new OrthographicCamera();
    }

    //пользовательский ввод
    protected abstract void handleInput();

    //обновляет картинку
    public void update(float dt) {
        for(View v: components) {
            v.update(dt);
        }
        handleInput();
        handleAccelerometer();
    }

    public abstract void handleAccelerometer();

    //рисовние экрана
    //SpriteBatch - выставляет текстуру и координаты для фигур
    public abstract void render(SpriteBatch sb);

    public void renderStatic(SpriteBatch staticBatch) {
        for(View v: components) {
            v.render(staticBatch);
        }
    }

    //для освобождения ресурсов
    public void dispose() {
        for (View v: components) {
            v.dispose();
        }
    }

    public enum GameState
    {
        PAUSE,
        RUN,
        RESUME,
        STOPPED
    }
}
