package com.ztp.ztpproject.models;

public class Role {
    /**
     * Compares two roles for the level of privileges.
     * 
     * @param compareTo the role to compare to
     * @param check the role to check
     * @return true if the level of privileges of compareTo is at least as high as
     *         the level of privileges of check, false otherwise
     */
    public static final boolean hasAtLeastPrivileges(RoleList compareTo,RoleList check) {
        return compareTo.ordinal() >= check.ordinal();
    }
}
