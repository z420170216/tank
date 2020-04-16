package com.zhangwenbo.tank.model;

import com.zhangwenbo.tank.Enum.Dir;
import com.zhangwenbo.tank.Enum.Group;
import com.zhangwenbo.tank.bean.GameObject;
import com.zhangwenbo.tank.bean.Tank;
import com.zhangwenbo.tank.bean.Wall;
import com.zhangwenbo.tank.mgr.PropertyMgr;
import com.zhangwenbo.tank.mor.ChainCollider;
import com.zhangwenbo.tank.mor.Collider;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GameModel {

    private static GameModel instance = new GameModel();
    private Tank myTank = new Tank(500, 300, Group.GOOD, Dir.UP);
    private List<GameObject> objects = new ArrayList<GameObject>();
    private Collider chainCollider = new ChainCollider();

    public Tank getMyTank() {
        return myTank;
    }

    public void paint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.white);
        g.setColor(c);
        myTank.paint(g);
        for (int i = 0; i < objects.size(); i++) {
            objects.get(i).paint(g);
        }
        for (int i = 0; i < objects.size(); i++) {
            for (int j = i + 1; j < objects.size(); j++) {
                GameObject o1 = objects.get(i);
                GameObject o2 = objects.get(j);
                chainCollider.collideWith(o1, o2);
            }
        }
    }

    private GameModel() {

    }

    static {
        init();
    }

    private static void init() {
        PropertyMgr propMgr = PropertyMgr.getInstance();
        for (int i = 0; i < propMgr.getInteger("initTankCount"); i++) {
            new Tank(200 + i * 80, 200, Group.BAD, Dir.DOWN);
        }
        for (int i = 0; i < propMgr.getInteger("initWallCount"); i++) {
            new Wall(200 + i * 500, 300);
        }
    }

    public static GameModel getInstance() {
        return instance;
    }

    public void add(GameObject go) {
        this.objects.add(go);
    }

    public void remove(GameObject go) {
        this.objects.remove(go);
    }
}
