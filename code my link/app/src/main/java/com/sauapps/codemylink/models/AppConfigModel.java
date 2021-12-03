package com.sauapps.codemylink.models;

public class AppConfigModel {

    private String privacy_policy;
    private String term_and_condition;
    private String about_us;
    private String share_text;
    private String telegram_channel;
    private String contact_us;

    public AppConfigModel(String privacy_policy, String term_and_condition, String about_us, String share_text, String telegram_channel, String contact_us) {
        this.privacy_policy = privacy_policy;
        this.term_and_condition = term_and_condition;
        this.about_us = about_us;
        this.share_text = share_text;
        this.telegram_channel = telegram_channel;
        this.contact_us = contact_us;
    }

    public String getPrivacy_policy() {
        return privacy_policy;
    }

    public void setPrivacy_policy(String privacy_policy) {
        this.privacy_policy = privacy_policy;
    }

    public String getTerm_and_condition() {
        return term_and_condition;
    }

    public void setTerm_and_condition(String term_and_condition) {
        this.term_and_condition = term_and_condition;
    }

    public String getAbout_us() {
        return about_us;
    }

    public void setAbout_us(String about_us) {
        this.about_us = about_us;
    }

    public String getShare_text() {
        return share_text;
    }

    public void setShare_text(String share_text) {
        this.share_text = share_text;
    }

    public String getTelegram_channel() {
        return telegram_channel;
    }

    public void setTelegram_channel(String telegram_channel) {
        this.telegram_channel = telegram_channel;
    }

    public String getContact_us() {
        return contact_us;
    }

    public void setContact_us(String contact_us) {
        this.contact_us = contact_us;
    }
}
