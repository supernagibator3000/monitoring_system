package com.superngb.monitoring_system.Enums;

public enum RoleEnum {
    ROLE_USER(1L),
    ROLE_ADMIN(2L);

    private Long id;

    RoleEnum(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
