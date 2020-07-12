package org.example.service;

import org.example.model.Role;
import org.example.repository.RoleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {
    @Autowired
    private RoleDao roleDao;

    public Role save(Role role) {
        return roleDao.save(role);
    }

    public Role update(Role role) {
        return roleDao.update(role);
    }

    public boolean delete(Role role) {
        return roleDao.delete(role);
    }

    public List<Role> getRoles() {
        return roleDao.getRoles();
    }

    public Role getBy(Long id) {
        return roleDao.getBy(id);
    }

    public Role getRoleByName(String name) {
        return roleDao.getRolebyName(name);
    }
}
