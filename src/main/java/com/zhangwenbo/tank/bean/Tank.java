package com.zhangwenbo.tank.bean;

import com.zhangwenbo.tank.Enum.Dir;
import com.zhangwenbo.tank.Enum.Group;
import com.zhangwenbo.tank.TankFrame;
import com.zhangwenbo.tank.mgr.ResourceMgr;
import com.zhangwenbo.tank.utils.Audio;

import java.awt.*;
import java.util.Random;

public class Tank {
    private int x = 300, y = 500;
    public static int WIDTH = ResourceMgr.tankL.getWidth();
    public static int HEIGHT = ResourceMgr.tankL.getHeight();
    private static final int SPEED = 5;
    private Dir dir = Dir.DOWN;
    private boolean moving = true;
    private Group group = Group.GOOD;
    private boolean living = true;
    private Random random = new Random();

    Rectangle rect = new Rectangle();

    public Tank() {
        super();
    }

    public Tank(int x, int y, Group group, Dir dir) {
        super();
        this.x = x;
        this.group = group;
        this.y = y;
        this.dir = dir;

        rect.x = this.x;
        rect.y = this.y;
        rect.width = WIDTH;
        rect.height = HEIGHT;
    }


    public void paint(Graphics g) {
        if (!living) {
            TankFrame.getInstance().getTanks().remove(this);
            return;
        }
        move();
        switch (dir) {
            case UP:
                g.drawImage(ResourceMgr.tankU, x, y, null);
                break;
            case DOWN:
                g.drawImage(ResourceMgr.tankD, x, y, null);
                break;
            case LEFT:
                g.drawImage(ResourceMgr.tankL, x, y, null);
                break;
            case RIGHT:
                g.drawImage(ResourceMgr.tankR, x, y, null);
                break;
        }
    }

    private void move() {
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
        if (x > 800 - WIDTH) x = 800 - WIDTH;
        if (y < 30) y = 30;
        if (y > 600 - HEIGHT) y = 600 - HEIGHT;
    }

    private void turn() {
        dir = Dir.values()[random.nextInt(4)];
    }

    public void fire() {
        Bullet bullet = new Bullet(this.x + WIDTH / 2 - Bullet.WIDTH / 2, this.y + HEIGHT / 2 - Bullet.HEIGHT / 2, this.group, this.dir);
        TankFrame.getInstance().getBullets().add(bullet);
    }

    public void die() {
        this.living = false;
        TankFrame.getInstance().getExploads().add(new Expload(this.x + WIDTH / 2 - Expload.WIDTH / 2, this.y + HEIGHT / 2 - Expload.HEIGHT / 2, this.group));
        new Thread(new Runnable() {
            public void run() {
                new Audio("audio/explode.wav").play();
            }
        }).start();
    }


    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public boolean isLiving() {
        return living;
    }

    public void setLiving(boolean living) {
        this.living = living;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Dir getDir() {
        return dir;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }
}
