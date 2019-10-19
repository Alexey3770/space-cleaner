package com.dethreeca.space_cleaner.utils;

import com.badlogic.gdx.graphics.Texture;

import java.util.HashMap;
import java.util.Map;

public class TextureManager {
    public static final String BACKGROUND = "background";

    private Map<String, Texture> textures;

    public TextureManager() {
        textures = new HashMap<>();
        textures.put(BACKGROUND, new Texture("bg.png"));
    }

    public Texture getTexture(String textureName) {
        return textures.get(textureName);
    }

    public void dispose() {
        for(Texture texture: textures.values()) {
            texture.dispose();
        }
    }
}
