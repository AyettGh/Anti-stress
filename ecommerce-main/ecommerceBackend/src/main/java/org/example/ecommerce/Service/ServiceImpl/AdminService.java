package org.example.ecommerce.Service.ServiceImpl;

import org.example.ecommerce.Entity.Admin;

import java.util.List;
import java.util.Optional;

public interface AdminService {
    public List<Admin> getAll();
    public Admin addAdmin(Admin admin);
    public void  deleteAllAdmin();
    public void deleteAdminById(Long id);
    public Boolean exitById(Long id);
    public Optional<Admin> getAdminById(Long id);}
