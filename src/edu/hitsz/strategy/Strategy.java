package edu.hitsz.strategy;

import edu.hitsz.bullet.BaseBullet;

import java.util.List;

public interface Strategy {

    public List<BaseBullet> doShoot(int locationX, int locationY, int speed, int direction, int shootNum, int power);

}
