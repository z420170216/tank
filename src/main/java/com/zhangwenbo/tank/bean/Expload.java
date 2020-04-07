package com.zhangwenbo.tank.bean;

import com.zhangwenbo.tank.Enum.Group;

import java.awt.*;

public abstract class Expload {
    Group group;
    int x,y;

    public abstract void paint(Graphics g);


    public Group getGroup(){
        return group;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
}
