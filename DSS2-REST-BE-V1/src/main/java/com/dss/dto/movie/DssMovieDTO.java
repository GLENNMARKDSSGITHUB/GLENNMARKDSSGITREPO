/**
 * @author Glen Mark T Anduiza
 * @version 1.0
 * @since 10/31/2022
 */

package com.dss.dto.movie;

import com.dss.dto.reviews.ReviewsDTO;
import com.dss.dto.actors.ActorsDTO;
import com.dss.entity.image.Images;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;
import java.util.List;

/**
 * This class is a Data Transfer Object for DssMovieDTO
 */

public class DssMovieDTO {

    private String movieId;
    private String movieTitle;
    private String year;
    private String writers;
    private String directedBy;
    private String producedBy;
    private String musicBy;
    private String duration;
    private double movieCost;
    private String category;
    private String country;
    private String language;
    private Date creationDate;
    private String createdBy;
    private Date lastModificationDate;
    private String lastModifiedBy;

    private List<ActorsDTO> movieActors;
    private List<ReviewsDTO> movieRatings;
    @JsonIgnore
    private List<Images> image;

    public DssMovieDTO() {
    }

    public DssMovieDTO(String movieId, String movieTitle, String year, String writers, String directedBy, String producedBy, String musicBy, String duration, double movieCost, String category, String country, String language, Date creationDate, String createdBy, Date lastModificationDate, String lastModifiedBy, List<ActorsDTO> movieActors, List<ReviewsDTO> movieRatings, List<Images> image) {
        this.movieId = movieId;
        this.movieTitle = movieTitle;
        this.year = year;
        this.writers = writers;
        this.directedBy = directedBy;
        this.producedBy = producedBy;
        this.musicBy = musicBy;
        this.duration = duration;
        this.movieCost = movieCost;
        this.category = category;
        this.country = country;
        this.language = language;
        this.creationDate = creationDate;
        this.createdBy = createdBy;
        this.lastModificationDate = lastModificationDate;
        this.lastModifiedBy = lastModifiedBy;
        this.movieActors = movieActors;
        this.movieRatings = movieRatings;
        this.image = image;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getWriters() {
        return writers;
    }

    public void setWriters(String writers) {
        this.writers = writers;
    }

    public String getDirectedBy() {
        return directedBy;
    }

    public void setDirectedBy(String directedBy) {
        this.directedBy = directedBy;
    }

    public String getProducedBy() {
        return producedBy;
    }

    public void setProducedBy(String producedBy) {
        this.producedBy = producedBy;
    }

    public String getMusicBy() {
        return musicBy;
    }

    public void setMusicBy(String musicBy) {
        this.musicBy = musicBy;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public double getMovieCost() {
        return movieCost;
    }

    public void setMovieCost(double movieCost) {
        this.movieCost = movieCost;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
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

    public List<ActorsDTO> getMovieActors() {
        return movieActors;
    }

    public void setMovieActors(List<ActorsDTO> movieActors) {
        this.movieActors = movieActors;
    }

    public List<ReviewsDTO> getMovieRatings() {
        return movieRatings;
    }

    public void setMovieRatings(List<ReviewsDTO> movieRatings) {
        this.movieRatings = movieRatings;
    }

    public List<Images> getImage() {
        return image;
    }

    public void setImage(List<Images> image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "DssMovieDTO{" +
                "movieId='" + movieId + '\'' +
                ", movieTitle='" + movieTitle + '\'' +
                ", year='" + year + '\'' +
                ", writers='" + writers + '\'' +
                ", directedBy='" + directedBy + '\'' +
                ", producedBy='" + producedBy + '\'' +
                ", musicBy='" + musicBy + '\'' +
                ", duration='" + duration + '\'' +
                ", movieCost=" + movieCost +
                ", category='" + category + '\'' +
                ", country='" + country + '\'' +
                ", language='" + language + '\'' +
                ", creationDate=" + creationDate +
                ", createdBy='" + createdBy + '\'' +
                ", lastModificationDate=" + lastModificationDate +
                ", lastModifiedBy='" + lastModifiedBy + '\'' +
                ", movieActors=" + movieActors +
                ", movieRatings=" + movieRatings +
                ", image=" + image +
                '}';
    }
}
