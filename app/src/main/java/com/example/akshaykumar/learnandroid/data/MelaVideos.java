package com.example.akshaykumar.learnandroid.data;

public class MelaVideos {
    private int id;
    private String heading, description, imageUrl, timePosted, videoUrl;

    public MelaVideos() {
    }

    public MelaVideos(int id, String heading, String description, String imageUrl, String timePosted, String videoUrl) {
        this.id = id;
        this.heading = heading;
        this.description = description;
        this.imageUrl = imageUrl;
        this.timePosted = timePosted;
        this.videoUrl = videoUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTimePosted() {
        return timePosted;
    }

    public void setTimePosted(String timePosted) {
        this.timePosted = timePosted;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }
}