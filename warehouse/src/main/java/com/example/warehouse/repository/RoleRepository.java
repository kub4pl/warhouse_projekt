package com.example.warehouse.repository;
import com.example.warehouse.model.Role;
import com.example.warehouse.model.RoleType;
import com.example.warehouse.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    public Optional<Role> findByType(RoleType type);

    boolean existsByType(RoleType type);
}
