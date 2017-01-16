package com.six.history.dao;

/**
 * Effect:数据库的Bean类
 * autour: 张玉杰
 * date: 2016/12/19 20:48
 * update: 2016/12/19
 */

public class ScUser {
    private int id;
    private String data;
    private String title;
    private String image;
    private String e_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getE_id() {
        return e_id;
    }

    public void setE_id(String e_id) {
        this.e_id = e_id;
    }

    public ScUser(int id, String data, String title, String image, String e_id) {
        this.id = id;
        this.data = data;
        this.title = title;
        this.image = image;
        this.e_id = e_id;
    }

    public ScUser() {
        super();
    }

    @Override
    public String toString() {
        return "ScUser{" +
                "id=" + id +
                ", data='" + data + '\'' +
                ", title='" + title + '\'' +
                ", image='" + image + '\'' +
                ", e_id='" + e_id + '\'' +
                '}';
    }
}
