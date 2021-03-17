package com.projects.FirstProject.models;

public enum UserType {
    ADMIN("Admin"), //id=0
    EMPLOYEE("Employee"); //id=1

    private final String displayName;

    UserType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
