package com.zhangwenbo.tank;

import com.zhangwenbo.tank.Enum.Dir;
import com.zhangwenbo.tank.bean.Tank;
import com.zhangwenbo.tank.mgr.PropertyMgr;
import com.zhangwenbo.tank.model.GameModel;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TankFrame extends Frame {

    private Image offScreenImage = null;

    public static final int GAME_WIDTH = PropertyMgr.getInstance().getInteger("gameWidth"), GAME_HEIGHT = PropertyMgr.getInstance().getInteger("gameHeight");

    public TankFrame() {
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
                        GameModel.getInstance().getMyTank().fire();
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
                Tank myTank = GameModel.getInstance().getMyTank();
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
        GameModel.getInstance().paint(g);
    }

}
