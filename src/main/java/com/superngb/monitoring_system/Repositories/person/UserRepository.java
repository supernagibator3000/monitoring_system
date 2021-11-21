package com.superngb.monitoring_system.Repositories.person;

import com.superngb.monitoring_system.Entities.person.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
