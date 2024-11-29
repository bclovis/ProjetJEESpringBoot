package com.example.projetjeespringboot.controller;

import com.example.projetjeespringboot.model.Admin;
import com.example.projetjeespringboot.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/admins")
public class AdminController {

    @Autowired
    private AdminService adminService;

    // Endpoint pour créer ou mettre à jour un admin
    @PostMapping
    public Admin createOrUpdateAdmin(@RequestBody Admin admin) {
        return adminService.saveAdmin(admin);
    }

    // Endpoint pour obtenir un admin par email
    @GetMapping("/{email}")
    public Optional<Admin> getAdminByEmail(@PathVariable String email) {
        return adminService.getAdminByEmail(email);
    }

    // Endpoint pour obtenir tous les admins
    @GetMapping
    public List<Admin> getAllAdmins() {
        return adminService.getAllAdmins();
    }

    // Endpoint pour supprimer un admin par email
    @DeleteMapping("/{email}")
    public void deleteAdmin(@PathVariable String email) {
        adminService.deleteAdminByEmail(email);
    }
}
