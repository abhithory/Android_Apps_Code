package com.sauapps.codemylink.models;

public class UserMyLinksModel {

    private String link_id;
    private String link;
    private String link_status;
    private String link_code;
    private String link_title;
    private String link_des;
    private String link_date;
    private String link_views;

    public UserMyLinksModel(String link_id, String link, String link_status,
                            String link_code, String link_title, String link_des, String link_date, String link_views) {
        this.link_id = link_id;
        this.link = link;
        this.link_status = link_status;
        this.link_code = link_code;
        this.link_title = link_title;
        this.link_des = link_des;
        this.link_date = link_date;
        this.link_views = link_views;
    }

    public String getLink_status() {
        return link_status;
    }

    public void setLink_status(String link_status) {
        this.link_status = link_status;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getLink_id() {
        return link_id;
    }

    public void setLink_id(String link_id) {
        this.link_id = link_id;
    }

    public String getLink_code() {
        return link_code;
    }

    public void setLink_code(String link_code) {
        this.link_code = link_code;
    }

    public String getLink_title() {
        return link_title;
    }

    public void setLink_title(String link_title) {
        this.link_title = link_title;
    }

    public String getLink_des() {
        return link_des;
    }

    public void setLink_des(String link_des) {
        this.link_des = link_des;
    }

    public String getLink_date() {
        return link_date;
    }

    public void setLink_date(String link_date) {
        this.link_date = link_date;
    }

    public String getLink_views() {
        return link_views;
    }

    public void setLink_views(String link_views) {
        this.link_views = link_views;
    }
}
