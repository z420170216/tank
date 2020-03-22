package com.zhangwenbo.tank;

import com.zhangwenbo.tank.Enum.Dir;
import com.zhangwenbo.tank.Enum.Group;
import com.zhangwenbo.tank.bean.Tank;
import com.zhangwenbo.tank.mgr.PropertyMgr;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        TankFrame tf = TankFrame.getInstance();
        PropertyMgr propMgr = PropertyMgr.getInstance();
        for (int i = 0; i < propMgr.getInteger("initTankCount"); i++) {
            tf.getTanks().add(new Tank(200+i*80,200,Group.BAD,Dir.DOWN));
        }
        while(true){
            Thread.sleep(50);
            tf.repaint();
        }

    }
}
