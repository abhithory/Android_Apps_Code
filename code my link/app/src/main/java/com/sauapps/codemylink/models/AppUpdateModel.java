package com.sauapps.codemylink.models;

public class AppUpdateModel {

    private String appVersion;
    private String isUpdate;
    private String updateText;
    private String appLink;

    public AppUpdateModel(String appVersion, String isUpdate, String updateText, String appLink) {
        this.appVersion = appVersion;
        this.isUpdate = isUpdate;
        this.updateText = updateText;
        this.appLink = appLink;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public String getIsUpdate() {
        return isUpdate;
    }

    public void setIsUpdate(String isUpdate) {
        this.isUpdate = isUpdate;
    }

    public String getUpdateText() {
        return updateText;
    }

    public void setUpdateText(String updateText) {
        this.updateText = updateText;
    }

    public String getAppLink() {
        return appLink;
    }

    public void setAppLink(String appLink) {
        this.appLink = appLink;
    }
}
