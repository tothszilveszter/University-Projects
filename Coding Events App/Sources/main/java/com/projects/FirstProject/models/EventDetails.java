package com.projects.FirstProject.models;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class EventDetails extends AbstractEntity{

    private String description;
    private String contactEmail;

//    @OneToOne(mappedBy = "eventDetails")
//    private Event event;     //inverse relation

    public EventDetails(String description, String contactEmail) {
        this.description = description;
        this.contactEmail = contactEmail;
    }

    public EventDetails(){}

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }
}
