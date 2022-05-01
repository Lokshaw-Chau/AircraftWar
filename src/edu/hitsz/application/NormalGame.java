package edu.hitsz.application;

import java.awt.image.BufferedImage;
/**
 * 普通难度的游戏
 *
 * @author ZLX
 * @date 2022/4/28
 */
public class NormalGame extends AbstractGame {
    @Override
    public void setBossScoreThreshold() {
        bossScoreThreshold = 1000;
    }

    @Override
    protected void setMagnification() {
        double magnificationLimit = 1.5;
        if (magnification < magnificationLimit) {
            magnification = 1.1 + difficultyCount * 0.05;
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
        enemyMaxNumber = 6;
    }

    @Override
    protected void setEnemyShootDuration() {
        enemyShootDuration = 400;
    }

    @Override
    protected void setHeroShootDuration() {
        int heroShootDurationLimit = 400;
        if (heroShootDuration >= heroShootDurationLimit) {
            heroShootDuration = 800 - difficultyCount * 100;
        }
    }

    @Override
    public BufferedImage getBackgroundImage() {
        return ImageManager.BACKGROUND_IMAGE_NORMAL;
    }
}
