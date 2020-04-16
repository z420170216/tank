package com.zhangwenbo.tank.strategy;

import com.zhangwenbo.tank.Enum.Group;
import com.zhangwenbo.tank.bean.Bullet;
import com.zhangwenbo.tank.bean.Tank;
import com.zhangwenbo.tank.decorator.LineDecorator;
import com.zhangwenbo.tank.decorator.RectDecorator;
import com.zhangwenbo.tank.mgr.ResourceMgr;
import com.zhangwenbo.tank.model.GameModel;

public class DefaultFireStrategy implements FireStrategy {

    private static DefaultFireStrategy dfs = new DefaultFireStrategy();

    public void fire(Tank t) {
        int bX, bY;
        if (t.getGroup() == Group.GOOD) {
            bX = t.getX() + t.getWidth() / 2 - ResourceMgr.getInstance().getGoodBulletL().getWidth() / 2;
            bY = t.getY() + t.getHeight() / 2 - ResourceMgr.getInstance().getGoodBulletL().getHeight() / 2;
        } else {
            bX = t.getX() + t.getWidth() / 2 - ResourceMgr.getInstance().getBadBulletL().getWidth() / 2;
            bY = t.getY() + t.getHeight() / 2 - ResourceMgr.getInstance().getBadBulletL().getHeight() / 2;
        }
        GameModel.getInstance().add(new LineDecorator(new RectDecorator(new Bullet(bX, bY, t.getGroup(), t.getDir()))));
    }

    public static DefaultFireStrategy getInstance() {
        return dfs;
    }

    private DefaultFireStrategy() {
    }
}
