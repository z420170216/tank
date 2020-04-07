package com.zhangwenbo.tank.bean;

import com.zhangwenbo.tank.Enum.Dir;
import com.zhangwenbo.tank.Enum.Group;
import com.zhangwenbo.tank.TankFrame;
import com.zhangwenbo.tank.factory.impl.BadTankFactory;
import com.zhangwenbo.tank.factory.impl.GoodTankFactory;
import com.zhangwenbo.tank.mgr.PropertyMgr;
import com.zhangwenbo.tank.mgr.ResourceMgr;
import com.zhangwenbo.tank.strategy.DefaultFireStrategy;
import com.zhangwenbo.tank.strategy.FireStrategy;
import com.zhangwenbo.tank.utils.Audio;

import java.awt.*;
import java.lang.reflect.Constructor;
import java.util.Random;

public class BadTank extends Tank {
    private static final int SPEED = 5;

    private Random random = new Random();


    public BadTank(int x, int y, Dir dir,FireStrategy fs) {
        super();
        this.x = x;
        this.y = y;
        this.dir = dir;

        group = Group.BAD;
        rect = new Rectangle();

        this.width = ResourceMgr.getInstance().getBadTankL().getWidth();
        this.height = ResourceMgr.getInstance().getBadTankL().getHeight();

        rect.x = this.x;
        rect.y = this.y;
        rect.width = width;
        rect.height = height;

        this.fs = fs;
    }


    public void paint(Graphics g) {
        if (!living) {
            TankFrame.getInstance().getTanks().remove(this);
            return;
        }
        move();
        ResourceMgr resourceMgr = ResourceMgr.getInstance();
        switch (dir) {
            case UP:
                g.drawImage(resourceMgr.getBadTankU(), x, y, null);
                break;
            case DOWN:
                g.drawImage(resourceMgr.getBadTankD(), x, y, null);
                break;
            case LEFT:
                g.drawImage(resourceMgr.getBadTankL(), x, y, null);
                break;
            case RIGHT:
                g.drawImage(resourceMgr.getBadTankR(), x, y, null);
                break;
        }
    }

    public void move() {
        if (moving) {
            switch (dir) {
                case UP:
                    y -= SPEED;
                    break;
                case DOWN:
                    y += SPEED;
                    break;
                case LEFT:
                    x -= SPEED;
                    break;
                case RIGHT:
                    x += SPEED;
                    break;
            }
        }
        if (this.group == Group.BAD) {
            if (random.nextInt(100) > 95) {
                fire();
            }
            if (random.nextInt(100) > 95) {
                turn();
            }
        }
        boundsCheck();
        rect.x = this.x;
        rect.y = this.y;
    }

    private void boundsCheck() {
        if (x < 0) x = 0;
        if (x > 800 - width) x = 800 - width;
        if (y < 30) y = 30;
        if (y > 600 - height) y = 600 - height;
    }

    public void turn() {
        dir = Dir.values()[random.nextInt(4)];
    }

    public void fire() {
        fs.fire(this);
    }

    public void die() {
        this.living = false;
        BadTankFactory.getInstance().createExpload(this.x + width / 2 - GoodExpload.width / 2, this.y + height / 2 - GoodExpload.height / 2);
    }

    public Group getGroup() {
        return group;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Dir getDir() {
        return dir;
    }

    public Rectangle getRect() {
        return rect;
    }
}
