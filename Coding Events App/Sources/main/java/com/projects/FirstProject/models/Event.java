package com.projects.FirstProject.models;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Event extends AbstractEntity {


    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    private EventDetails eventDetails;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private EventCategory category;

    @ManyToMany
    private final List<Tag> tags = new ArrayList<>();

    public Event(String name, EventDetails eventDetails, EventCategory category) {
        this.name = name;
        this.category = category;
        this.eventDetails = eventDetails;
    }

    public Event() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public EventCategory getCategory() {
        return category;
    }

    public void setCategory(EventCategory category) {
        this.category = category;
    }

    public EventDetails getEventDetails() {
        return eventDetails;
    }

    public void setEventDetails(EventDetails eventDetails) {
        this.eventDetails = eventDetails;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void addTags(Tag tag){
        this.tags.add(tag);
    }

    @Override
    public String toString() {
        return name;
    }
}
