package org.example.repository;

import org.example.model.Role;

import java.util.List;

public interface RoleDao {
    Role save(Role role);
    Role update(Role role);
    boolean delete(Role role);
    List<Role> getRoles();
    Role getBy(Long id);
    Role getRolebyName(String name);
}
