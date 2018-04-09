package com.kuanguang.huiyun.model;

/**
 * Created by
 */

public class TestModel {

    public String title;
    public boolean isCheck;
    public boolean isExplan;
    public int statue;

    public TestModel add(String title,boolean isCheck){
        TestModel mo = new TestModel();
        mo.title = title;
        mo.isCheck = isCheck;
        return mo;
    }

}
