package com.sauapps.codemylink.models;

public class GeneratedLinkModel {

    private String link_status;
    private String code;
    private String link;
    private String link_title;
    private String link_des;

    public GeneratedLinkModel(String link_status, String code, String link, String link_title, String link_des) {
        this.link_status = link_status;
        this.code = code;
        this.link = link;
        this.link_title = link_title;
        this.link_des = link_des;
    }

    public String getLink_status() {
        return link_status;
    }

    public void setLink_status(String link_status) {
        this.link_status = link_status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
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
}
