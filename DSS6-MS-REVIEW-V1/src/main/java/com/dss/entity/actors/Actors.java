/**
 * @author Glen Mark T Anduiza
 * @version 1.0
 * @since 10/31/2022
 */

package com.dss.entity.actors;

import com.dss.entity.movie.DssMovie;
import lombok.Getter;

import javax.persistence.*;
import java.util.Date;

/**
 * This is an Entity Class for Actors
 */


@Entity
@Table(name = "DSS_ACTORS")
@Getter
public class Actors {

    @Id
    @Column(name = "ACTOR_ID", length = 10, nullable = false)
    private String actorId;

    @Column(name = "MOVIE_ID", length = 10, nullable = false)
    private String movieId;

    @Column(name = "FIRST_NAME", nullable = false)
    private String firstName;

    @Column(name = "LAST_NAME", nullable = false)
    private String lastName;

    @Column(name = "GENDER", nullable = false)
    private String gender;

    @Column(name = "AGE", nullable = false)
    private int age;

    @Column(name = "MOVIE_CAST", nullable = false)
    private String movieCast;

    @Column(name = "ROLE", nullable = false)
    private String role;

    @Column(name = "CREATION_DATE", nullable = false)
    private Date creationDate;

    @Column(name = "CREATED_BY", length = 100, nullable = false)
    private String createdBy;

    @Column(name = "LAST_MODIFICATION_DATE")
    private Date lastModificationDate;

    @Column(name = "LAST_MODIFIED_BY", length = 100)
    private String lastModifiedBy;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private DssMovie dss;
}
