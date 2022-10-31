package com.dss.repository.user;

import com.dss.entity.user.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {

    @Query(value = "SELECT * FROM DSS_USERS WHERE DSS_USER_ID = :dssUserId", nativeQuery = true)
    List<Users> findUserByUserId(@Param("dssUserId") String dssUserId);

    @Query(value = "SELECT * FROM DSS_USERS WHERE EMAIL = :email", nativeQuery = true)
    List<Users> findUserByEmail(@Param("email") String email);

    @Query(value = "SELECT * FROM DSS_USERS WHERE EMAIL = :email", nativeQuery = true)
    Users findUserByEmailAddress(@Param("email") String email);

    @Query(value = "SELECT * FROM DSS_USERS WHERE CELLPHONE_NO = :cellphoneNumber", nativeQuery = true)
    List<Users> findUserByCellphoneNumber(@Param("cellphoneNumber") String cellphoneNumber);

    @Query(value = "SELECT MAX(DSS_USER_ID) FROM DSS_USERS", nativeQuery = true)
    String maxUserId();
}
