package com.devs.travels.domain;

public enum RoleEnum {

    ROLE_ADMIN("Enterprise Administrator"),
    ROLE_EMPLOYEE("Common Employee"),
    ROLE_MANAGER("Enterprise Manager"),
    ROLE_FACILITATOR("Enterprise Facilitator");

    private String label;

    RoleEnum(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

}
