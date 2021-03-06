package edu.hitsz.bullet;

/**
 * @author hitsz
 */
public class EnemyBullet extends BaseBullet {

    public static final int WIDTH = 6;
    public static final int HEIGHT = 18;

    public EnemyBullet(int locationX, int locationY, int speedX, int speedY, int power) {
        super(locationX, locationY, speedX, speedY, power);
    }

    @Override
    public void explode() {
        vanish();
    }
}
