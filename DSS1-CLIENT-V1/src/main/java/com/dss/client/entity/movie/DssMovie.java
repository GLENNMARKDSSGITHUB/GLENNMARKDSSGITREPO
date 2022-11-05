/**
 * @author Glen Mark T Anduiza
 * @version 1.0
 * @since 10/31/2022
 */

package com.dss.client.entity.movie;

import com.dss.client.entity.actors.Actors;
import com.dss.client.entity.image.Images;
import com.dss.client.entity.reviews.Reviews;
import lombok.*;

import java.util.Date;
import java.util.List;

/**
 * This is an Entity Class for DssMovie
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DssMovie {

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
    private List<Actors> movieActors;
    private List<Reviews> movieReviews;

    private List<Images> image;

}
