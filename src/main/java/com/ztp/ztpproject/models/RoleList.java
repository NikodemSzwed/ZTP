package com.ztp.ztpproject.models;

/**
 * The RoleList enum represents the different roles available in the system.
 *
 * The roles determine the level of privileges that a user has. The roles are
 * defined in order of hierarchy, with ADMIN having the highest level of
 * privileges, followed by MODERATOR, and then USER with the lowest level.
 *
 * Roles can be used to control access to different parts of the system,
 * ensuring that users have the appropriate permissions for their actions.
 *
 * @version 1.0
 */
public enum RoleList {
    ADMIN,
    MODERATOR,
    USER
}
