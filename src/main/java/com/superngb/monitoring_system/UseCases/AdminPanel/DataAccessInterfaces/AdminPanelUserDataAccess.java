package com.superngb.monitoring_system.UseCases.AdminPanel.DataAccessInterfaces;

import com.superngb.monitoring_system.Entities.person.User;

import java.util.List;

public interface AdminPanelUserDataAccess {
    List<User> getAll();
    List<User> filter(Long id, Long personality,String username,String ROLE_USER, String ROLE_ADMIN);
    User findById(Long id);
    User findByUsername(String username);
    void save(User user);
    User deleteById(Long id);
    User findByPersonalityId(Long personality);
}
