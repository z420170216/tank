package com.zhangwenbo.tank.bean;

import com.zhangwenbo.tank.Enum.Group;
import com.zhangwenbo.tank.TankFrame;
import com.zhangwenbo.tank.mgr.ResourceMgr;
import com.zhangwenbo.tank.utils.Audio;

import java.awt.*;

public class GoodExpload extends Expload{
    public static int width = ResourceMgr.getInstance().getGoodExplodes()[0].getWidth();
    public static int height = ResourceMgr.getInstance().getGoodExplodes()[0].getHeight();
    private int step = 0;
    private boolean living = true;

    public GoodExpload(int x, int y) {
        super();
        group = Group.GOOD;
        this.x = x;
        this.group = Group.GOOD;
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
            g.drawImage(resourceMgr.getGoodExplodes()[step], x, y, null);
            step++;
        }
        if (step >= resourceMgr.getGoodExplodes().length) {
            die();
            TankFrame.getInstance().getExploads().remove(this);
        }
    }


    public void die() {
        this.living = false;
    }
}
