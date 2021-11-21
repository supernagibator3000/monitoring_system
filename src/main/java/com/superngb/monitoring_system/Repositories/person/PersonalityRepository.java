package com.superngb.monitoring_system.Repositories.person;

import com.superngb.monitoring_system.Entities.person.Personality;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonalityRepository extends JpaRepository<Personality, Long> {
    Personality findByFirstName(String firstName);
    Personality findBySecondName(String secondName);
    Personality findByMiddleName(String middleName);
    Personality findByEmail(String email);
}
