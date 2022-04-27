package edu.hitsz.application;

import java.awt.image.BufferedImage;

/**
 * @author ZLX
 */
public class EasyGame extends Game {
    @Override
    public BufferedImage getBackgroundImage() {
        return ImageManager.BACKGROUND_IMAGE_EASY;
    }

    @Override
    public boolean setCreatBossFlag() {
        return false;
    }

    @Override
    public void setBossScoreThreshold() {
    }

    @Override
    protected void setMagnification() {
        magnification = 1;
    }

    @Override
    protected void setEliteEnemyPobability() {

    }

    @Override
    protected void setCycleDuration() {

    }

    @Override
    protected void setEnemyMaxNumber() {
    }

    @Override
    protected void setEnemyShootDuration() {


    }

    @Override
    protected void setHeroShootDuration() {

    }
}
