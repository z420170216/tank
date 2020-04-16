package com.zhangwenbo.tank.bean;

import com.zhangwenbo.tank.model.GameModel;

import java.awt.*;

public class Wall extends GameObject {
    public static int WIDTH = 80;
    public static int HEIGHT = 80;

    private Rectangle rect;

    public Wall(int x, int y) {
        this.x = x;
        this.y = y;
        rect = new Rectangle();
        rect.x = x;
        rect.y = y;
        rect.width = WIDTH;
        rect.height = HEIGHT;
        GameModel.getInstance().add(this);
    }

    public Rectangle getRect() {
        return rect;
    }

    public void paint(Graphics g) {
        Color color = g.getColor();
        g.setColor(Color.white);
        g.fillRect(this.x, this.y, WIDTH, HEIGHT);
        g.setColor(color);
    }
}
