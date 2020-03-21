package com.zhangwenbo.tank;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        TankFrame tf = TankFrame.getInstance();
        while(true){
            Thread.sleep(50);
            tf.repaint();
        }

    }
}
