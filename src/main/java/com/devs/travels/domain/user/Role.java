package com.devs.travels.domain.user;

public enum Role {

    ROLE_ADMIN("Enterprise Administrator"),
    ROLE_EMPLOYEE("Common Employee"),
    ROLE_MANAGER("Enterprise Manager"),
    ROLE_FACILITATOR("Enterprise Facilitator");

    private String label;

    Role(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

}
