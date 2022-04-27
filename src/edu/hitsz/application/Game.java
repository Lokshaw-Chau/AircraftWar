package edu.hitsz.application;

import edu.hitsz.aircraft.*;
import edu.hitsz.basic.AbstractFlyingObject;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.supply.AbstractSuppply;
import edu.hitsz.supply.BombSupply;
import edu.hitsz.supply.BombSupplyPublisher;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/**
 * 游戏主面板，游戏启动
 *
 * @author hitsz
 */
public abstract class Game extends JPanel {

    private int backGroundTop = 0;
    /**
     * Scheduled 线程池，用于任务调度
     */
    private final ScheduledExecutorService executorService;
    /**
     * 时间间隔(ms)，控制刷新频率
     */
    private final int timeInterval = 40;
    private final List<BaseBullet> heroBullets;
    private final List<BaseBullet> enemyBullets;
    private final List<AbstractSuppply> suppplies;

    private final HeroAircraft heroAircraft;
    protected final List<AbstractAircraft> enemyAircrafts;
    /**
     * 几个音乐线程
     */
    private MusicThread bgmMusicThread;
    private MusicThread bgmBossMusicThread;
    protected EnemyFactory enemyFactory;

    private boolean gameOverFlag = false;
    private boolean bossNotExist = true;
    protected int time = 0;
    /**
     * 周期（ms)
     * 指示子弹的发射、敌机的产生频率，难度增加的频率
     */
    protected int cycleDuration = 600;
    private int cycleTime = 0;
    /**
     * 游戏难度相关的变量
     */
    private int difficultyIncreaseDuration = 10000;
    protected int difficultyCount = 0;
    protected double magnification = 1;
    protected int bossScoreThreshold = 1000;
    protected int eliteEnemyProbability = 10;
    protected int enemyMaxNumber = 5;
    protected int enemyShootDuration = 600;
    protected int heroShootDuration = 600;

    public Game() {
        heroAircraft = HeroAircraft.creatHeroAircraft();

        enemyAircrafts = new LinkedList<>();
        heroBullets = new LinkedList<>();
        enemyBullets = new LinkedList<>();
        suppplies = new LinkedList<>();
        //设置基本变量
        setBossScoreThreshold();
        setEnemyMaxNumber();
        setEnemyShootDuration();
        setHeroShootDuration();
        //启动背景音乐线程
        bgmMusicThread = new MusicThread("src/videos/bgm.wav");
        bgmMusicThread.setLoop(true);
        bgmMusicThread.start();
        //Scheduled 线程池，用于定时任务调度
        ThreadFactory gameThread = r -> {
            Thread t = new Thread(r);
            t.setName("game thread");
            return t;
        };
        executorService = new ScheduledThreadPoolExecutor(1, gameThread);

        //启动英雄机鼠标监听
        new HeroController(this, heroAircraft);

    }

    /**
     * 游戏启动入口，执行游戏逻辑
     */
    public void action() {

        // 定时任务：绘制、对象产生、碰撞判定、击毁及结束判定
        Runnable task = () -> {

            time += timeInterval;

            // 周期性执行（控制频率）
            if (timeCountAndNewCycleJudge()) {
                //System.out.println(time);
                // 新敌机产生
                creatEnemyAircraft();
            }
            if (time % difficultyIncreaseDuration == 0) {
                difficultyCount++;
                //提高敌机数值的倍率
                setMagnification();
                //提高精英机产生的概率
                setEliteEnemyPobability();
                //提高敌机产生的频率
                setCycleDuration();
                if (!(this instanceof EasyGame)) {
                    System.out.println("难度提升！敌机数值倍率：" + magnification + "，精英机产生的概率：" + eliteEnemyProbability + "%，敌机产生的周期：" + cycleDuration + "ms");
                }
            }
            // 飞机射出子弹
            enemyShootAction();
            heroShootAction();

            // 子弹移动
            bulletsMoveAction();

            // 飞机移动
            aircraftsMoveAction();

            //补给移动
            supppliesMoveAction();

            // 撞击检测
            crashCheckAction();

            // 后处理
            postProcessAction();

            //每个时刻重绘界面
            repaint();

            // 游戏结束检查
            if (heroAircraft.getHp() <= 0) {
                // 游戏结束
                executorService.shutdown();
                gameOverFlag = true;
                System.out.println("Game Over!");
                //打断所有bgm线程并启动gameover线程
                bgmMusicThread.setRunning(false);
                if (!bossNotExist) {
                    bgmBossMusicThread.setRunning(false);
                }
                new MusicThread("src/videos/game_over.wav").start();
                //唤醒排行榜线程
                synchronized (Main.RANKLOCK) {
                    Main.RANKLOCK.notify();
                }
            }
        };

        /**
         * 以固定延迟时间进行执行
         * 本次任务执行完成后，需要延迟设定的延迟时间，才会执行新的任务
         */
        executorService.scheduleWithFixedDelay(task, timeInterval, timeInterval, TimeUnit.MILLISECONDS);

    }

    //***********************
    //      Action 各部分
    //***********************

    private boolean timeCountAndNewCycleJudge() {
        cycleTime += timeInterval;
        if (cycleTime >= cycleDuration && cycleTime - timeInterval < cycleTime) {
            // 跨越到新的周期
            cycleTime %= cycleDuration;
            return true;
        } else {
            return false;
        }
    }

    private void enemyShootAction() {
        if (time % enemyShootDuration == 0) {
            //敌机射击
            for (AbstractAircraft enemy : enemyAircrafts) {
                enemyBullets.addAll(enemy.shoot());
            }
        }
    }

    private void heroShootAction() {
        if (time % heroShootDuration == 0) {
            // 英雄射击
            heroBullets.addAll(heroAircraft.shoot());
            new MusicThread("src/videos/bullet.wav").start();
        }
    }

    private void bulletsMoveAction() {
        for (BaseBullet bullet : heroBullets) {
            bullet.forward();
        }
        for (BaseBullet bullet : enemyBullets) {
            bullet.forward();
        }
    }

    private void aircraftsMoveAction() {
        for (AbstractAircraft enemyAircraft : enemyAircrafts) {
            enemyAircraft.forward();
        }
    }

    private void supppliesMoveAction() {
        for (AbstractSuppply suppply : suppplies) {
            suppply.forward();
        }
    }

    /**
     * 创建敌机：
     */
    private final void creatEnemyAircraft() {
        if (enemyAircrafts.size() < enemyMaxNumber) {
            Random random = new Random();
            int number = random.nextInt(100);
            if (number < eliteEnemyProbability) {
                enemyFactory = new EliteEnemyFactory();
            } else {
                enemyFactory = new MobEnemyFactory();
            }
            if (setCreatBossFlag()) {
                if (heroAircraft.getScore() >= bossScoreThreshold && bossNotExist) {
                    enemyFactory = new BossEnemyFactory();
                    bossNotExist = false;
                    //启动boss音乐线程
                    bgmBossMusicThread = new MusicThread("src/videos/bgm_boss.wav");
                    bgmBossMusicThread.start();
                    //打断bgm线程
                    bgmMusicThread.setRunning(false);
                }
            }
            enemyAircrafts.add(enemyFactory.creatEnemy(magnification));
        }
    }

    /**
     * 子类重载该方法可以取消产生boss
     */
    public boolean setCreatBossFlag() {
        return true;
    }

    /**
     * 子类通过重载该方法设置产生boss机的分数线
     */
    public abstract void setBossScoreThreshold();

    /**
     * 子类通过重载该方法决定敌机的强度随时间变化的倍率
     */
    protected abstract void setMagnification();

    /**
     * 子类通过重载该方法决定精英机产生的概率
     */
    protected abstract void setEliteEnemyPobability();

    /**
     * 子类通过重载该方法决定敌机产生的频率
     */
    protected abstract void setCycleDuration();

    /**
     * 子类通过重载该方法决定最大敌机个数
     */
    protected abstract void setEnemyMaxNumber();

    /**
     * 子类通过重载该方法决定敌机射击的周期
     */
    protected abstract void setEnemyShootDuration();

    /**
     * 子类通过重载该方法决定英雄机射击的周期
     */
    protected abstract void setHeroShootDuration();

    /**
     * 碰撞检测：
     * 1. 敌机攻击英雄
     * 2. 英雄攻击/撞击敌机
     * 3. 英雄获得补给
     */
    private void crashCheckAction() {
        // TODO 敌机子弹攻击英雄
        for (BaseBullet bullet : enemyBullets) {
            if (bullet.notValid()) {
                continue;
            }
            if (heroAircraft.crash(bullet)) {
                //英雄机碰撞到敌机子弹，损失生命值
                heroAircraft.decreaseHp(bullet.getPower());
                bullet.vanish();
            }
        }
        // 英雄子弹攻击敌机
        for (BaseBullet bullet : heroBullets) {
            if (bullet.notValid()) {
                continue;
            }
            for (AbstractAircraft enemyAircraft : enemyAircrafts) {
                if (enemyAircraft.notValid()) {
                    // 已被其他子弹击毁的敌机，不再检测
                    // 避免多个子弹重复击毁同一敌机的判定
                    continue;
                }
                if (enemyAircraft.crash(bullet)) {
                    // 敌机撞击到英雄机子弹
                    new MusicThread("src/videos/bullet_hit.wav").start();
                    // 敌机损失一定生命值
                    enemyAircraft.decreaseHp(bullet.getPower());
                    bullet.vanish();
                    if (enemyAircraft.notValid()) {
                        //获得分数，产生道具补给
                        if (enemyAircraft instanceof BossEnemy) {
                            //销毁boss机
                            bossScoreThreshold += bossScoreThreshold;
                            bossNotExist = true;
                            heroAircraft.addScore(20);
                            //关闭bgm_boss线程并打开bgm线程
                            bgmBossMusicThread.setRunning(false);
                            bgmMusicThread.setRunning(true);
                        }
                        if (enemyAircraft instanceof EliteEnemy || enemyAircraft instanceof BossEnemy) {
                            suppplies.addAll(enemyAircraft.makeSupply());
                            heroAircraft.addScore(20);
                        }
                        heroAircraft.addScore(10);
                    }
                }
                // 英雄机 与 敌机 相撞，均损毁
                if (enemyAircraft.crash(heroAircraft) || heroAircraft.crash(enemyAircraft)) {
                    enemyAircraft.vanish();
                    heroAircraft.decreaseHp(Integer.MAX_VALUE);
                }
            }
        }
        // Todo: 我方获得道具，道具生效
        for (AbstractSuppply suppply : suppplies) {
            if (heroAircraft.crash(suppply)) {
                //启动getsupply音效
                new MusicThread("src/videos/get_supply.wav").start();
                if (suppply instanceof BombSupply) {
                    for (AbstractFlyingObject item : enemyAircrafts) {
                        BombSupplyPublisher.getInstance().addFlyingObject(item);
                    }
                    for (AbstractFlyingObject item : enemyBullets) {
                        BombSupplyPublisher.getInstance().addFlyingObject(item);
                    }
                    //播放炸弹音效
                    new MusicThread("src/videos/bomb_explosion.wav").start();
                }
                suppply.takeEffect(heroAircraft);
                suppply.vanish();
            }
        }
    }

    /**
     * 后处理：
     * 1. 删除无效的子弹
     * 2. 删除无效的敌机
     * 3. 删除无效的道具
     * 4. 检查英雄机生存
     * <p>
     * 无效的原因可能是撞击或者飞出边界
     */
    private void postProcessAction() {
        enemyBullets.removeIf(AbstractFlyingObject::notValid);
        heroBullets.removeIf(AbstractFlyingObject::notValid);
        enemyAircrafts.removeIf(AbstractFlyingObject::notValid);
        suppplies.removeIf(AbstractFlyingObject::notValid);
    }


    //***********************
    //      Paint 各部分
    //***********************

    /**
     * 子类重载该方法以更改背景图片
     */
    public abstract BufferedImage getBackgroundImage();

    /**
     * 重写paint方法
     * 通过重复调用paint方法，实现游戏动画
     *
     * @param g
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        // 绘制背景,图片滚动
        g.drawImage(getBackgroundImage(), 0, this.backGroundTop - Main.WINDOW_HEIGHT, null);
        g.drawImage(getBackgroundImage(), 0, this.backGroundTop, null);
        this.backGroundTop += 1;
        if (this.backGroundTop == Main.WINDOW_HEIGHT) {
            this.backGroundTop = 0;
        }

        // 先绘制子弹和道具，后绘制飞机
        // 这样子弹显示在飞机的下层
        paintImageWithPositionRevised(g, enemyBullets);
        paintImageWithPositionRevised(g, heroBullets);
        paintImageWithPositionRevised(g, suppplies);
        paintImageWithPositionRevised(g, enemyAircrafts);

        g.drawImage(ImageManager.HERO_IMAGE, heroAircraft.getLocationX() - ImageManager.HERO_IMAGE.getWidth() / 2,
                heroAircraft.getLocationY() - ImageManager.HERO_IMAGE.getHeight() / 2, null);

        //绘制得分和生命值
        paintScoreAndLife(g);

    }

    private void paintImageWithPositionRevised(Graphics g, List<? extends AbstractFlyingObject> objects) {
        if (objects.size() == 0) {
            return;
        }

        for (AbstractFlyingObject object : objects) {
            BufferedImage image = object.getImage();
            assert image != null : objects.getClass().getName() + " has no image! ";
            g.drawImage(image, object.getLocationX() - image.getWidth() / 2,
                    object.getLocationY() - image.getHeight() / 2, null);
        }
    }

    private void paintScoreAndLife(Graphics g) {
        int x = 10;
        int y = 25;
        g.setColor(new Color(16711680));
        g.setFont(new Font("SansSerif", Font.BOLD, 22));
        g.drawString("SCORE:" + heroAircraft.getScore(), x, y);
        y = y + 20;
        g.drawString("LIFE:" + this.heroAircraft.getHp(), x, y);
    }
}
