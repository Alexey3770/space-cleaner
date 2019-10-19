package com.dethreeca.space_cleaner;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dethreeca.space_cleaner.state.GameMenu;
import com.dethreeca.space_cleaner.state.GameStateManager;

public class SpaceCleaner extends ApplicationAdapter {

	SpriteBatch batch;
	SpriteBatch staticBatch;
	public static int WIDTH = 480;
	public static int HEIGTH = 800;

	private GameStateManager gameStateManager;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		staticBatch = new SpriteBatch();
		gameStateManager = new GameStateManager();
		Gdx.gl.glClearColor(1, 0, 0, 1);
        WIDTH = Gdx.graphics.getWidth();
        HEIGTH = Gdx.graphics.getHeight();
		gameStateManager.push(new GameMenu(gameStateManager));
	}

	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		//разница между послежним итекущим кадрами в секундах
		gameStateManager.update(Gdx.graphics.getDeltaTime());
		gameStateManager.render(batch);
		gameStateManager.renderStatic(staticBatch);
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
