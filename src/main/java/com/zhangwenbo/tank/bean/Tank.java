package com.zhangwenbo.tank.bean;

import com.zhangwenbo.tank.Enum.Dir;
import com.zhangwenbo.tank.Enum.Group;
import com.zhangwenbo.tank.TankFrame;
import com.zhangwenbo.tank.mgr.PropertyMgr;
import com.zhangwenbo.tank.mgr.ResourceMgr;
import com.zhangwenbo.tank.model.GameModel;
import com.zhangwenbo.tank.strategy.FireStrategy;
import com.zhangwenbo.tank.utils.Audio;

import java.awt.*;
import java.lang.reflect.Constructor;
import java.util.Random;


public class Tank extends GameObject {
    private int oldX, oldY;
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
        this.width = ResourceMgr.getInstance().getBadTankL().getWidth();
        this.height = ResourceMgr.getInstance().getBadTankL().getHeight();
        rect.x = this.x;
        rect.y = this.y;
        rect.width = width;
        rect.height = height;
        try {
            if (group == Group.GOOD) {
                moving = false;
                Class<?> clazz = Class.forName(PropertyMgr.getInstance().getString("goodFS"));
                Constructor<?> constructor = clazz.getDeclaredConstructor();
                constructor.setAccessible(true);
                fs = (FireStrategy) constructor.newInstance();
            } else {
                Class<?> clazz = Class.forName(PropertyMgr.getInstance().getString("badFS"));
                Constructor<?> constructor = clazz.getDeclaredConstructor();
                constructor.setAccessible(true);
                fs = (FireStrategy) constructor.newInstance();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (group == Group.BAD) {
            GameModel.getInstance().add(this);
        }
    }


    public void paint(Graphics g) {
        if (!living) {
            GameModel.getInstance().remove(this);
            return;
        }
        move();
        ResourceMgr resourceMgr = ResourceMgr.getInstance();
        switch (dir) {
            case UP:
                if (group == Group.BAD) {
                    g.drawImage(resourceMgr.getBadTankU(), x, y, null);
                } else {
                    g.drawImage(resourceMgr.getGoodTankU(), x, y, null);
                }
                break;
            case DOWN:
                if (group == Group.BAD) {
                    g.drawImage(resourceMgr.getBadTankD(), x, y, null);
                } else {
                    g.drawImage(resourceMgr.getGoodTankD(), x, y, null);
                }
                break;
            case LEFT:
                if (group == Group.BAD) {
                    g.drawImage(resourceMgr.getBadTankL(), x, y, null);
                } else {
                    g.drawImage(resourceMgr.getGoodTankL(), x, y, null);
                }
                break;
            case RIGHT:
                if (group == Group.BAD) {
                    g.drawImage(resourceMgr.getBadTankR(), x, y, null);
                } else {
                    g.drawImage(resourceMgr.getGoodTankR(), x, y, null);
                }
                break;
        }
    }

    private void move() {
        oldX = x;
        oldY = y;
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

    public void reset() {
        this.x = oldX;
        this.y = oldY;
    }

    private void boundsCheck() {
        if (x < 0) x = 0;
        if (x > TankFrame.GAME_WIDTH - width) x = TankFrame.GAME_WIDTH - width;
        if (y < 30) y = 30;
        if (y > TankFrame.GAME_HEIGHT - height) y = TankFrame.GAME_HEIGHT - height;
    }

    private void turn() {
        dir = Dir.values()[random.nextInt(4)];
    }

    public void fire() {
        fs.fire(this);
    }

    public void die() {
        this.living = false;
        new Expload(this.x + width / 2 - Expload.WIDTH / 2, this.y + height / 2 - Expload.HEIGHT / 2, this.group);
        new Thread(new Runnable() {
            public void run() {
                new Audio("audio/explode.wav").play();
            }
        }).start();
    }

    public Group getGroup() {
        return group;
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
