package com.dss.entity.roles;

import com.dss.entity.user.Users;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author Glen Mark T Anduiza
 * @version 1.0
 * @since 10/31/2022
 */

@Entity
@Table(name = "DSS_ROLES")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Roles {

    @Id
    @Column(name = "ROLE_ID", length = 100, nullable = false)
    private String dssRoleId;

    @Column(name = "USER_ROLE", length = 20, nullable = false)
    private String userRole;

    @ManyToOne
    private Users user;

    public Roles(String dssRoleId, String userRole) {
        this.dssRoleId = dssRoleId;
        this.userRole = userRole;
    }
}