package com.zhangwenbo.tank.mor;

import com.zhangwenbo.tank.bean.GameObject;
import com.zhangwenbo.tank.mgr.PropertyMgr;

import java.util.LinkedList;
import java.util.List;


public class ChainCollider implements Collider {
    private List<Collider> colliderList = new LinkedList<Collider>();

    public ChainCollider() {
        PropertyMgr instance = PropertyMgr.getInstance();
        String colliders = instance.getString("Collider");
        String[] split = colliders.split(",");
        for (String colliderName : split) {
            try {
                Collider collider = (Collider) Class.forName(colliderName).newInstance();
                colliderList.add(collider);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean collideWith(GameObject o1, GameObject o2) {
        for (Collider collider : colliderList) {
            if (!collider.collideWith(o1, o2)) {
                break;
            }
        }
        return false;
    }
}
