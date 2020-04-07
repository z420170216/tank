package com.zhangwenbo.tank.bean;

import com.zhangwenbo.tank.Enum.Dir;
import com.zhangwenbo.tank.Enum.Group;
import com.zhangwenbo.tank.TankFrame;
import com.zhangwenbo.tank.factory.impl.GoodTankFactory;
import com.zhangwenbo.tank.mgr.ResourceMgr;
import com.zhangwenbo.tank.strategy.FireStrategy;

import java.awt.*;

public class GoodTank extends Tank {
    private static final int SPEED = 5;


    public GoodTank(int x, int y, Dir dir, FireStrategy fs) {
        super();
        this.x = x;
        this.group = Group.GOOD;
        this.y = y;
        this.dir = dir;
        this.width = ResourceMgr.getInstance().getGoodTankL().getWidth() ;
        this.height = ResourceMgr.getInstance().getGoodTankL().getHeight() ;

        rect = new Rectangle();
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
                g.drawImage(resourceMgr.getGoodTankU(), x, y, null);
                break;
            case DOWN:
                g.drawImage(resourceMgr.getGoodTankD(), x, y, null);
                break;
            case LEFT:
                g.drawImage(resourceMgr.getGoodTankL(), x, y, null);
                break;
            case RIGHT:
                g.drawImage(resourceMgr.getGoodTankR(), x, y, null);
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

    public void fire() {
        fs.fire(this);
    }

    public void die() {
        this.living = false;
        GoodTankFactory.getInstance().createExpload(this.x,this.y);
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
