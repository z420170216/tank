package com.zhangwenbo.tank.bean;

import com.zhangwenbo.tank.Enum.Dir;
import com.zhangwenbo.tank.TankFrame;

import java.awt.*;

public class Bullet {
    private static final int SPEED = 10, WIDTH = 10, HEIGHT = 10;
    private Dir dir = Dir.DOWN;
    private int x, y;

    private boolean living = true;

    public Bullet(int x, int y, Dir dir) {
        super();
        this.x = x;
        this.y = y;
        this.dir = dir;
    }

    public Dir getDir() {
        return dir;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public void paint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.RED);
        move();
        g.fillOval(this.x, this.y, WIDTH, HEIGHT);
        g.setColor(c);
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
        if(!living){
            tf.getBullets().remove(this);
        }
    }
}
