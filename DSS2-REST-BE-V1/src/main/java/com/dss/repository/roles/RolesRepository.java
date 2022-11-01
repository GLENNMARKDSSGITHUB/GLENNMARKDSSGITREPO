package com.dss.repository.roles;

import com.dss.entity.roles.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Glen Mark T Anduiza
 * @version 1.0
 * @since 10/31/2022
 */

@Repository
public interface RolesRepository extends JpaRepository<Roles, Long> {
}
