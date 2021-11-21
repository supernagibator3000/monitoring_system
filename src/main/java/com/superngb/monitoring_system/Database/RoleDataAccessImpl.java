package com.superngb.monitoring_system.Database;

import com.superngb.monitoring_system.Entities.Role;
import com.superngb.monitoring_system.Repositories.RoleRepository;
import com.superngb.monitoring_system.UseCases.AdminPanel.DataAccessInterfaces.AdminPanelRoleDataAccess;

public class RoleDataAccessImpl implements AdminPanelRoleDataAccess {

    private final RoleRepository roleRepository;

    public RoleDataAccessImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role findById(Long id) {
        return roleRepository.findById(id).orElse(null);
    }
}
