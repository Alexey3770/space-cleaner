package com.dethreeca.space_cleaner.model;

import com.dethreeca.space_cleaner.utils.Settings;

import java.util.Set;

public class User {
    private static User instance;
    private int countBigGarbage;
    private int countLittleGarbage;
    private int countMiddleGarbage;
    private int countCountLaserGun;
    private int countCountIceGun;
    private int countCountBucketGun;
    private int fuelValue;
    private int level;
    private float targetPath;
    private boolean soundsOn;
    private int money;
    private float mCurrentPath;
    private final int priseLittleGarbage = 3;
    private final int priseBigGarbage = 2;
    private final int priseMiddleGarbage = 1;
    private final int priseFuelValue = 15;
    public static final int MAX_BUCKET_SIZE = 20;
    private final int priseGun = 10;

    public static User getInstance() {
        if (instance == null) {
            instance = new User();
        }
        return instance;
    }

    private User() {
        init();
    }

    public void countingLevel(float currentPath) {
        mCurrentPath = currentPath;
        if (mCurrentPath > targetPath) {
            targetPath *= 2;
            level++;
            Settings.getInstance().setLevel(level);
            Settings.getInstance().setTargetLength(targetPath);
            Settings.getInstance().save();
        }
    }

    public void save() {
        Settings.getInstance().setCountBigGarbage(countBigGarbage);
        Settings.getInstance().setCountLittleGarbage(countLittleGarbage);
        Settings.getInstance().setCountMiddleGarbage(countMiddleGarbage);
        Settings.getInstance().setFuelValue(fuelValue);
        Settings.getInstance().setLevel(level);
        Settings.getInstance().setTargetLength(targetPath);
        Settings.getInstance().setSoundsOn(soundsOn);
        Settings.getInstance().setMoney(money);
        Settings.getInstance().setCountIceGun(countCountIceGun);
        Settings.getInstance().setCountBucketGun(countCountBucketGun);
        Settings.getInstance().setCountLaserGun(countCountLaserGun);
    }

    public void init() {
        countBigGarbage = Settings.getInstance().getCountBigGarbage();
        countLittleGarbage = Settings.getInstance().getCountLittleGarbage();
        countMiddleGarbage = Settings.getInstance().getCountMiddleGarbage();
        fuelValue = Settings.getInstance().getFuelValue();
        level = Settings.getInstance().getLevel();
        targetPath = Settings.getInstance().getTargetLength();
        soundsOn = Settings.getInstance().isSoundsOn();
        money = Settings.getInstance().getMoney();
        countCountIceGun = Settings.getInstance().getCountIceGun();
        countCountBucketGun = Settings.getInstance().getCountBucketGun();
        countCountLaserGun = Settings.getInstance().getCountLaserGun();
    }

    public int getCurrentLevel() {
        return level;
    }

    public float getCurrentPath() {
        return mCurrentPath;
    }

    public int getCountMoney() {
        return money;
    }

    public int getCountFuel() {
        return fuelValue;
    }

    public int getCountLaserGun() {
        return countCountLaserGun;
    }

    public int getCountIceGun() {
        return countCountIceGun;
    }

    public int getCountBucketGun() {
        return countCountBucketGun;
    }

    public boolean exchangeGarbageToMoney() {
        if (countBigGarbage + countMiddleGarbage + countLittleGarbage == 0) {
            return false;
        }
        int mon = countBigGarbage * priseBigGarbage + countLittleGarbage * priseLittleGarbage + countMiddleGarbage * priseMiddleGarbage;
        money += mon;
        countMiddleGarbage = 0;
        countLittleGarbage = 0;
        countBigGarbage = 0;
        countCountBucketGun = 0;
        Settings.getInstance().setCountBigGarbage(countBigGarbage);
        Settings.getInstance().setCountLittleGarbage(countLittleGarbage);
        Settings.getInstance().setCountMiddleGarbage(countMiddleGarbage);
        Settings.getInstance().setMoney(money);
        money += mon;
        return true;
    }

    public boolean exchangeMoneyToFuel() {
        if (money >= priseFuelValue) {
            fuelValue++;
            money -= priseFuelValue;
            Settings.getInstance().setMoney(money);
            Settings.getInstance().setFuelValue(fuelValue);
            return true;
        }
        return false;
    }

    public boolean exchangeMoneyToIceGun() {
        if (money >= priseGun) {
            countCountIceGun++;
            money -= priseGun;
            Settings.getInstance().setMoney(money);
            Settings.getInstance().setCountIceGun(countCountIceGun);
            return true;
        }
        return false;
    }

    public boolean exchangeMoneyToLaserGun() {
        if (money >= priseGun) {
            countCountLaserGun++;
            money -= priseGun;
            Settings.getInstance().setMoney(money);
            Settings.getInstance().setCountLaserGun(countCountLaserGun);
            return true;
        }
        return false;
    }

    public void removeIceGun() {
        if (countCountIceGun > 0) {
            countCountIceGun--;
            Settings.getInstance().setCountIceGun(countCountIceGun);
        }
    }

    public void removeLaserGun() {
        if (countCountLaserGun > 0) {
            countCountLaserGun--;
            Settings.getInstance().setCountLaserGun(countCountLaserGun);
        }
    }

    public void removePlaceInBucket() {
        if (countCountBucketGun < MAX_BUCKET_SIZE) {
            countCountBucketGun++;
            countMiddleGarbage++;
            Settings.getInstance().setCountBucketGun(countCountBucketGun);
        }
    }

    public void addLittleGarbage() {
        countLittleGarbage++;
    }

    public void addBigGarbage() {
        countBigGarbage++;
    }

    public void removeFuel() {
        if (fuelValue > 0) {
            fuelValue--;
            Settings.getInstance().setFuelValue(fuelValue);
        }
    }
}
