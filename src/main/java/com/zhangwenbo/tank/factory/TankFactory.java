package com.zhangwenbo.tank.factory;

import com.zhangwenbo.tank.Enum.Dir;
import com.zhangwenbo.tank.bean.Bullet;
import com.zhangwenbo.tank.bean.Expload;
import com.zhangwenbo.tank.bean.Tank;
import com.zhangwenbo.tank.strategy.FireStrategy;

public interface TankFactory {

    Tank createTank(int x, int y, Dir dir, FireStrategy fs);
    Bullet createBullet(int x, int y, Dir dir);
    Expload createExpload(int x, int y);
}
