package com.projects.FirstProject.models;

import com.projects.FirstProject.models.AbstractEntity;
import com.projects.FirstProject.models.Event;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;


@Entity
public class EventCategory extends AbstractEntity {

    private String name;

    @OneToMany(mappedBy = "category")
    private final List<Event> events = new ArrayList<>();

    public EventCategory(String name) {
        this.name = name;
    }

    public EventCategory() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Event> getEvents() {
        return events;
    }
}