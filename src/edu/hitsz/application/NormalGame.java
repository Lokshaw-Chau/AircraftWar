package edu.hitsz.application;

import java.awt.image.BufferedImage;

public class NormalGame extends Game {
    @Override
    public BufferedImage getBackgroundImage() {
        return ImageManager.BACKGROUND_IMAGE_NORMAL;
    }
}
