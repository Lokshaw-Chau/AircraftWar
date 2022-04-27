package edu.hitsz.application;

import java.awt.image.BufferedImage;

public class NormalGame extends Game {
    @Override
    public void setBossScoreThreshold() {
        bossScoreThreshold = 1000;
    }

    @Override
    protected void setMagnification() {
        if (magnification < 1.5) {
            magnification = 1.1 + difficultyCount * 0.05;
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
        enemyMaxNumber = 6;
    }

    @Override
    protected void setEnemyShootDuration() {
        enemyShootDuration = 400;
    }

    @Override
    protected void setHeroShootDuration() {
        if (heroShootDuration >= 400) {
            heroShootDuration = 800 - difficultyCount * 100;
        }
    }

    @Override
    public BufferedImage getBackgroundImage() {
        return ImageManager.BACKGROUND_IMAGE_NORMAL;
    }
}
