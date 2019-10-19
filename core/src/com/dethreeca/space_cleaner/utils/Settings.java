package com.dethreeca.space_cleaner.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

import static com.dethreeca.space_cleaner.model.User.MAX_BUCKET_SIZE;

public class Settings {
    private static String MY_PREFERENCES = "My Preferences";
    private static String SOUND_PREFERENCES_KEY = "SOUND_PREFERENCES_KEY";
    private static String COUNT_LITTLE_GARBAGE_PREFERENCES_KEY = "COUNT_LITTLE_GARBAGE_PREFERENCES_KEY";
    private static String COUNT_MIDDLE_GARBAGE_PREFERENCES_KEY = "COUNT_MIDDLE_GARBAGE_PREFERENCES_KEY";
    private static String COUNT_BIG_GARBAGE_PREFERENCES_KEY = "COUNT_BIG_GARBAGE_PREFERENCES_KEY";
    private static String TARGET_LENGTH_PREFERENCES_KEY = "TARGET_LENGTH_PREFERENCES_KEY";
    private static String FUEL_VALUE_PREFERENCES_KEY = "FUEL_VALUE_PREFERENCES_KEY";
    private static String LEVEL_PREFERENCES_KEY = "LEVEL_PREFERENCES_KEY";
    private static String MONEY_PREFERENCES_KEY = "MONEY_PREFERENCES_KEY";
    private static String ICE_GUN_PREFERENCES_KEY = "ICE_GUN_PREFERENCES_KEY";
    private static String LASER_GUN_PREFERENCES_KEY = "LASER_GUN_PREFERENCES_KEY";
    private static String BUCKET_GUN_PREFERENCES_KEY = "BUCKET_GUN_PREFERENCES_KEY";
    private static Settings instance;
    private Preferences prefs;

    public static Settings getInstance() {
        if (instance == null) {
            instance = new Settings();
        }
        return instance;
    }

    private Settings() {
        prefs = Gdx.app.getPreferences(MY_PREFERENCES);
    }

    public boolean isSoundsOn() {
        return prefs.getBoolean(SOUND_PREFERENCES_KEY, true);
    }

    public void setSoundsOn(boolean soundsOn) {
        prefs.putBoolean(SOUND_PREFERENCES_KEY, soundsOn);
    }

    public int getCountLittleGarbage() {
        return prefs.getInteger(COUNT_LITTLE_GARBAGE_PREFERENCES_KEY, 0);
    }

    public void setCountLittleGarbage(int countLittleGarbage) {
        prefs.putInteger(COUNT_LITTLE_GARBAGE_PREFERENCES_KEY, countLittleGarbage);
    }

    public int getCountMiddleGarbage() {
        return prefs.getInteger(COUNT_MIDDLE_GARBAGE_PREFERENCES_KEY, 0);
    }

    public void setCountMiddleGarbage(int countMiddleGarbage) {
        prefs.putInteger(COUNT_MIDDLE_GARBAGE_PREFERENCES_KEY, countMiddleGarbage);
    }

    public int getCountBigGarbage() {
        return prefs.getInteger(COUNT_BIG_GARBAGE_PREFERENCES_KEY, 0);
    }

    public void setCountBigGarbage(int countBigGarbage) {
        prefs.putInteger(COUNT_BIG_GARBAGE_PREFERENCES_KEY, countBigGarbage);
    }

    public float getTargetLength() {
        return prefs.getFloat(TARGET_LENGTH_PREFERENCES_KEY, 5000);
    }

    public void setTargetLength(float pathLength) {
        prefs.putFloat(TARGET_LENGTH_PREFERENCES_KEY, pathLength);
    }

    public int getFuelValue() {
        return prefs.getInteger(FUEL_VALUE_PREFERENCES_KEY, 20);
    }

    public void setFuelValue(int flueValue) {
        prefs.putInteger(FUEL_VALUE_PREFERENCES_KEY, flueValue);
    }

    public int getLevel(){
        return prefs.getInteger(LEVEL_PREFERENCES_KEY,0);
    }

    public void setLevel(int level){
        prefs.putInteger(LEVEL_PREFERENCES_KEY, level);
    }

    public int getMoney(){
        return prefs.getInteger(MONEY_PREFERENCES_KEY,0);
    }

    public void setMoney(int money){
        prefs.putInteger(MONEY_PREFERENCES_KEY, money);
    }

    public int getCountIceGun(){
        return prefs.getInteger(ICE_GUN_PREFERENCES_KEY,20);
    }

    public void setCountIceGun(int obj){
        prefs.putInteger(ICE_GUN_PREFERENCES_KEY, obj);
    }

    public int getCountLaserGun(){
        return prefs.getInteger(LASER_GUN_PREFERENCES_KEY,20);
    }

    public void setCountLaserGun(int obj){
        prefs.putInteger(LASER_GUN_PREFERENCES_KEY, obj);
    }

    public int getCountBucketGun(){
        return prefs.getInteger(BUCKET_GUN_PREFERENCES_KEY, 0);
    }

    public void setCountBucketGun(int obj){
        prefs.putInteger(BUCKET_GUN_PREFERENCES_KEY, obj);
    }
}
