package com.dss.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "DSS_ROLES")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Roles {

    @Id
    @Column(name = "DSS_ROLE_ID", length = 100, nullable = false)
    private String dssRoleId;

    @Column(name = "USER_ROLE", length = 20, nullable = false)
    private String userRole;

    @Column(name = "CREATION_DATE", nullable = false)
    private Date creationDate;

    @Column(name = "CREATED_BY", length = 100, nullable = false)
    private String createdBy;

    @Column(name = "LAST_MODIFICATION_DATE")
    private Date lastModificationDate;

    @Column(name = "LAST_MODIFIED_BY", length = 100)
    private String lastModifiedBy;

    @ManyToMany(mappedBy = "userRoles")
    private List<Users> users;

    public Roles(String dssRoleId, String userRole, Date creationDate, String createdBy, Date lastModificationDate, String lastModifiedBy) {
        this.dssRoleId = dssRoleId;
        this.userRole = userRole;
        this.creationDate = creationDate;
        this.createdBy = createdBy;
        this.lastModificationDate = lastModificationDate;
        this.lastModifiedBy = lastModifiedBy;
    }
}
