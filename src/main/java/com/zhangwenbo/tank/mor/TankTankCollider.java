package com.zhangwenbo.tank.mor;

import com.zhangwenbo.tank.bean.GameObject;
import com.zhangwenbo.tank.bean.Tank;

public class TankTankCollider implements Collider {
    public boolean collideWith(GameObject o1, GameObject o2) {
        if (o1 instanceof Tank && o2 instanceof Tank) {
            Tank t1 = (Tank) o1;
            Tank t2 = (Tank) o2;
            if (t1.getRect().intersects(t2.getRect())) {
                t1.reset();
                t2.reset();
            }
        }
        return true;
    }
}
