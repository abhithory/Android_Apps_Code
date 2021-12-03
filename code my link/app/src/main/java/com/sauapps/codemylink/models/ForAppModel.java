package com.sauapps.codemylink.models;

public class ForAppModel {

    private String url;
    private String geturl;
    private String seturl;
    private String updateurl;
    private String deleteurl;

    public ForAppModel(String url, String geturl, String seturl, String updateurl, String deleteurl) {
        this.url = url;
        this.geturl = geturl;
        this.seturl = seturl;
        this.updateurl = updateurl;
        this.deleteurl = deleteurl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getGeturl() {
        return geturl;
    }

    public void setGeturl(String geturl) {
        this.geturl = geturl;
    }

    public String getSeturl() {
        return seturl;
    }

    public void setSeturl(String seturl) {
        this.seturl = seturl;
    }

    public String getDeleteurl() {
        return deleteurl;
    }

    public void setDeleteurl(String deleteurl) {
        this.deleteurl = deleteurl;
    }


    public String getUpdateurl() {
        return updateurl;
    }

    public void setUpdateurl(String updateurl) {
        this.updateurl = updateurl;
    }


}
