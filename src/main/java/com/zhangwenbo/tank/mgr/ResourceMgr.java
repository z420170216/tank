package com.zhangwenbo.tank.mgr;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ResourceMgr {
    private BufferedImage goodTankL, goodTankR, goodTankU, goodTankD, bulletL, bulletR, bulletD, bulletU;
    private BufferedImage[] explodes = new BufferedImage[16];
    private static ResourceMgr INSTANCE = null;

    private ResourceMgr() {
    }

    static {
        try {
            INSTANCE = new ResourceMgr();
            INSTANCE.goodTankL = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/GoodTank1.gif"));
            INSTANCE.goodTankR = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/goodTankR.gif"));
            INSTANCE.goodTankU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/goodTankU.gif"));
            INSTANCE.goodTankD = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/goodTankD.gif"));
            INSTANCE.bulletL = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/bulletL.gif"));
            INSTANCE.bulletR = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/bulletR.gif"));
            INSTANCE.bulletD = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/bulletD.gif"));
            INSTANCE.bulletU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/bulletU.gif"));
            for (int i = 0; i < 16; i++) {
                INSTANCE.explodes[i] = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/e" + (i + 1) + ".gif"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ResourceMgr getInstance() {
        return INSTANCE;
    }

    public BufferedImage getgoodTankL() {
        return goodTankL;
    }


    public BufferedImage getgoodTankR() {
        return goodTankR;
    }


    public BufferedImage getgoodTankU() {
        return goodTankU;
    }


    public BufferedImage getgoodTankD() {
        return goodTankD;
    }


    public BufferedImage getBulletL() {
        return bulletL;
    }


    public BufferedImage getBulletR() {
        return bulletR;
    }


    public BufferedImage getBulletD() {
        return bulletD;
    }


    public BufferedImage getBulletU() {
        return bulletU;
    }


    public BufferedImage[] getExplodes() {
        return explodes;
    }

}
