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

    //обновляет картинку
    public void update(float dt) {
        handleInput();
        for(View v: components) {
            v.update(dt);
        }
    }

    //рисовние экрана
    //SpriteBatch - выставляет текстуру и координаты для фигур
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(camera.combined);
    }

    public void renderStatic(SpriteBatch staticBatch) {
        staticBatch.begin();
        for(View v: components) {
            v.render(staticBatch);
        }
        staticBatch.end();
    }

    //для освобождения ресурсов
    public void dispose() {
        for (View v: components) {
            v.dispose();
        }
    }

    //пользовательский ввод
    protected abstract void handleInput();

    protected void addView(View view) {
        components.add(view);
    }

    protected void addAllView(List<View> view) {
        components.addAll(view);
    }

    protected void removeView(View view) {
        components.remove(view);
    }

    public enum GameState
    {
        PAUSE,
        RUN,
        RESUME,
        STOPPED
    }
}
