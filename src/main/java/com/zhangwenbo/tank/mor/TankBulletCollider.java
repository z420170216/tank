package com.zhangwenbo.tank.mor;

import com.zhangwenbo.tank.bean.Bullet;
import com.zhangwenbo.tank.bean.GameObject;
import com.zhangwenbo.tank.bean.Tank;

public class TankBulletCollider implements Collider {
    public boolean collideWith(GameObject o1, GameObject o2) {
        if (o1 instanceof Bullet && o2 instanceof Tank) {
            Bullet b = (Bullet) o1;
            Tank t = (Tank) o2;
            if (b.getGroup() == t.getGroup()) {
                return true;
            } else {
                if (b.getRect().intersects(t.getRect())) {
                    b.die();
                    t.die();
                }
            }

            return false;
        } else if (o2 instanceof Bullet && o1 instanceof Tank) {
            collideWith(o2, o1);
        }
        return true;
    }
}
