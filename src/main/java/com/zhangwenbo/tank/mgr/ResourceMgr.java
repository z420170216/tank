package com.zhangwenbo.tank.mgr;

import com.zhangwenbo.tank.utils.ImageUtil;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ResourceMgr {
    private BufferedImage badTankL, badTankR, badTankU, badTankD, badBulletL, badBulletR, badBulletD, badBulletU;
    private BufferedImage goodTankL, goodTankR, goodTankU, goodTankD, goodBulletL, goodBulletR, goodBulletD, goodBulletU;
    private BufferedImage[] goodExplodes = new BufferedImage[16];
    private BufferedImage[] badExplodes = new BufferedImage[11];
    private static ResourceMgr INSTANCE = null;

    private ResourceMgr() {
    }

    static {
        try {
            INSTANCE = new ResourceMgr();
            INSTANCE.badTankU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/BadTank1.png"));
            INSTANCE.badTankR = ImageUtil.rotateImage(INSTANCE.badTankU,90);
            INSTANCE.badTankL = ImageUtil.rotateImage(INSTANCE.badTankU,-90);
            INSTANCE.badTankD = ImageUtil.rotateImage(INSTANCE.badTankU,180);
            INSTANCE.badBulletL = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/bulletL.gif"));
            INSTANCE.badBulletR = ImageUtil.rotateImage(INSTANCE.badBulletL,180);
            INSTANCE.badBulletU = ImageUtil.rotateImage(INSTANCE.badBulletL,90);
            INSTANCE.badBulletD = ImageUtil.rotateImage(INSTANCE.badBulletL,270);
            INSTANCE.goodTankU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/GoodTank1.png"));
            INSTANCE.goodTankR = ImageUtil.rotateImage(INSTANCE.goodTankU,90);
            INSTANCE.goodTankL = ImageUtil.rotateImage(INSTANCE.goodTankU,-90);
            INSTANCE.goodTankD = ImageUtil.rotateImage(INSTANCE.goodTankU,180);
            INSTANCE.goodBulletU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/bulletU.png"));
            INSTANCE.goodBulletR = ImageUtil.rotateImage(INSTANCE.goodBulletU,90);
            INSTANCE.goodBulletL = ImageUtil.rotateImage(INSTANCE.goodBulletU,-90);
            INSTANCE.goodBulletD = ImageUtil.rotateImage(INSTANCE.goodBulletU,180);
            for (int i = 0; i < 16; i++) {
                INSTANCE.goodExplodes[i] = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/e" + (i + 1) + ".gif"));
            }
            for (int i = 0; i < 11; i++) {
                INSTANCE.badExplodes[i] = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/" + i + ".gif"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ResourceMgr getInstance() {
        return INSTANCE;
    }

    public BufferedImage getBadTankL() {
        return badTankL;
    }

    public BufferedImage getBadTankR() {
        return badTankR;
    }

    public BufferedImage getBadTankU() {
        return badTankU;
    }

    public BufferedImage getBadTankD() {
        return badTankD;
    }

    public BufferedImage getBadBulletL() {
        return badBulletL;
    }

    public BufferedImage getBadBulletR() {
        return badBulletR;
    }

    public BufferedImage getBadBulletD() {
        return badBulletD;
    }

    public BufferedImage getBadBulletU() {
        return badBulletU;
    }

    public BufferedImage getGoodTankL() {
        return goodTankL;
    }

    public BufferedImage getGoodTankR() {
        return goodTankR;
    }

    public BufferedImage getGoodTankU() {
        return goodTankU;
    }

    public BufferedImage getGoodTankD() {
        return goodTankD;
    }

    public BufferedImage getGoodBulletL() {
        return goodBulletL;
    }

    public BufferedImage getGoodBulletR() {
        return goodBulletR;
    }

    public BufferedImage getGoodBulletD() {
        return goodBulletD;
    }

    public BufferedImage getGoodBulletU() {
        return goodBulletU;
    }

    public BufferedImage[] getGoodExplodes() {
        return goodExplodes;
    }

    public BufferedImage[] getBadExplodes() {
        return badExplodes;
    }
}
