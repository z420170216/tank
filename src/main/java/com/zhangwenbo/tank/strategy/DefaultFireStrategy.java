package com.zhangwenbo.tank.strategy;

import com.zhangwenbo.tank.bean.Bullet;
import com.zhangwenbo.tank.bean.Tank;

public class DefaultFireStrategy implements FireStrategy {

    private static DefaultFireStrategy dfs = new DefaultFireStrategy();
    public void fire(Tank t) {
        int bX = t.getX() + t.WIDTH / 2 - Bullet.WIDTH / 2;
        int bY = t.getY() + t.HEIGHT / 2 - Bullet.HEIGHT / 2;
        new Bullet(bX, bY, t.getGroup(), t.getDir());
    }
    public static DefaultFireStrategy getInstance(){
        return dfs;
    }

    private DefaultFireStrategy() {
    }
}
