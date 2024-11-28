package com.example.projetjeespringboot.Service;

import com.example.projetjeespringboot.Model.Admin;
import com.example.projetjeespringboot.Repository.AdminRepository;
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
