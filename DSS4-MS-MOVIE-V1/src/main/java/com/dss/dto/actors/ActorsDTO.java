/**
 * @author Glen Mark T Anduiza
 * @version 1.0
 * @since 10/31/2022
 */

package com.dss.dto.actors;

import lombok.*;

import java.util.Date;

/**
 * This class is a Data Transfer Object for ActorsDTO
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ActorsDTO {

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
}
