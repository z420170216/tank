package com.zhangwenbo.tank.bean;

import com.zhangwenbo.tank.Enum.Dir;
import com.zhangwenbo.tank.Enum.Group;
import com.zhangwenbo.tank.TankFrame;
import com.zhangwenbo.tank.mgr.ResourceMgr;

import java.awt.*;

public class GoodBullet extends Bullet {
    private static final int SPEED = 10;
    public static int width = ResourceMgr.getInstance().getGoodBulletL().getWidth();
    public static int height = ResourceMgr.getInstance().getGoodBulletL().getHeight();
    private boolean living = true;

    public GoodBullet(int x, int y, Dir dir) {
        super();

        this.x = x;
        this.y = y;
        this.group = Group.GOOD;
        this.dir = dir;

        rect = new Rectangle();

        rect.x = x;
        rect.y = y;
        rect.height = height;
        rect.width = width;

        TankFrame.getInstance().getBullets().add(this);
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
                g.drawImage(ResourceMgr.getInstance().getGoodBulletU(), x, y, null);
                break;
            case DOWN:
                g.drawImage(ResourceMgr.getInstance().getGoodBulletD(), x, y, null);
                break;
            case LEFT:
                g.drawImage(ResourceMgr.getInstance().getGoodBulletL(), x, y, null);
                break;
            case RIGHT:
                g.drawImage(ResourceMgr.getInstance().getGoodBulletR(), x, y, null);
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
        rect.x = this.x;
        rect.y = this.y;
    }

    public void collideWith(Tank tank) {
        if (this.group == tank.getGroup()) {
            return;
        } else {
            if (this.rect.intersects(tank.getRect())) {
                this.die();
                tank.die();
            }
        }
    }

    private void die() {
        this.living = false;
    }
}
