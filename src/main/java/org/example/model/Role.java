package org.example.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "allowed_resource")
    private String allowedResource;
    @Column(name = "allowed_read")
    private Boolean allowedRead;
    @Column(name = "allowed_create")
    private Boolean allowedCreate;
    @Column(name = "allowed_update")
    private Boolean allowedUpdate;
    @Column(name = "allowed_delete")
    private Boolean allowedDelete;
    @JsonIgnore
    @ManyToMany(mappedBy = "roles")
    private Set<User> users;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAllowedResource() {
        return allowedResource;
    }

    public void setAllowedResource(String allowedResource) {
        this.allowedResource = allowedResource;
    }

    public Boolean getAllowedRead() {
        return allowedRead;
    }

    public void setAllowedRead(Boolean allowedRead) {
        this.allowedRead = allowedRead;
    }

    public Boolean getAllowedCreate() {
        return allowedCreate;
    }

    public void setAllowedCreate(Boolean allowedCreate) {
        this.allowedCreate = allowedCreate;
    }

    public Boolean getAllowedUpdate() {
        return allowedUpdate;
    }

    public void setAllowedUpdate(Boolean allowedUpdate) {
        this.allowedUpdate = allowedUpdate;
    }

    public Boolean getAllowedDelete() {
        return allowedDelete;
    }

    public void setAllowedDelete(Boolean allowedDelete) {
        this.allowedDelete = allowedDelete;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return id == role.id &&
                Objects.equals(name, role.name) &&
                Objects.equals(allowedResource, role.allowedResource) &&
                Objects.equals(allowedRead, role.allowedRead) &&
                Objects.equals(allowedCreate, role.allowedCreate) &&
                Objects.equals(allowedUpdate, role.allowedUpdate) &&
                Objects.equals(allowedDelete, role.allowedDelete);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, allowedResource, allowedRead, allowedCreate, allowedUpdate, allowedDelete);
    }
}
