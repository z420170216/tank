package com.zhangwenbo.tank.bean;

import com.zhangwenbo.tank.Enum.Dir;
import com.zhangwenbo.tank.Enum.Group;

import java.awt.*;

public abstract class Bullet {
    Group group;
    Rectangle rect;
    Dir dir;
    int x, y;

    public abstract void paint(Graphics g);

    public abstract void collideWith(Tank t);

    public Group getGroup() {
        return group;
    }

    public Rectangle getRect() {
        return rect;
    }

    public Dir getDir() {
        return dir;
    }

}
