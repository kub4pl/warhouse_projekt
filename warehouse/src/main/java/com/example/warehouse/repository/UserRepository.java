package com.example.warehouse.repository;

import com.example.warehouse.model.Role;
import com.example.warehouse.model.User;
import com.example.warehouse.repository.repositoryInterface.AccidentShortDescription;
import com.example.warehouse.repository.repositoryInterface.RoleDescription;
import com.example.warehouse.repository.repositoryInterface.UserNameDescription;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {


    Optional<User> findByUsername(String username);
    Optional<User> findByToken(String token);

    boolean existsByUsername(String username);
    boolean existsByToken(String token);

    @Query("select u.username from User u")
    Page<String> findUserName(Pageable pageable);

    @Query("select u.username as username, u.id as id from User u")
    Page<UserNameDescription> findUserNameAndId (Pageable pageable);

    @Query("select u.username as username, u.id as id, r.type as type from User u inner join Role r on u.role=r.id")
    Page<RoleDescription> findUserNameAndIdAndRole (Pageable pageable);

    @Query("select u.username as username, r.type as roleType from User u left join Role r on u.role.id= r.id " +
                "where (:username is null or upper(u.username) like CONCAT('%',:username,'%')) "+
                "and (:roleType is null or upper(r.type) like CONCAT('%',:roleType,'%')) ")
        Page<AccidentShortDescription> findAll(@Param("username") String username, @Param("roleType") String roleType, Pageable pageable);

//        @Query("select a from Accident a left join Company c on c.id= a.company.id " +
//                "where (:companyId is null or c.id= :companyId) " +
//                "and (:accidentType is null or a.accidentType = :accidentType) " +
//                "and (:status is null or a.status = :status) ")
//        Page<AccidentShortDescription> findAll(@Param("companyId") Long companyId, @Param("accidentType") AccidentType accidentType, @Param("status") AccidentStatus status, Pageable pageable);
}