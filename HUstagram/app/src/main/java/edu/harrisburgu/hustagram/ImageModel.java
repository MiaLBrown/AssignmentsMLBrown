package edu.harrisburgu.hustagram;

public class ImageModel {
    private String imageName;
    private String comment;
    private String dateTime;

    public ImageModel(String imageName, String comment, String dateTime) {
        this.imageName = imageName;
        this.comment = comment;
        this.dateTime = dateTime;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }
}
