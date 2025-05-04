package org.example.ecommerce.Entity;


import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;

@Entity
@DiscriminatorValue("ADMIN")
public class Admin extends User implements Serializable {
    @ElementCollection
    @CollectionTable(name = "admin_permission", joinColumns = @JoinColumn(name = "admin_id"))
    @Column(name = "permission")
    private List<String> permissions;

    private String adminSpecificField;

    public Admin() {
        super();
    }

    public Admin(String name, String email, String role, List<String> permissions, String adminSpecificField) {
        super(name, email, role);
        this.permissions = permissions;
        this.adminSpecificField = adminSpecificField;
    }

    public List<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<String> permissions) {
        this.permissions = permissions;
    }

    public String getAdminSpecificField() {
        return adminSpecificField;
    }

    public void setAdminSpecificField(String adminSpecificField) {
        this.adminSpecificField = adminSpecificField;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "permissions=" + permissions +
                ", adminSpecificField='" + adminSpecificField + '\'' +
                '}';
    }
}

