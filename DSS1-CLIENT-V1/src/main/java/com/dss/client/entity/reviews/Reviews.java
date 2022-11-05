/**
 * @author Glen Mark T Anduiza
 * @version 1.0
 * @since 10/31/2022
 */

package com.dss.client.entity.reviews;

import com.dss.client.entity.movie.DssMovie;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * This is an Entity Class for Reviews
 */

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Reviews {

    private String reviewId;
    private String movieId;
    private int rate;
    private String reviewHeadline;
    private String reviewContent;
    private Date creationDate;
    private String createdBy;
    private Date lastModificationDate;
    private String lastModifiedBy;
    private DssMovie dss;
}
