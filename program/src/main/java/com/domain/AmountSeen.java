package com.domain;

public class AmountSeen {
    
    private String emailAddress;
    private String contentItemID;
    private double viewPercentage;

    public AmountSeen(String emailAddress, String contentItemID, double viewPercentage) {
        this.emailAddress = emailAddress;
        this.contentItemID = contentItemID;
        this.viewPercentage = viewPercentage;
    }

    public AmountSeen(String emailAddress, Double viewPercentage) {
        this.emailAddress = emailAddress;
        this.viewPercentage = viewPercentage;
    }

    public String getEmailAddress() {
        return this.emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    
    public String getContentItemID() {
        return this.contentItemID;
    }

    public void setContentItemID(String ContentItemID) {
        this.contentItemID = ContentItemID;
    }

    public double getViewPercentage() {
        return this.viewPercentage;
    }

    public void setViewPercentage(double viewPercentage) {
        this.viewPercentage = viewPercentage;
    }

}
