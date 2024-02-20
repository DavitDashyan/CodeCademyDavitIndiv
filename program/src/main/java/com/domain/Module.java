package com.domain;

public class Module {
    
    private String moduleTitle;
    private String version;
    private String moduleDescription;
    private String contactPersonName;
    private String contactPersonEmail;
    private String courseName;
    private double averageProgress;

    public Module(String moduleTitle, String version, String moduleDescription, 
                  String contactPersonName, String contactPersonEmail, 
                  String courseName) {
        this.moduleTitle = moduleTitle;
        this.version = version;
        this.moduleDescription = moduleDescription;
        this.contactPersonName = contactPersonName;
        this.contactPersonEmail = contactPersonEmail;
        this.courseName = courseName;
    }

    public Module(String moduleTitle, String version, String moduleDescription, 
                  String contactPersonName, String contactPersonEmail, 
                  String courseName, double averageProgress) {
        this.moduleTitle = moduleTitle;
        this.version = version;
        this.moduleDescription = moduleDescription;
        this.contactPersonName = contactPersonName;
        this.contactPersonEmail = contactPersonEmail;
        this.courseName = courseName;
        this.averageProgress = averageProgress;
    }

    public String getModuleTitle() {
        return moduleTitle;
    }

    public void setModuleTitle(String moduleTitle) {
        this.moduleTitle = moduleTitle;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getModuleDescription() {
        return moduleDescription;
    }

    public void setModuleDescription(String moduleDescription) {
        this.moduleDescription = moduleDescription;
    }

    public String getContactPersonName() {
        return contactPersonName;
    }

    public void setContactPersonName(String contactPersonName) {
        this.contactPersonName = contactPersonName;
    }

    public String getContactPersonEmail() {
        return contactPersonEmail;
    }

    public void setContactPersonEmail(String contactPersonEmail) {
        this.contactPersonEmail = contactPersonEmail;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public double getAverageProgress() {
        return averageProgress;
    }

    public void setAverageProgress(double averageProgress) {
        this.averageProgress = averageProgress;
    }
}
