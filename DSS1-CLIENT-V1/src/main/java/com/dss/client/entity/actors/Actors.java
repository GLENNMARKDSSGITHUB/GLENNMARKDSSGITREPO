/**
 * @author Glen Mark T Anduiza
 * @version 1.0
 * @since 10/31/2022
 */

package com.dss.client.entity.actors;

import com.dss.client.entity.movie.DssMovie;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * This is an Entity Class for Actors
 */

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Actors {

    private String actorId;
    private String movieId;
    private String firstName;
    private String lastName;
    private String gender;
    private int age;
    private String movieCast;
    private String role;
    private Date creationDate;
    private String createdBy;
    private Date lastModificationDate;
    private String lastModifiedBy;
    private DssMovie dss;

}
