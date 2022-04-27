package edu.hitsz.application;

import java.awt.image.BufferedImage;

public class HardGame extends Game {
    @Override
    public void setBossScoreThreshold() {
        bossScoreThreshold = 500;
    }

    @Override
    protected void setMagnification() {
        if (magnification < 2) {
            magnification = 1.3 + difficultyCount * 0.05;
        }
    }

    @Override
    protected void setEliteEnemyPobability() {
        if (eliteEnemyProbability < 50) {
            eliteEnemyProbability += 5;
        }
    }

    @Override
    protected void setCycleDuration() {
        if (cycleDuration > 300) {
            cycleDuration -= 50;
        }
    }

    @Override
    protected void setEnemyMaxNumber() {
        enemyMaxNumber = 7;
    }

    @Override
    protected void setEnemyShootDuration() {
        enemyShootDuration = 400;
    }

    @Override
    protected void setHeroShootDuration() {
        if (heroShootDuration >= 300) {
            heroShootDuration = 600 - difficultyCount * 100;
        }

    }

    @Override
    public BufferedImage getBackgroundImage() {
        return ImageManager.BACKGROUND_IMAGE_HARD;
    }
}
