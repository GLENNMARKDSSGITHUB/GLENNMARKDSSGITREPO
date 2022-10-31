package com.dss.entity.roles;

import com.dss.entity.user.Users;
import lombok.*;

import javax.persistence.*;

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
