package com.zhangwenbo.tank.bean;

import com.zhangwenbo.tank.Enum.Dir;
import com.zhangwenbo.tank.Enum.Group;
import com.zhangwenbo.tank.strategy.FireStrategy;

import java.awt.*;

public abstract class Tank {
    public Group group;
    public int x,y,width,height;
    public Dir dir = Dir.DOWN;
    public boolean moving = true;
    public boolean living = true;
    public FireStrategy fs;
    public Rectangle rect;


    public abstract void fire();
    public abstract void paint(Graphics g);
    public abstract void die();



    public Group getGroup() {
        return group;
    }
    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }
    public Dir getDir() {
        return dir;
    }
    public boolean isMoving() {
        return moving;
    }
    public boolean isLiving() {
        return living;
    }
    public Rectangle getRect() {
        return rect;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public void setDir(Dir dir) {
        this.dir = dir;
    }
    public void setMoving(boolean moving) {
        this.moving = moving;
    }
}
