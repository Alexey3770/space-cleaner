package com.dethreeca.space_cleaner.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class Settings {
    private static String MY_PREFERENCES = "My Preferences";
    private static String SOUND_PREFERENCES_KEY = "SOUND_PREFERENCES_KEY";
    private static String COUNT_LITTLE_GARBAGE_PREFERENCES_KEY = "COUNT_LITTLE_GARBAGE_PREFERENCES_KEY";
    private static String COUNT_MIDDLE_GARBAGE_PREFERENCES_KEY = "COUNT_MIDDLE_GARBAGE_PREFERENCES_KEY";
    private static String COUNT_BIG_GARBAGE_PREFERENCES_KEY = "COUNT_BIG_GARBAGE_PREFERENCES_KEY";
    private static String PATH_LENGTH_PREFERENCES_KEY = "PATH_LENGTH_PREFERENCES_KEY";
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

    public int getPathLength() {
        return prefs.getInteger(PATH_LENGTH_PREFERENCES_KEY, 0);
    }

    public void setPathLength(int pathLength) {
        prefs.putInteger(PATH_LENGTH_PREFERENCES_KEY, pathLength);
    }
}
