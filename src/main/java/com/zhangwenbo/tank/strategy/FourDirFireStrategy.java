package com.zhangwenbo.tank.strategy;

import com.zhangwenbo.tank.Enum.Dir;
import com.zhangwenbo.tank.bean.GoodBullet;
import com.zhangwenbo.tank.bean.GoodTank;
import com.zhangwenbo.tank.bean.Tank;
import com.zhangwenbo.tank.factory.impl.GoodTankFactory;

public class FourDirFireStrategy implements FireStrategy {

    private static FourDirFireStrategy dfs = new FourDirFireStrategy();

    public void fire(Tank t) {
        int bX = t.getX() + t.width / 2 - GoodBullet.width / 2;
        int bY = t.getY() + t.height / 2 - GoodBullet.height / 2;
        GoodTankFactory.getInstance().createBullet(bX, bY, Dir.UP);
        GoodTankFactory.getInstance().createBullet(bX, bY, Dir.DOWN);
        GoodTankFactory.getInstance().createBullet(bX, bY, Dir.RIGHT);
        GoodTankFactory.getInstance().createBullet(bX, bY, Dir.LEFT);
    }

    public static FourDirFireStrategy getInstance() {
        return dfs;
    }

    private FourDirFireStrategy() {
    }
}
