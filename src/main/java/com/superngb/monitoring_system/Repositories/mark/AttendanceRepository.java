package com.superngb.monitoring_system.Repositories.mark;

import com.superngb.monitoring_system.Entities.mark.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
}
