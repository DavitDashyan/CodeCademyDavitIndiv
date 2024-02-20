package com.domain;

public class ParticipantProgress {
    
    private Registration registration;
    private ContentItem contentItem;
    private AmountSeen amountSeen;

    public ParticipantProgress(Registration registration, ContentItem contentItem, AmountSeen amountSeen) {
        this.registration = registration;
        this.contentItem = contentItem;
        this.amountSeen = amountSeen;
    }

    public String getEmailAddress() {
        return registration.getEmail();
    }

    public String getCourseName() {
        return registration.getCourseName();
    }

    public String getModuleTitle() {
        return contentItem.getModuleTitle();
    }

    public String getModuleVersion() {
        return contentItem.getModuleVersion();
    }

    public String getWebcastTitle() {
        return contentItem.getWebcastTitle();
    }

    public Double getViewPercentage() {
        return amountSeen.getViewPercentage();
    }
}
