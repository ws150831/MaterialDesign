package com.six.history.bean;

/**
 * autour: 王广宽
 * date: 2016/12/21 19:11
 * update: 2016/12/21
 * explain:RventBus回传用的bean,日历和历史的今天回传
 */
public class RiQiBean {
    private int nian;//属性
    private int yue;//属性
    private int ri;//属性

    public RiQiBean(int nian, int yue, int ri)
    {
        this.nian = nian;
        this.yue = yue;
        this.ri = ri;
    }

    public int getNian() {
        return nian;
    }

    public void setNian(int nian) {
        this.nian = nian;
    }

    public int getYue() {
        return yue;
    }

    public void setYue(int yue) {
        this.yue = yue;
    }

    public int getRi() {
        return ri;
    }

    public void setRi(int ri) {
        this.ri = ri;
    }
}
