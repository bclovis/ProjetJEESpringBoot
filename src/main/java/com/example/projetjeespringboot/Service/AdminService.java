package com.example.projetjeespringboot.service;

import com.example.projetjeespringboot.model.Admin;
import com.example.projetjeespringboot.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    @Autowired
    private AdminRepository adminRepository;

    public Admin saveAdmin(Admin admin)
    {
        return adminRepository.save(admin);
    }

    /*
    public Admin updateAdmin(Admin admin, String adminId)
    {
        //
    }
    */

    public void deleteAdminById(String adminId)
    {
        adminRepository.deleteById(adminId);
    }
}
