package com.dethreeca.space_cleaner.state;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

//управление окнами
public class GameStateManager {

    private Stack<State> states;

    public GameStateManager(){
        states = new Stack<State>();
    }

    //add state
    public void push(State state){
        states.push(state);
    }

    //delete state
    public void pop(){
        //oсвобождаем ресурсы
        states.pop().dispose();
    }

    public void set(State state){
        states.pop().dispose();
        states.push(state);
    }

    public void update(float dt){
        //возвращает верхний элемент
        //не удаляя его при этом из стека
        states.peek().update(dt);
    }

    //отрисовка послежнего элемента стека
    public void render(SpriteBatch sb){
        states.peek().render(sb);
    }

    public void renderStatic(SpriteBatch staticBatch) {
        states.peek().renderStatic(staticBatch);

    }
}
