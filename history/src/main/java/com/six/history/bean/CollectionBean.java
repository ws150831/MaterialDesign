package com.six.history.bean;


public class CollectionBean {
    private String history_id;
    private String data;
    private String title;
    private String url;

    @Override
    public String toString() {
        return "CollectionBean{" +
                "history_id='" + history_id + '\'' +
                ", data='" + data + '\'' +
                ", title='" + title + '\'' +
                ", url='" + url + '\'' +
                '}';
    }

    public String getHistory_id() {
        return history_id;
    }

    public void setHistory_id(String history_id) {
        this.history_id = history_id;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


}
