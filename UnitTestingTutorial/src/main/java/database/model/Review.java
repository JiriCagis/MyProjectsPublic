package database.model;

import java.io.Serializable;

/**
 * This entity, which describe a critique of the recording, is written by a Reviewer.
 */
public class Review extends Identify implements Serializable {
    private Reviewer reviewer;
    private Recording recording;
    private String rating;
    private String review;

    /** CONSTRUCTORS */
    public Review(){}

    public Review(Reviewer reviewer, Recording recording, String review, String rating) {
        this.id = null;
        this.reviewer = reviewer;
        this.recording = recording;
        this.review = review;
        this.rating = rating;
    }

    public Review(Integer id,Reviewer reviewer, Recording recording, String review, String rating) {
        this.id = id;
        this.reviewer = reviewer;
        this.recording = recording;
        this.review = review;
        this.rating = rating;
    }

    /** GETTERS AND SETTERS */
    public Reviewer getReviewer() {
        return reviewer;
    }

    public void setReviewer(Reviewer reviewer) {
        this.reviewer = reviewer;
    }

    public Recording getRecording() {
        return recording;
    }

    public void setRecording(Recording recording) {
        this.recording = recording;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }
}
