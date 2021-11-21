package com.superngb.monitoring_system.UseCases.AdminPanel.DataAccessInterfaces;

import com.superngb.monitoring_system.Entities.Role;

public interface AdminPanelRoleDataAccess {
    Role findById(Long id);
}
