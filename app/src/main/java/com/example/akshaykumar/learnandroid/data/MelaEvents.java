package com.example.akshaykumar.learnandroid.data;

/**
 * Created by akshaykumar on 9/21/16.
 */
public class MelaEvents {

    private int id;
    private String name, description, userImage, timePosted, eventImage;

    public MelaEvents() {
    }

    public MelaEvents(int id, String name, String description, String userImage, String timePosted, String eventImage) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.userImage = userImage;
        this.timePosted = timePosted;
        this.eventImage = eventImage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

    public String getTimePosted() {
        return timePosted;
    }

    public void setTimePosted(String timePosted) {
        this.timePosted = timePosted;
    }

    public String getEventImage() {
        return eventImage;
    }

    public void setEventImage(String eventImage) {
        this.eventImage = eventImage;
    }
}
