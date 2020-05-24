package com.transistorwebservices.libraryapi.user;

/**
 * @Author: coffee@2am
 */
public enum Role {

    ADMIN("Admin"),
    USER("User");


    private String roleName;

    Role(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
