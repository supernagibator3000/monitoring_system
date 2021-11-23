package com.superngb.monitoring_system.UseCases.AdminPanel.DataAccessInterfaces;

import com.superngb.monitoring_system.Entities.event.Checkpoint;

import java.util.List;

public interface AdminPanelCheckpointDataAccess {
    List<Checkpoint> getAll();
    List<Checkpoint> filter(Long id, String name, Long subjectId);
    Checkpoint findById(Long id);
    Checkpoint findByName(String name);
    Checkpoint findBySubjectId(Long subjectId);
    void save(Checkpoint checkpoint);
    Checkpoint deleteById(Long id);
}
