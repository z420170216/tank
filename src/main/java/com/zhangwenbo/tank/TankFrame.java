package com.zhangwenbo.tank;

import com.zhangwenbo.tank.Enum.Dir;
import com.zhangwenbo.tank.bean.Bullet;
import com.zhangwenbo.tank.bean.Expload;
import com.zhangwenbo.tank.bean.Tank;
import com.zhangwenbo.tank.factory.impl.GoodTankFactory;
import com.zhangwenbo.tank.strategy.FourDirFireStrategy;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

public class TankFrame extends Frame {

    private Image offScreenImage = null;

    private volatile static TankFrame tf;
    public static final int GAME_WIDTH = 800, GAME_HEIGHT = 600;

    private Tank myTank = GoodTankFactory.getInstance().createTank(500,300,Dir.UP,FourDirFireStrategy.getInstance());
    private List<Tank> tanks = new ArrayList<Tank>();
    private List<Bullet> bullets = new ArrayList<Bullet>();
    private List<Expload> exploads = new ArrayList<Expload>();

    private TankFrame() {
        setSize(GAME_WIDTH, GAME_HEIGHT);
        setTitle("坦克大战");
        setResizable(false);
        setVisible(true);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        addKeyListener(new KeyAdapter() {
            boolean bU = false;
            boolean bL = false;
            boolean bR = false;
            boolean bD = false;

            // 按下
            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                switch (keyCode) {
                    case KeyEvent.VK_LEFT:
                        bL = true;
                        break;
                    case KeyEvent.VK_RIGHT:
                        bR = true;
                        break;
                    case KeyEvent.VK_DOWN:
                        bD = true;
                        break;
                    case KeyEvent.VK_UP:
                        bU = true;
                        break;
                    case KeyEvent.VK_CONTROL:
                        myTank.fire();
                        break;
                    default:
                }
                setMainTankDir();
            }

            // 释放
            @Override
            public void keyReleased(KeyEvent e) {
                int keyCode = e.getKeyCode();
                switch (keyCode) {
                    case KeyEvent.VK_LEFT:
                        bL = false;
                        break;
                    case KeyEvent.VK_RIGHT:
                        bR = false;
                        break;
                    case KeyEvent.VK_DOWN:
                        bD = false;
                        break;
                    case KeyEvent.VK_UP:
                        bU = false;
                        break;
                    default:
                }
                setMainTankDir();
            }

            private void setMainTankDir() {
                if (!bL && !bR && !bU && !bD) {
                    myTank.setMoving(false);
                } else {
                    if (bL) myTank.setDir(Dir.LEFT);
                    if (bR) myTank.setDir(Dir.RIGHT);
                    if (bU) myTank.setDir(Dir.UP);
                    if (bD) myTank.setDir(Dir.DOWN);
                    myTank.setMoving(true);
                }
            }
        });
    }

    // 双缓冲概念解决屏幕闪烁
    @Override
    public void update(Graphics g) {
        if (offScreenImage == null) {
            offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
        }
        Graphics gOffScreen = offScreenImage.getGraphics();
        Color c = gOffScreen.getColor();
        gOffScreen.setColor(Color.BLACK);
        gOffScreen.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
        paint(gOffScreen);
        gOffScreen.setColor(c);
        g.drawImage(offScreenImage, 0, 0, null);
    }

    @Override
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

    public static TankFrame getInstance() {
        if (tf == null) {
            synchronized (TankFrame.class) {
                if (tf == null) {
                    tf = new TankFrame();
                    return tf;
                } else {
                    return tf;
                }
            }
        } else {
            return tf;
        }
    }
}
