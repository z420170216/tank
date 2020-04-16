package com.zhangwenbo.tank.decorator;

import com.zhangwenbo.tank.bean.Bullet;
import com.zhangwenbo.tank.bean.GameObject;

import java.awt.*;

public class RectDecorator extends Decorator {

    public RectDecorator(GameObject go) {
        this.go = go;
        this.x = go.getX();
        this.y = go.getY();
    }

    public void paint(Graphics g) {
        if(go instanceof Bullet){

        }else{
            go.paint(g);
        }
        this.x = go.getX();
        this.y = go.getY();
        Color color = g.getColor();
        g.setColor(Color.white);
        g.drawRect(this.x, this.y, go.getWidth() + 2, go.getHeight() + 2);
        g.setColor(color);
    }
}
