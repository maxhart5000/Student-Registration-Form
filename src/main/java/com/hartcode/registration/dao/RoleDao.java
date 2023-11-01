package com.hartcode.registration.dao;

import com.hartcode.registration.entity.Role;

public interface RoleDao {

    public Role findRoleByName(String theRoleName);
}
