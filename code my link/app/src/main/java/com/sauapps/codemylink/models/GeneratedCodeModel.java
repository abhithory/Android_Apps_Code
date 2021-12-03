package com.sauapps.codemylink.models;

public class GeneratedCodeModel {

    private String code_generated_status;
    private String code;
    private String link;
    private String link_title;
    private String link_des;


    public GeneratedCodeModel(String code_generated_status, String code, String link, String link_title, String link_des) {

        this.code_generated_status = code_generated_status;
        this.code = code;
        this.link = link;
        this.link_title = link_title;
        this.link_des = link_des;
    }



    public String getCode_generated_status() {
        return code_generated_status;
    }

    public void setCode_generated_status(String code_generated_status) {
        this.code_generated_status = code_generated_status;
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
