package com.zhangwenbo.tank.bean;

import com.zhangwenbo.tank.Enum.Dir;
import com.zhangwenbo.tank.Enum.Group;
import com.zhangwenbo.tank.TankFrame;
import com.zhangwenbo.tank.model.GameModel;
import com.zhangwenbo.tank.mgr.ResourceMgr;

import java.awt.*;

public class Bullet extends GameObject{
    private static final int SPEED = 10;
    private Dir dir = Dir.DOWN;
    private int x, y;
    private Group group = Group.BAD;

    public static int WIDTH = ResourceMgr.getInstance().getBulletL().getWidth();
    public static int HEIGHT = ResourceMgr.getInstance().getBulletL().getHeight();

    private Rectangle rect = new Rectangle();



    private boolean living = true;

    public Bullet(int x, int y, Group group, Dir dir) {
        super();
        this.x = x;
        this.y = y;
        this.group = group;
        this.dir = dir;

        rect.x=x;
        rect.y=y;
        rect.height=HEIGHT;
        rect.width=WIDTH;

        GameModel.getInstance().add(this);
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
                g.drawImage(ResourceMgr.getInstance().getBulletU(), x, y, null);
                break;
            case DOWN:
                g.drawImage(ResourceMgr.getInstance().getBulletD(), x, y, null);
                break;
            case LEFT:
                g.drawImage(ResourceMgr.getInstance().getBulletL(), x, y, null);
                break;
            case RIGHT:
                g.drawImage(ResourceMgr.getInstance().getBulletR(), x, y, null);
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
        rect.x=this.x;
        rect.y=this.y;
    }

    public void collideWith(Tank tank) {
        if (this.group == tank.getGroup()) {
            return ;
        } else {
            if (this.rect.intersects(tank.getRect())) {
                this.die();
                tank.die();
            }
        }
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
