package org.dbs.shop.domain.user;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class User {
    private String userName;
    private String email;

    private String password;

    List<RoleType> roles;
    public User(String userName, String email, RoleType roleType) {
        this.userName = userName;
        this.email = email;
        this.password = "none";
        roles = new ArrayList<>();
        roles.add(roleType);
    }

    public User(String userName, String email, List<RoleType> roles) {
        this.userName = userName;
        this.email = email;
        this.password = "none";
        this.roles = new ArrayList<>();
        Collections.copy(this.roles, roles);
    }

    public String getName() {
        return this.userName;
    }

    public void addRole(RoleType role) {
        roles.add(role);
    }

    public String getEmail() {
        return this.email;
    }

    public String getPassword() {
        return this.password;
    }

    public List<RoleType> getRoles() {
        return Collections.unmodifiableList(this.roles);
    }

    public boolean haveRole(RoleType aRole) {
        for(RoleType role: this.roles) {
            if (role == aRole) {
                return true;
            }
        }
        return false;
    }

    public void changePassword(String password) {
        this.password = password;
    }
}
