package com.superngb.monitoring_system.Repositories.person;

import com.superngb.monitoring_system.Entities.person.Personality;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Personality, Long> {
}
