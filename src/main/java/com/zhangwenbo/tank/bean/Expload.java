package com.zhangwenbo.tank.bean;

import com.zhangwenbo.tank.Enum.Group;
import com.zhangwenbo.tank.TankFrame;
import com.zhangwenbo.tank.mgr.ResourceMgr;

import java.awt.*;

public class Expload {
    private int x = 300, y = 500;
    public static int WIDTH = ResourceMgr.tankL.getWidth();
    public static int HEIGHT = ResourceMgr.tankL.getHeight();
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
    }

    public void paint(Graphics g) {
        if (living) {
            g.drawImage(ResourceMgr.explodes[step], x, y, null);
            step++;
        }
        if (step >= ResourceMgr.explodes.length) {
            die();
            TankFrame.getInstance().getExploads().remove(this);
        }
    }


    public void die() {
        this.living = false;
    }
}
