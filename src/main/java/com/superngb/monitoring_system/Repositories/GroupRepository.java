package com.superngb.monitoring_system.Repositories;

import com.superngb.monitoring_system.Entities.Group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<Group, Long> {
    Group findByName(String name);
}
