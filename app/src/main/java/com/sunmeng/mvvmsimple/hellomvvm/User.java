package com.sunmeng.mvvmsimple.hellomvvm;

import java.io.Serializable;

/**
 * Created by sunmeng on 2017/11/7.
 * Email:sunmeng995@gmail.com
 * Description:
 */

public class User implements Serializable {
    private int id;
    private String name;

    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
