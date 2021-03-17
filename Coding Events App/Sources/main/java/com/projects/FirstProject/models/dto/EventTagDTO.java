package com.projects.FirstProject.models.dto;

import com.projects.FirstProject.models.Event;
import com.projects.FirstProject.models.Tag;
import com.sun.istack.NotNull;

public class EventTagDTO { //data transfer object. wrap together an event and a tag object

    @NotNull
    private Event event;

    @NotNull
    private Tag tag;

    public EventTagDTO(){}

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }
}
