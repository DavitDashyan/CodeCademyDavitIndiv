package com.domain;

public class ContentItem {
    
    private String contentItemID;
    private String publicationDate;
    private String courseName;
    private String status;
    private String moduleTitle;
    private String moduleVersion;
    private String webcastTitle;

    // Constructors, getters, and setters

    public ContentItem(String contentItemID, String publicationDate, String courseName, String status, String moduleTitle, String moduleVersion, String webcastTitle) {
        this.contentItemID = contentItemID;
        this.publicationDate = publicationDate;
        this.courseName = courseName;
        this.status = status;
        this.moduleTitle = moduleTitle;
        this.moduleVersion = moduleVersion;
        this.webcastTitle = webcastTitle;
    }

    public ContentItem(String moduleTitle, String moduleVersion, String webcastTitle) {
        this.moduleTitle = moduleTitle;
        this.moduleVersion = moduleVersion;
        this.webcastTitle = webcastTitle;
    }

    public String getContentItemID() {
        return contentItemID;
    }

    public void setContentItemID(String contentItemID) {
        this.contentItemID = contentItemID;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getModuleTitle() {
        return moduleTitle;
    }

    public void setModuleTitle(String moduleTitle) {
        this.moduleTitle = moduleTitle;
    }

    public String getModuleVersion() {
        return moduleVersion;
    }

    public void setModuleVersion(String moduleVersion) {
        this.moduleVersion = moduleVersion;
    }

    public String getWebcastTitle() {
        return webcastTitle;
    }

    public void setWebcastTitle(String webcastTitle) {
        this.webcastTitle = webcastTitle;
    }

}
