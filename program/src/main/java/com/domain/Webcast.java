package com.domain;

import java.time.LocalDate;

public class Webcast {

    private String webcastTitle;
    private String webcastDescription;
    private String speakerName;
    private String speakerOrganisation;
    private String duration;
    private LocalDate publicationDate;
    private String URL;

    public Webcast(String webcastTitle, String webcastDescription, String speakerName, String speakerOrganisation,
            String duration, LocalDate publicationDate, String URL) {
        this.webcastTitle = webcastTitle;
        this.webcastDescription = webcastDescription;
        this.speakerName = speakerName;
        this.speakerOrganisation = speakerOrganisation;
        this.duration = duration;
        this.publicationDate = publicationDate;
        this.URL = URL;
    }

    // Logica
    public int watchWebcast() {
        return 0;
    }

    // Getters en Setters
    public String getWebcastTitle() {
        return this.webcastTitle;
    }

    public void setWebcastTitle(String webcastTitle) {
        this.webcastTitle = webcastTitle;
    }

    public String getWebcastDescription() {
        return this.webcastDescription;
    }

    public void setWebcastDescription(String webcastDescription) {
        this.webcastDescription = webcastDescription;
    }

    public String getSpeakerName() {
        return this.speakerName;
    }

    public void setSpeakerName(String speakerName) {
        this.speakerName = speakerName;
    }

    public String getSpeakerOrganisation() {
        return this.speakerOrganisation;
    }

    public void setSpeakerOrganisation(String speakerOrganisation) {
        this.speakerOrganisation = speakerOrganisation;
    }

    public String getDuration() {
        return this.duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public LocalDate getPublicationDate() {
        return this.publicationDate;
    }

    public void setPublicationDate(LocalDate publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getURL() {
        return this.URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

}
