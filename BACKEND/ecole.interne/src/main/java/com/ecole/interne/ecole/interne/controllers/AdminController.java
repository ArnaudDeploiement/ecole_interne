package com.ecole.interne.ecole.interne.controllers;

import com.ecole.interne.ecole.interne.entities.Admin;
import com.ecole.interne.ecole.interne.entities.User;
import com.ecole.interne.ecole.interne.repositories.AdminRepository;
import com.ecole.interne.ecole.interne.repositories.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final AdminRepository admincrud;
    private final UserRepository usercrud;


    public AdminController(AdminRepository admincrud, UserRepository usercrud) {
        this.admincrud = admincrud;
        this.usercrud = usercrud;
    }

    @GetMapping
    public List<Admin> getAllAdmin() {
        List<Admin> adminList = admincrud.findAll();
        return adminList;


    }

    @GetMapping("/{adminId}")
    public Optional<Admin> getAdminById(@PathVariable Integer adminId) {
        return admincrud.findById(adminId);

    }

    @PostMapping
    public Admin createAdminWithUser(@RequestBody Admin admin) {
        User nUser = createUser(admin.getFname(), admin.getLname(), "password");
        nUser.setAdmin(admin);
        nUser.setFname(admin.getFname());
        nUser.setLname(admin.getLname());
        User saveuser = usercrud.save(nUser);
        admin.setUser(saveuser);
        Admin nAdmin = admincrud.save(admin);
        return nAdmin;
    }


    @PutMapping("/{adminId}")
    public Admin updateAdmin(@PathVariable Long adminId, @RequestBody Admin adminUpdates) {
        Admin existingAdmin = admincrud.findById(adminId);
        User user = existingAdmin.getUser();

        if (adminUpdates.getFname() != null) {
            existingAdmin.setFname(adminUpdates.getFname());
            user.setFname(adminUpdates.getFname());
            String username = updateUsername(adminUpdates.getFname(), user.getLname());
            user.setUsername(username);

        }

        if (adminUpdates.getLname() != null) {
            existingAdmin.setLname(adminUpdates.getLname());
            user.setLname(adminUpdates.getLname());
            String username = updateUsername(adminUpdates.getLname(), user.getFname());
            user.setUsername(username);
        }

        admincrud.save(existingAdmin);

        return existingAdmin;
    }


    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Integer id) {
        admincrud.deleteById(id);
    }


    public User createUser(String fname, String lname, String password) {
        int k = 0;
        String usernamecreated = fname.substring(0, 1).toUpperCase() + lname + k++;
        String passwordcreate = password + k++;
        User usercreated = new User(usernamecreated, passwordcreate);

        return usercreated;
    }

    public String updateUsername(String fname, String lname) {
        int k = 0;
        String usernamecreated = fname.substring(0, 1).toUpperCase() + lname + k++;

        return usernamecreated;
    }

    public String updatepassword(String password) {
        int k = 0;
        String passwordupdated = password + k++;
        return passwordupdated;
    }

}
