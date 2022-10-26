package com.dss.repository;

import com.dss.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RegistrationRepository extends JpaRepository<Users, Long> {

    @Query(value = "SELECT * FROM DSS_USERS WHERE EMAIL = :email", nativeQuery = true)
    List<Users> findUserByEmail(@Param("email") String email);

    @Query(value = "SELECT * FROM DSS_USERS WHERE CELLPHONE_NO = :cellphoneNumber", nativeQuery = true)
    List<Users> findUserByCellphoneNumber(@Param("cellphoneNumber") String cellphoneNumber);

    @Query(value = "SELECT COUNT(*) FROM DSS_USERS", nativeQuery = true)
    int maxDssId();
}
