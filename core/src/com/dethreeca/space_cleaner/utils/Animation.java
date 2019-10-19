package com.dethreeca.space_cleaner.utils;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.ArrayList;

public class Animation {

    //кадры анимации
    private ArrayList<TextureRegion> frames;

    //длительность отображения кадра
    private float maxFrameTime;

    //длительность отображения текущего кадра
    private float currentFrameTime;

    //количество кадров анимации
    private int frameCount;

    //отдельный кадр анимации
    private int frame;

    private boolean isLooped = false;

    private boolean animationFinished = false;

    public Animation(TextureRegion region, int frameCount, float cycleTime){
        frames = new ArrayList<TextureRegion>();
        int frameWidth = region.getRegionWidth() / frameCount;
        for (int i = 0; i < frameCount; i++){
            frames.add(new TextureRegion(region, i* frameWidth, 0, frameWidth, region.getRegionHeight()));
        }
        this.frameCount = frameCount;
        this.maxFrameTime = cycleTime / frameCount;
        this.frame = 0;
    }

    public void update(float df){
        currentFrameTime += df;
        if (currentFrameTime > maxFrameTime && !animationFinished){
            frame++;
            currentFrameTime = 0;System.out.println("update");
        } else if (frame >= frameCount && isLooped){
            frame = 0;System.out.println("loop");
        }

        if (frame >= frameCount) {
            animationFinished = true;
        }
    }

    public TextureRegion getFrame(){
        return frames.get(frame);
    }

    public boolean isAnimationFinished() {
        return animationFinished;
    }

    public void setCurrentFrame(int frame){
        this.frame = frame;
    }

    public void setLooped() {
        this.isLooped = isLooped;
    }
}
