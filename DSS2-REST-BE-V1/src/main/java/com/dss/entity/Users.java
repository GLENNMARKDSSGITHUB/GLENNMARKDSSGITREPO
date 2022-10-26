package com.dss.entity;

import lombok.*;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "DSS_USERS")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Users {

    @Id
    @Column(name = "DSS_USER_ID", length = 100, nullable = false)
    private String dssUserId;

    @Column(name = "FIRST_NAME", length = 100, nullable = false)
    private String firstName;

    @Column(name = "LAST_NAME", length = 100, nullable = false)
    private String lastName;

    @Column(name = "EMAIL", length = 100, nullable = false)
    private String email;

    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Column(name = "STATUS", length = 15, nullable = false)
    private String status;

    @Column(name = "CELLPHONE_NO", length = 20, nullable = false)
    private String cellphoneNumber;

    @Column(name = "CREATION_DATE", nullable = false)
    private Date creationDate;

    @Column(name = "CREATED_BY", length = 100, nullable = false)
    private String createdBy;

    @Column(name = "LAST_MODIFICATION_DATE")
    private Date lastModificationDate;

    @Column(name = "LAST_MODIFIED_BY", length = 100)
    private String lastModifiedBy;

    @ManyToMany
    @JoinTable(
            name = "USER_ROLES",
            joinColumns = @JoinColumn(name = "DSS_USER_ID"),
            inverseJoinColumns = @JoinColumn(name = "DSS_ROLE_ID"))
    @NotFound(action = NotFoundAction.IGNORE)
    @ToString.Exclude
    private List<Roles> userRoles;
}
