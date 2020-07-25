package org.example.controller;

import org.example.model.Role;
import org.example.model.User;
import org.example.service.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/role/")
public class RoleController {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private RoleService roleService;

    // {prefix}/role/ POST
    @RequestMapping(value = "", method = RequestMethod.POST)
    public Role create(@RequestBody Role newRole) {
        Role role = roleService.save(newRole);
        return role;
    }

    // {prefix}/role/?name=user GET
    @RequestMapping(value = "/", method = RequestMethod.GET, params = {"name"})
    public Role getRoleByName(@RequestParam(name = "name") String name) {
        return roleService.getRoleByName(name);
    }

    // {prefix}/role/3?resource=/user,/rating,/item PATCH
    @RequestMapping(value = "/{id}", method = RequestMethod.PATCH)
    public Role updateRole(@PathVariable(name = "id") Long id, @RequestParam("resource") String resource) {
        Role role = roleService.getBy(id);
        role.setAllowedResource(resource);
        role = roleService.update(role);
        return role;
    }

    // {prefix}/role/3 PUT
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Role updateRole(@PathVariable(name = "id") Long id, @RequestBody Role updatedRole){
        updatedRole.setId(id);
        updatedRole = roleService.update(updatedRole);
        return updatedRole;
    }
}
