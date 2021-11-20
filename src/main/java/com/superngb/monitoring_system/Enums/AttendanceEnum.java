package com.superngb.monitoring_system.Enums;

public enum AttendanceEnum {
//    WAS_ABSENT_FOR_NO_GOOD_REASON("не был"),
//    WAS_ABSENT_FOR_A_GOOD_REASON("не был по уважительной причине"),
//    WAS_ILL("болел"),
//    ATTENDED("был");

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
