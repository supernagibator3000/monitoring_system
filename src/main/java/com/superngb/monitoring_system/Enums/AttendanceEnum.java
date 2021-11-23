package com.superngb.monitoring_system.Enums;

public enum AttendanceEnum {
    EMPTY("пусто"),
    ABSENT("не был"),
    REASON("не был по уважительной причине"),
    ILL("болел"),
    ATTENDED("был");

    private String description;

    AttendanceEnum(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
