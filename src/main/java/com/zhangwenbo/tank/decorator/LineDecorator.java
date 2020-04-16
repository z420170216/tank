package com.zhangwenbo.tank.decorator;

import com.zhangwenbo.tank.bean.Bullet;
import com.zhangwenbo.tank.bean.GameObject;

import java.awt.*;

public class LineDecorator extends Decorator {

    public LineDecorator(GameObject go) {
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
        g.drawLine(this.x, this.y, this.x + 100, this.y + 100);
        g.setColor(color);
    }
}
