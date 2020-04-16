package com.zhangwenbo.tank.strategy;

import com.zhangwenbo.tank.Enum.Dir;
import com.zhangwenbo.tank.Enum.Group;
import com.zhangwenbo.tank.bean.Bullet;
import com.zhangwenbo.tank.bean.Tank;
import com.zhangwenbo.tank.mgr.PropertyMgr;
import com.zhangwenbo.tank.mgr.ResourceMgr;

public class FourDirFireStrategy implements FireStrategy {

    private static FourDirFireStrategy dfs = new FourDirFireStrategy();

    public void fire(Tank t) {
        int bX, bY;
        if (t.getGroup() == Group.GOOD) {
            bX = t.getX() + t.getWidth() / 2 - ResourceMgr.getInstance().getGoodBulletL().getWidth() / 2;
            bY = t.getY() + t.getHeight() / 2 - ResourceMgr.getInstance().getGoodBulletL().getHeight() / 2;
        } else {
            bX = t.getX() + t.getWidth() / 2 - ResourceMgr.getInstance().getBadBulletL().getWidth() / 2;
            bY = t.getY() + t.getHeight() / 2 - ResourceMgr.getInstance().getBadBulletL().getHeight() / 2;
        }
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
