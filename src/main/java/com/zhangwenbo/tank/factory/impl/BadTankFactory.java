package com.zhangwenbo.tank.factory.impl;


import com.zhangwenbo.tank.Enum.Dir;
import com.zhangwenbo.tank.bean.*;
import com.zhangwenbo.tank.factory.TankFactory;
import com.zhangwenbo.tank.strategy.FireStrategy;

public class BadTankFactory implements TankFactory {
    private static BadTankFactory instance = new BadTankFactory();
    private BadTankFactory(){

    }
    public static TankFactory getInstance() {
        return instance;
    }

    public Tank createTank(int x, int y, Dir dir, FireStrategy fs) {
        return new BadTank(x,y,dir,fs);
    }

    public Bullet createBullet(int x, int y,Dir dir) {
        return new BadBullet(x,y,dir);
    }

    public Expload createExpload(int x, int y) {
        return new BadExpload(x,y);
    }
}
