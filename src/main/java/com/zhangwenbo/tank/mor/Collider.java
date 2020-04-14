package com.zhangwenbo.tank.mor;

import com.zhangwenbo.tank.bean.GameObject;

public interface Collider {

    boolean collideWith(GameObject o1, GameObject o2);
}
