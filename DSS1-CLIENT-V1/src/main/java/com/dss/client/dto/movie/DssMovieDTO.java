/**
 * @author Glen Mark T Anduiza
 * @version 1.0
 * @since 10/31/2022
 */

package com.dss.client.dto.movie;

import com.dss.client.dto.actors.ActorsDTO;
import com.dss.client.dto.reviews.ReviewsDTO;
import com.dss.client.dto.image.ImagesDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.util.Date;
import java.util.List;

/**
 * This class is a Data Transfer Object for DssMovieDTO
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
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
    private List<ImagesDTO> image;
}
