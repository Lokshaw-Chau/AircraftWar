package edu.hitsz.application;

import java.awt.image.BufferedImage;

/**
 * 简单模式的游戏
 *
 * @author ZLX
 * @date 2022/4/28
 */
public class EasyGame extends AbstractGame {
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
    protected void setEliteEnemyProbability() {
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
