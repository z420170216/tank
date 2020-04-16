package com.zhangwenbo.tank.bean;

import com.zhangwenbo.tank.Enum.Group;
import com.zhangwenbo.tank.mgr.ResourceMgr;
import com.zhangwenbo.tank.model.GameModel;

import java.awt.*;

public class Expload extends GameObject {
    public static int WIDTH = ResourceMgr.getInstance().getGoodExplodes()[0].getWidth();
    public static int HEIGHT = ResourceMgr.getInstance().getGoodExplodes()[0].getHeight();
    private int step = 0;
    private Group group = Group.GOOD;
    private boolean living = true;

    public Expload() {
        super();
    }

    public Expload(int x, int y, Group group) {
        super();
        this.x = x;
        this.group = group;
        this.y = y;
        GameModel.getInstance().add(this);
    }

    public void paint(Graphics g) {
        ResourceMgr resourceMgr = ResourceMgr.getInstance();
        if (living) {
            g.drawImage(resourceMgr.getGoodExplodes()[step], x, y, null);
            step++;
        }
        if (step >= resourceMgr.getGoodExplodes().length) {
            die();
            GameModel.getInstance().remove(this);
        }
    }


    public void die() {
        this.living = false;
    }
}
