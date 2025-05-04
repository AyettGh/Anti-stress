package org.example.ecommerce.Service;

import org.example.ecommerce.Entity.Admin;
import org.example.ecommerce.Repository.AdminRepository;
import org.example.ecommerce.Service.ServiceImpl.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminRepository adminRepository;
    @Override
    public List<Admin> getAll() {
        return adminRepository.findAll();
    }

    @Override
    public Admin addAdmin(Admin admin) {
        return adminRepository.save(admin);
    }

    @Override
    public void deleteAllAdmin() {
        adminRepository.deleteAll();
    }

    @Override
    public void deleteAdminById(Long id) {
        adminRepository.deleteById(id);
    }

    @Override
    public Boolean exitById(Long id) {
        return adminRepository.existsById(id);
    }

    @Override
    public Optional<Admin> getAdminById(Long id) {
        return adminRepository.findById(id);
    }
}
