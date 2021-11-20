package com.superngb.monitoring_system.Enums;

public enum ScoreEnum {
    TWO("неудовлетворительно"),
    THREE("удовлетворительно"),
    FOUR("хорошо"),
    FIVE("отлично");

    private String description;

    ScoreEnum(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
