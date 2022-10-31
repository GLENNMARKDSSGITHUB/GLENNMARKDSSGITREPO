package com.dss.dto.reviews;

import java.util.Date;

public class ReviewsDTO {

    private String reviewId;

    private String movieId;
    private int rate;
    private String reviewHeadline;
    private String reviewContent;
    private Date creationDate;
    private String createdBy;
    private Date lastModificationDate;
    private String lastModifiedBy;

    public ReviewsDTO() {
    }

    public ReviewsDTO(String reviewId, String movieId, int rate, String reviewHeadline, String reviewContent, Date creationDate, String createdBy, Date lastModificationDate, String lastModifiedBy) {
        this.reviewId = reviewId;
        this.movieId = movieId;
        this.rate = rate;
        this.reviewHeadline = reviewHeadline;
        this.reviewContent = reviewContent;
        this.creationDate = creationDate;
        this.createdBy = createdBy;
        this.lastModificationDate = lastModificationDate;
        this.lastModifiedBy = lastModifiedBy;
    }

    public String getReviewId() {
        return reviewId;
    }

    public void setReviewId(String reviewId) {
        this.reviewId = reviewId;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public String getReviewHeadline() {
        return reviewHeadline;
    }

    public void setReviewHeadline(String reviewHeadline) {
        this.reviewHeadline = reviewHeadline;
    }

    public String getReviewContent() {
        return reviewContent;
    }

    public void setReviewContent(String reviewContent) {
        this.reviewContent = reviewContent;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getLastModificationDate() {
        return lastModificationDate;
    }

    public void setLastModificationDate(Date lastModificationDate) {
        this.lastModificationDate = lastModificationDate;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    @Override
    public String toString() {
        return "ReviewsDTO{" +
                "reviewId='" + reviewId + '\'' +
                ", movieId='" + movieId + '\'' +
                ", rate=" + rate +
                ", reviewHeadline='" + reviewHeadline + '\'' +
                ", reviewContent='" + reviewContent + '\'' +
                ", creationDate=" + creationDate +
                ", createdBy='" + createdBy + '\'' +
                ", lastModificationDate=" + lastModificationDate +
                ", lastModifiedBy='" + lastModifiedBy + '\'' +
                '}';
    }
}
