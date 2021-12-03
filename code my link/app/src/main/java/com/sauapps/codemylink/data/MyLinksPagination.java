package com.sauapps.codemylink.data;

public class MyLinksPagination {

    private String currectpageCount;
    private String totalPageCount;

    private String nextPageUrl;
    private String previousPageUrl;
    private String firstpageUrl;
    private String lastpageurl;


    public MyLinksPagination(String currectpageCount, String totalPageCount, String nextPageUrl, String previousPageUrl, String firstpageUrl, String lastpageurl) {
        this.currectpageCount = currectpageCount;
        this.totalPageCount = totalPageCount;
        this.nextPageUrl = nextPageUrl;
        this.previousPageUrl = previousPageUrl;
        this.firstpageUrl = firstpageUrl;
        this.lastpageurl = lastpageurl;
    }

    public String getCurrectpageCount() {
        return currectpageCount;
    }

    public void setCurrectpageCount(String currectpageCount) {
        this.currectpageCount = currectpageCount;
    }

    public String getTotalPageCount() {
        return totalPageCount;
    }

    public void setTotalPageCount(String totalPageCount) {
        this.totalPageCount = totalPageCount;
    }

    public String getNextPageUrl() {
        return nextPageUrl;
    }

    public void setNextPageUrl(String nextPageUrl) {
        this.nextPageUrl = nextPageUrl;
    }

    public String getPreviousPageUrl() {
        return previousPageUrl;
    }

    public void setPreviousPageUrl(String previousPageUrl) {
        this.previousPageUrl = previousPageUrl;
    }

    public String getFirstpageUrl() {
        return firstpageUrl;
    }

    public void setFirstpageUrl(String firstpageUrl) {
        this.firstpageUrl = firstpageUrl;
    }

    public String getLastpageurl() {
        return lastpageurl;
    }

    public void setLastpageurl(String lastpageurl) {
        this.lastpageurl = lastpageurl;
    }
}


