package com.zhangwenbo.tank.bean;

import com.zhangwenbo.tank.Enum.Dir;
import com.zhangwenbo.tank.Enum.Group;
import com.zhangwenbo.tank.TankFrame;
import com.zhangwenbo.tank.mgr.PropertyMgr;
import com.zhangwenbo.tank.mgr.ResourceMgr;
import com.zhangwenbo.tank.strategy.DefaultFireStrategy;
import com.zhangwenbo.tank.strategy.FireStrategy;
import com.zhangwenbo.tank.utils.Audio;

import java.awt.*;
import java.lang.reflect.Constructor;
import java.util.Random;

public class Tank {
    private int x = 300, y = 500;
    public static int WIDTH = ResourceMgr.getInstance().getTankL().getWidth();
    public static int HEIGHT = ResourceMgr.getInstance().getTankL().getHeight();
    private static final int SPEED = 5;
    private Dir dir = Dir.DOWN;
    private boolean moving = true;
    private Group group = Group.GOOD;
    private boolean living = true;
    private Random random = new Random();

    private FireStrategy fs = null;

    private Rectangle rect = new Rectangle();


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
        try{
            if(group == Group.GOOD){
                Class<?> clazz = Class.forName(PropertyMgr.getInstance().getString("goodFS"));
                Constructor<?> constructor = clazz.getDeclaredConstructor();
                constructor.setAccessible(true);
                fs = (FireStrategy) constructor.newInstance();
            }else{
                Class<?> clazz = Class.forName(PropertyMgr.getInstance().getString("badFS"));
                Constructor<?> constructor = clazz.getDeclaredConstructor();
                constructor.setAccessible(true);
                fs = (FireStrategy) constructor.newInstance();
            }
        }catch (Exception e){

        }
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
                g.drawImage(resourceMgr.getTankU(), x, y, null);
                break;
            case DOWN:
                g.drawImage(resourceMgr.getTankD(), x, y, null);
                break;
            case LEFT:
                g.drawImage(resourceMgr.getTankL(), x, y, null);
                break;
            case RIGHT:
                g.drawImage(resourceMgr.getTankR(), x, y, null);
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
        fs.fire(this);
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

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
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

    public Rectangle getRect() {
        return rect;
    }
}
