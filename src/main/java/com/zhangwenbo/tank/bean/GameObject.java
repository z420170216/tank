package com.zhangwenbo.tank.bean;

import java.awt.*;

public abstract class GameObject {
    protected int x, y;
    protected int width;
    protected int height;

    public abstract void paint(Graphics g);

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
