package com.zhangwenbo.tank.strategy;

import com.zhangwenbo.tank.Enum.Dir;
import com.zhangwenbo.tank.bean.Bullet;
import com.zhangwenbo.tank.bean.Tank;

public class FourDirFireStrategy implements FireStrategy {

    private static FourDirFireStrategy dfs = new FourDirFireStrategy();

    public void fire(Tank t) {
        int bX = t.getX() + t.WIDTH / 2 - Bullet.WIDTH / 2;
        int bY = t.getY() + t.HEIGHT / 2 - Bullet.HEIGHT / 2;
        new Bullet(bX, bY, t.getGroup(), Dir.UP);
        new Bullet(bX, bY, t.getGroup(), Dir.DOWN);
        new Bullet(bX, bY, t.getGroup(), Dir.RIGHT);
        new Bullet(bX, bY, t.getGroup(), Dir.LEFT);
    }

    public static FourDirFireStrategy getInstance() {
        return dfs;
    }

    private FourDirFireStrategy() {
    }
}
