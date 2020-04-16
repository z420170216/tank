package com.zhangwenbo.tank.mor;

import com.zhangwenbo.tank.bean.GameObject;
import com.zhangwenbo.tank.bean.Tank;
import com.zhangwenbo.tank.bean.Wall;

public class WallTankCollider implements Collider {
    public boolean collideWith(GameObject o1, GameObject o2) {
        if (o1 instanceof Wall && o2 instanceof Tank) {
            Wall w = (Wall) o1;
            Tank t = (Tank) o2;
            if (w.getRect().intersects(t.getRect())) {
                t.reset();
            }
        } else if (o1 instanceof Tank && o2 instanceof Wall) {
            collideWith(o2, o1);
        }
        return true;
    }
}
