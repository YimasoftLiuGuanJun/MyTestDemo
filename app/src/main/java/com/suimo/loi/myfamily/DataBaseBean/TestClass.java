package com.suimo.loi.myfamily.DataBaseBean;

import org.litepal.annotation.Column;
import org.litepal.crud.LitePalSupport;

/**
 * Author：loi on 2019-01-21 10:58
 * Emil：894900183@qq.com
 * - -
 */
public class TestClass extends LitePalSupport {
    @Column(unique = true, defaultValue = "unknown")
    private String str;

    public TestClass(String str) {
        this.str = str;
    }
}
