package com.zhangwenbo.tank.mgr;

import java.io.IOException;
import java.util.Properties;

public class PropertyMgr {
    private volatile static PropertyMgr INSTANCE = null;
    private static Properties props = new Properties();

    private PropertyMgr() {
    }

    public Integer getInteger(String key) {
        if (props == null) {
            return null;
        } else {
            return Integer.parseInt((String) props.get(key));
        }
    }

    public String getString(String key) {
        if (props == null) {
            return null;
        } else {
            return (String) props.get(key);
        }
    }

    public static PropertyMgr getInstance() {
        if (INSTANCE == null) {
            synchronized (PropertyMgr.class) {
                try {
                    if (INSTANCE == null) {
                        INSTANCE = new PropertyMgr();
                        INSTANCE.props.load(PropertyMgr.class.getClassLoader().getResourceAsStream("config/config"));
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return INSTANCE;


    }


}
