package com.zhangwenbo.tank.mor;

import com.zhangwenbo.tank.bean.Bullet;
import com.zhangwenbo.tank.bean.GameObject;
import com.zhangwenbo.tank.bean.Wall;

public class WallBulletCollider implements Collider {
    public boolean collideWith(GameObject o1, GameObject o2) {
        if (o1 instanceof Wall && o2 instanceof Bullet) {
            Wall w = (Wall) o1;
            Bullet b = (Bullet) o2;
            if (w.getRect().intersects(b.getRect())) {
                b.die();
            }
        } else if (o1 instanceof Bullet && o2 instanceof Wall) {
            collideWith(o2, o1);
        }
        return true;
    }
}
