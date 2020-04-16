package com.zhangwenbo.tank.bean;

import com.zhangwenbo.tank.Enum.Dir;
import com.zhangwenbo.tank.Enum.Group;
import com.zhangwenbo.tank.TankFrame;
import com.zhangwenbo.tank.mgr.ResourceMgr;
import com.zhangwenbo.tank.model.GameModel;

import java.awt.*;

public class Bullet extends GameObject {
    private static final int SPEED = 10;
    private Dir dir = Dir.DOWN;
    private Group group = Group.BAD;

    private Rectangle rect = new Rectangle();

    private boolean living = true;

    public Bullet(int x, int y, Group group, Dir dir) {
        super();
        this.x = x;
        this.y = y;
        this.group = group;
        this.dir = dir;
        if (group == Group.GOOD) {
            this.width = ResourceMgr.getInstance().getGoodBulletL().getWidth();
            this.height = ResourceMgr.getInstance().getGoodBulletL().getHeight();
        } else {
            this.width = ResourceMgr.getInstance().getBadBulletL().getWidth();
            this.height = ResourceMgr.getInstance().getBadBulletL().getHeight();
        }


        this.rect.x = x;
        this.rect.y = y;
        this.rect.height = height;
        this.rect.width = width;

        GameModel.getInstance().add(this);
    }

    public Dir getDir() {
        return dir;
    }

    public void paint(Graphics g) {
        move();
        switch (dir) {
            case UP:
                if (group == Group.GOOD)
                    g.drawImage(ResourceMgr.getInstance().getGoodBulletU(), x, y, null);
                else
                    g.drawImage(ResourceMgr.getInstance().getBadBulletU(), x, y, null);
                break;
            case DOWN:
                if (group == Group.GOOD)
                    g.drawImage(ResourceMgr.getInstance().getGoodBulletD(), x, y, null);
                else
                    g.drawImage(ResourceMgr.getInstance().getBadBulletD(), x, y, null);
                break;
            case LEFT:
                if (group == Group.GOOD)
                    g.drawImage(ResourceMgr.getInstance().getGoodBulletL(), x, y, null);
                else
                    g.drawImage(ResourceMgr.getInstance().getBadBulletL(), x, y, null);
                break;
            case RIGHT:
                if (group == Group.GOOD)
                    g.drawImage(ResourceMgr.getInstance().getGoodBulletR(), x, y, null);
                else
                    g.drawImage(ResourceMgr.getInstance().getBadBulletR(), x, y, null);
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
        if (x < 0 || y < 0 || x > TankFrame.GAME_WIDTH || y > TankFrame.GAME_HEIGHT) {
            living = false;
        }
        if (!living) {
            GameModel.getInstance().remove(this);
        }
        rect.x = this.x;
        rect.y = this.y;
    }

    public void die() {
        this.living = false;
    }

    public Rectangle getRect() {
        return rect;
    }

    public Group getGroup() {
        return group;
    }
}
