package com.ztp.ztpproject.models;

enum RoleList {
    ADMIN,
    MODERATOR, 
    USER
}

class Role {
    public boolean hasPrivileges(RoleList compareTo,RoleList check) {
        return compareTo.ordinal() >= check.ordinal();
    }
}