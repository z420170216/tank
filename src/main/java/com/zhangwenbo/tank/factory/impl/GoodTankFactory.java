package com.zhangwenbo.tank.factory.impl;

import com.zhangwenbo.tank.Enum.Dir;
import com.zhangwenbo.tank.bean.*;
import com.zhangwenbo.tank.factory.TankFactory;
import com.zhangwenbo.tank.strategy.FireStrategy;

public class GoodTankFactory implements TankFactory {

    private static GoodTankFactory instance = new GoodTankFactory();

    private GoodTankFactory() {

    }

    public Tank createTank(int x, int y, Dir dir, FireStrategy fs) {
        return new GoodTank(x,y,dir,fs);
    }

    public Bullet createBullet(int x, int y,Dir dir) {
        return new GoodBullet(x,y,dir);
    }

    public Expload createExpload(int x, int y) {
        return new GoodExpload(x,y);
    }

    public static TankFactory getInstance() {
        return instance;
    }

}
