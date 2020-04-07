package com.zhangwenbo.tank.bean;

import com.zhangwenbo.tank.Enum.Group;
import com.zhangwenbo.tank.TankFrame;
import com.zhangwenbo.tank.mgr.ResourceMgr;
import com.zhangwenbo.tank.utils.Audio;

import java.awt.*;

public class BadExpload extends Expload{
    public static int width = ResourceMgr.getInstance().getBadExplodes()[0].getWidth();
    public static int height = ResourceMgr.getInstance().getBadExplodes()[0].getHeight();
    private int step = 0;
    private boolean living = true;

    public BadExpload(int x, int y) {
        super();
        group = Group.BAD;
        this.x = x;
        this.y = y;
        TankFrame.getInstance().getExploads().add(this);
        new Thread(new Runnable() {
            public void run() {
                new Audio("audio/explode.wav").play();
            }
        }).start();
    }

    public void paint(Graphics g) {
        ResourceMgr resourceMgr = ResourceMgr.getInstance();
        if (living) {
            g.drawImage(resourceMgr.getBadExplodes()[step], x, y, null);
            step++;
        }
        if (step >= resourceMgr.getBadExplodes().length) {
            die();
            TankFrame.getInstance().getExploads().remove(this);
        }
    }


    public void die() {
        this.living = false;
    }
}
