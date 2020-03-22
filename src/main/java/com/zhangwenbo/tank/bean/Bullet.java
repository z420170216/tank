package com.zhangwenbo.tank.bean;

import com.zhangwenbo.tank.Enum.Dir;
import com.zhangwenbo.tank.Enum.Group;
import com.zhangwenbo.tank.TankFrame;
import com.zhangwenbo.tank.mgr.ResourceMgr;

import java.awt.*;

public class Bullet {
    private static final int SPEED = 10;
    private Dir dir = Dir.DOWN;
    private int x, y;
    private Group group = Group.BAD;

    public static int WIDTH = ResourceMgr.bulletL.getWidth();
    public static int HEIGHT = ResourceMgr.bulletL.getHeight();

    private boolean living = true;

    public Bullet(int x, int y, Group group, Dir dir) {
        super();
        this.x = x;
        this.y = y;
        this.group = group;
        this.dir = dir;
    }

    public Dir getDir() {
        return dir;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public void paint(Graphics g) {
        move();
        switch (dir) {
            case UP:
                g.drawImage(ResourceMgr.bulletU, x, y, null);
                break;
            case DOWN:
                g.drawImage(ResourceMgr.bulletD, x, y, null);
                break;
            case LEFT:
                g.drawImage(ResourceMgr.bulletL, x, y, null);
                break;
            case RIGHT:
                g.drawImage(ResourceMgr.bulletR, x, y, null);
                break;
        }
    }

    private void move() {
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
        TankFrame tf = TankFrame.getInstance();
        if (x < 0 || y < 0 || x > tf.GAME_WIDTH || y > tf.GAME_HEIGHT) {
            living = false;
        }
        if (!living) {
            tf.getBullets().remove(this);
        }
    }

    public void collideWith(Tank tank) {
        if (this.group == tank.getGroup()) {
            return ;
        } else {
            Rectangle tankRectangle = new Rectangle(tank.getX(), tank.getY(), Tank.WIDTH, Tank.HEIGHT);
            Rectangle bulletRectangle = new Rectangle(this.x, this.y, WIDTH, HEIGHT);
            if (tankRectangle.intersects(bulletRectangle)) {
                this.die();
                tank.die();
            }
        }
    }

    private void die() {
        this.living = false;
    }
}
