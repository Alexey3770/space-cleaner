package com.dethreeca.space_cleaner.utils;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.TimeUtils;
import com.dethreeca.space_cleaner.game_object.space_object.Artifact;
import com.dethreeca.space_cleaner.game_object.space_object.Garbage;
import com.dethreeca.space_cleaner.game_object.space_object.SpaceObject;
import com.dethreeca.space_cleaner.game_object.space_object.Sputnik;
import com.dethreeca.space_cleaner.game_object.space_object.Station;

public class SpaceObjectGenerator {
    private final float STONE_ANGLE_SPEED = 80F;
    private final float SPUTNIK_ANGLE_SPEED = 20F;

    private float stoneSize;
    private float sputnikSize;
    private float garbageSize;
    private float stationSize;

    private String[] garbageTextures = new String[]{
            TextureManager.GARBAGE1,
            TextureManager.GARBAGE2,
            TextureManager.GARBAGE3,
            TextureManager.GARBAGE4
    };
    private String[] sputnikTextures = new String[] {
            TextureManager.SPUTNIK1,
            TextureManager.SPUTNIK2,
            TextureManager.SPUTNIK3
    };
    private String[] artifactMinTextures = new String[] {
            TextureManager.ARTIFACT_MIN
    };
    private String[] artifactMediumTextures = new String[] {
            TextureManager.ARTIFACT_MEDIUM1,
            TextureManager.ARTIFACT_MEDIUM2,
            TextureManager.ARTIFACT_MEDIUM3
    };
    private String[] artifactLargeTextures = new String[] {
            TextureManager.ARTIFACT_LARGE1,
            TextureManager.ARTIFACT_LARGE2,
            TextureManager.ARTIFACT_LARGE3
    };

    private double lastGarbageGenerate;
    private double lastArtifactTime;
    private double lastSputnikTime;
    private float oldAngle = -1;

    private TextureManager textureManager;

    public SpaceObjectGenerator(float width, TextureManager textureManager) {
        this.textureManager = textureManager;
        stoneSize = width * 0.09f;
        sputnikSize = width * 0.14f;
        garbageSize = width * 0.05f;
        stationSize = width * 0.4f;
        lastGarbageGenerate = 0;
        lastArtifactTime = 0;
        lastSputnikTime = 0;
    }

    public SpaceObject generateSpaceObject(Camera camera, float earthAngle, float dt) {
        lastSputnikTime += dt;
        lastArtifactTime += dt;
        lastGarbageGenerate += dt;

        float randStoneSize = MathUtils.random(stoneSize / 2, stoneSize);
        if(earthAngle < 350 && earthAngle > 30) {
            if (lastSputnikTime > 3f) {
                lastSputnikTime = 0;
                return generateSputnik(camera, sputnikSize);
            }
            if (lastArtifactTime > 5f) {
                lastArtifactTime = 0;
                return generateArtifact(camera, garbageSize);
            }
            if (lastGarbageGenerate > 0.6f) {
                lastGarbageGenerate = 0;
                return generateGarbage(camera, randStoneSize);
            }
        }
        if(earthAngle < oldAngle) {
            SpaceObject station = generateStation(camera, stationSize);
            oldAngle = earthAngle;
            return station;
        }
        oldAngle = earthAngle;
        return null;
    }

    private SpaceObject generateStation(Camera camera, float size) {
        return new Station(-size / 2, camera.position.y + camera.viewportHeight
                , size, size, 0, textureManager.getTexture(TextureManager.STATION));
    }

    private SpaceObject generateSputnik(Camera camera, float size) {
        return new Sputnik(MathUtils.random(0, camera.viewportWidth - 64),
                camera.position.y + camera.viewportHeight
                , size, size, MathUtils.random(-SPUTNIK_ANGLE_SPEED, SPUTNIK_ANGLE_SPEED),
                textureManager.getTexture(sputnikTextures[MathUtils.random(0,2)]));
    }

    private SpaceObject generateGarbage(Camera camera, float size) {
        return new Garbage(MathUtils.random(0, camera.viewportWidth - 64), camera.position.y + camera.viewportHeight
                    , size, size, MathUtils.random(-STONE_ANGLE_SPEED, STONE_ANGLE_SPEED),
                textureManager.getTexture(garbageTextures[MathUtils.random(0,3)]));
    }

    private SpaceObject generateArtifact(Camera camera, float size) {
        int runNumb = MathUtils.random(0, 2);
        Artifact.TypeSpaceObject type = Artifact.TypeSpaceObject.valueOf(runNumb);
        Texture texture = null;
        switch (type) {
            case Min:
                texture = textureManager.getTexture(artifactMinTextures[0]);
                break;
            case Large:
                texture = textureManager.getTexture(artifactLargeTextures[MathUtils.random(0,2)]);
                break;
            case Medium:
                texture = textureManager.getTexture(artifactMediumTextures[MathUtils.random(0,2)]);
                size = (float) (size / 1.5);
                break;
        }
        return new Artifact(MathUtils.random(0, camera.viewportWidth - 64), camera.position.y + camera.viewportHeight
                , size, size, MathUtils.random(-STONE_ANGLE_SPEED, STONE_ANGLE_SPEED),
                texture, type);
    }
}
