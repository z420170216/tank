package com.zhangwenbo.tank.bean;

import com.zhangwenbo.tank.Enum.Dir;
import com.zhangwenbo.tank.TankFrame;

import java.awt.*;

public class Tank {
    private int x = 200, y = 200;
    private static final int SPEED = 5;
    private Dir dir = Dir.DOWN;
    private boolean moving = false;



    public Tank() {
        super();
    }

    public Tank(int x, int y, Dir dir) {
        super();
        this.x = x;
        this.y = y;
        this.dir = dir;
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

    public void paint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.YELLOW);
        move();
        g.fillRect(x, y, 50, 50);
        g.setColor(c);
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
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
    }

    public void fire() {
        Bullet bullet = new Bullet(this.x,this.y,this.dir);
        TankFrame.getInstance().getBullets().add(bullet);
    }
}
