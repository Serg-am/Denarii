package com.denarii.entity;

public enum Role {
    ADMIN("ROLE_ADMIN"),
    OPERATOR("ROLE_OPERATOR"),
    USER("ROLE_USER");

    private final String roleName;

    Role(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleName() {
        return roleName;
    }
}


