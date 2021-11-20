package com.superngb.monitoring_system.Repositories.event;

import com.superngb.monitoring_system.Entities.event.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
}
