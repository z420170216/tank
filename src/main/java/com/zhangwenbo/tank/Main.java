package com.zhangwenbo.tank;

import com.zhangwenbo.tank.Enum.Dir;

import com.zhangwenbo.tank.factory.impl.BadTankFactory;
import com.zhangwenbo.tank.mgr.PropertyMgr;
import com.zhangwenbo.tank.strategy.DefaultFireStrategy;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        TankFrame tf = TankFrame.getInstance();
        PropertyMgr propMgr = PropertyMgr.getInstance();
        for (int i = 0; i < propMgr.getInteger("initTankCount"); i++) {
            tf.getTanks().add(BadTankFactory.getInstance().createTank(200 + i * 80, 200, Dir.DOWN, DefaultFireStrategy.getInstance()));
        }
        while (true) {
            Thread.sleep(50);
            tf.repaint();
        }

    }
}
