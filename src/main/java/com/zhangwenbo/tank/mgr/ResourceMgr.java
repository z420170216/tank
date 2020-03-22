package com.zhangwenbo.tank.mgr;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ResourceMgr {
    private BufferedImage tankL, tankR, tankU, tankD, bulletL, bulletR, bulletD, bulletU;
    private BufferedImage[] explodes = new BufferedImage[16];
    private static ResourceMgr INSTANCE = null;

    private ResourceMgr() {
    }

    static {
        try {
            INSTANCE = new ResourceMgr();
            INSTANCE.tankL = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/tankL.gif"));
            INSTANCE.tankR = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/tankR.gif"));
            INSTANCE.tankU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/tankU.gif"));
            INSTANCE.tankD = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/tankD.gif"));
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

    public BufferedImage getTankL() {
        return tankL;
    }


    public BufferedImage getTankR() {
        return tankR;
    }


    public BufferedImage getTankU() {
        return tankU;
    }


    public BufferedImage getTankD() {
        return tankD;
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
