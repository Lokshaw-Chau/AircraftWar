package edu.hitsz.application;

import java.awt.image.BufferedImage;
/**
 * 困难模式的游戏
 *
 * @author ZLX
 * @date 2022/4/28
 */
public class HardGame extends AbstractGame {
    @Override
    public void setBossScoreThreshold() {
        bossScoreThreshold = 500;
    }

    @Override
    protected void setMagnification() {
        double magnificationLimit = 2;
        if (magnification < magnificationLimit) {
            magnification = 1.3 + difficultyCount * 0.05;
        }
    }

    @Override
    protected void setEliteEnemyProbability() {
        int eliteEnemuProbabilityLimit = 50;
        if (eliteEnemyProbability < eliteEnemuProbabilityLimit) {
            eliteEnemyProbability += 5;
        }
    }

    @Override
    protected void setCycleDuration() {
        int cycleDurationLimit = 300;
        if (cycleDuration > cycleDurationLimit) {
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
        int heroShootDurationLimit = 300;
        if (heroShootDuration >= heroShootDurationLimit) {
            heroShootDuration = 600 - difficultyCount * 100;
        }
    }

    @Override
    public BufferedImage getBackgroundImage() {
        return ImageManager.BACKGROUND_IMAGE_HARD;
    }
}
