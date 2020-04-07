package com.zhangwenbo.tank.strategy;

import com.zhangwenbo.tank.bean.GoodBullet;
import com.zhangwenbo.tank.bean.Tank;
import com.zhangwenbo.tank.factory.impl.BadTankFactory;

public class DefaultFireStrategy implements FireStrategy {

    private static DefaultFireStrategy dfs = new DefaultFireStrategy();
    public void fire(Tank t) {
        int bX = t.getX() + t.getWidth() / 2 - GoodBullet.width / 2;
        int bY = t.getY() + t.getHeight() / 2 - GoodBullet.height / 2;
        BadTankFactory.getInstance().createBullet(bX,bY,t.getDir());
    }
    public static DefaultFireStrategy getInstance(){
        return dfs;
    }

    private DefaultFireStrategy() {
    }
}
