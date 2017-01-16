package com.six.history.bean;

/**
 * Created by Administrator on 2016/12/16/016.
 */

public class CardImage
{
    private int imageid;

    @Override
    public String toString() {
        return "CardImage{" +
                "imageid=" + imageid +
                '}';
    }

    public int getImageid() {
        return imageid;
    }

    public void setImageid(int imageid) {
        this.imageid = imageid;
    }

    public CardImage(int imageid) {

        this.imageid = imageid;
    }
}
