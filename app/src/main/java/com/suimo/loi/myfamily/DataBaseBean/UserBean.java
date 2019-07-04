package com.suimo.loi.myfamily.DataBaseBean;

import org.litepal.annotation.Column;
import org.litepal.crud.LitePalSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * Author：loi on 2019-01-21 10:36
 * Emil：894900183@qq.com
 * - -
 */
public class UserBean extends LitePalSupport {
    @Column(unique = true, defaultValue = "unknown")
    private String name;

    private float price;

    private byte[] cover;

    private List<TestClass> testClasses = new ArrayList<TestClass>();

    public List<TestClass> getTestClasses() {
        return testClasses;
    }

    public void setTestClasses(List<TestClass> testClasses) {
        this.testClasses = testClasses;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public byte[] getCover() {
        return cover;
    }

    public void setCover(byte[] cover) {
        this.cover = cover;
    }
//    private List<Song> songs = new ArrayList<Song>();
}
