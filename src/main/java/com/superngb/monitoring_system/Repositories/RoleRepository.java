package com.superngb.monitoring_system.Repositories;

import com.superngb.monitoring_system.Entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
