package edu.hitsz.application;

import java.awt.image.BufferedImage;

public class HardGame extends Game {
    @Override
    public void setBossScoreThreshold() {
        bossScoreThreshold = 500;
    }

    @Override
    protected void setMagnification() {
        if (magnification < 1.5) {
            magnification = 1 + difficultyCount * 0.02;
        }
    }

    @Override
    protected void setEliteEnemyPobability() {
        if (eliteEnemyProbability < 50) {
            eliteEnemyProbability += 10;
        }
    }

    @Override
    protected void setCycleDuration() {
        if (cycleDuration > 300) {
            cycleDuration -= 50;
        }
    }

    @Override
    public BufferedImage getBackgroundImage() {
        return ImageManager.BACKGROUND_IMAGE_HARD;
    }
}
