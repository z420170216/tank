package com.zhangwenbo.tank.facade;

import com.zhangwenbo.tank.Enum.Dir;
import com.zhangwenbo.tank.Enum.Group;
import com.zhangwenbo.tank.bean.Bullet;
import com.zhangwenbo.tank.bean.Expload;
import com.zhangwenbo.tank.bean.Tank;
import com.zhangwenbo.tank.mgr.PropertyMgr;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GameFacade {

    private static GameFacade instance = new GameFacade();

    private GameFacade() {
        PropertyMgr propMgr = PropertyMgr.getInstance();
        for (int i = 0; i < propMgr.getInteger("initTankCount"); i++) {
            tanks.add(new Tank(200+i*80,200,Group.BAD,Dir.DOWN));
        }
    }

    public static GameFacade getInstance() {
        return instance;
    }

    private Tank myTank = new Tank(500, 300, Group.GOOD, Dir.UP);
    private List<Tank> tanks = new ArrayList<Tank>();
    private List<Bullet> bullets = new ArrayList<Bullet>();
    private List<Expload> exploads = new ArrayList<Expload>();

    public Tank getMyTank() {
        return myTank;
    }

    public void paint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.white);
        g.drawString("子弹的数量:" + bullets.size(), 10, 60);
        g.drawString("敌人的数量:" + tanks.size(), 10, 80);
        g.drawString("爆炸的数量:" + exploads.size(), 10, 100);
        g.setColor(c);
        myTank.paint(g);
        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).paint(g);
        }
        for (int i = 0; i < tanks.size(); i++) {
            tanks.get(i).paint(g);
        }
        for (int i = 0; i < bullets.size(); i++) {
            for (int j = 0; j < tanks.size(); j++) {
                bullets.get(i).collideWith(tanks.get(j));
            }
        }
        for (int i = 0; i < exploads.size(); i++) {
            exploads.get(i).paint(g);
        }
    }

    public List<Expload> getExploads() {
        return exploads;
    }

    public List<Tank> getTanks() {
        return tanks;
    }

    public List<Bullet> getBullets() {
        return bullets;
    }
}
