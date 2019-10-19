package com.dethreeca.space_cleaner.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class PlaySoundManager {

    private Sound clickSound;
    private Sound iceAttackSound;
    private Sound laserAttackSound;
    private Sound collisionSound;
    private Sound moneySound;
    private Sound cleanGarbage;
    private Music bdMusic;

    public PlaySoundManager() {

        // short sounds
        clickSound = Gdx.audio.newSound(Gdx.files.internal("clickSound.mp3"));
        iceAttackSound = Gdx.audio.newSound(Gdx.files.internal("iceAttackSound.mp3"));
        laserAttackSound = Gdx.audio.newSound(Gdx.files.internal("laserAttackSound.mp3"));
        collisionSound = Gdx.audio.newSound(Gdx.files.internal("collisionSound.mp3"));
        moneySound = Gdx.audio.newSound(Gdx.files.internal("moneySound.mp3"));
        cleanGarbage = Gdx.audio.newSound(Gdx.files.internal("cleanGabage.mp3"));

        // long sounds
//        bdMusic = Gdx.audio.newMusic(Gdx.files.internal("rain.mp3"));
//        bdMusicMusic.setLooping(true);
    }

    public void playMoneySound() {
        if(isEnable()) {
            moneySound.play();
        }
    }

    public void playCleanGarbage() {
        if(isEnable()) {
            cleanGarbage.play();
        }
    }

    public void playBgSound() {
//        if(isEnable()) {
//            bdMusic.play();
//        } else {
//            bdMusic.stop();
//        }
    }

    public void playClickSound(){
        if(isEnable()) {
            clickSound.play();
        }
    }

    public void playIceAttackSound(){
        if(isEnable()) {
            iceAttackSound.play();
        }
    }

    public void playLaserAttackSound(){
        if(isEnable()) {
            laserAttackSound.play();
        }
    }

    public void playCollisionSound(){
        if(isEnable()) {

            collisionSound.play();
        }
    }

    public boolean isEnable() {
        return Settings.getInstance().isSoundsOn();
    }

    public void dispose() {
        clickSound.dispose();
        iceAttackSound.dispose();
        laserAttackSound.dispose();
        collisionSound.dispose();
        cleanGarbage.dispose();
        moneySound.dispose();
        bdMusic.dispose();
    }
}
