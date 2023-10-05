package com.ecole.interne.ecole.interne.controllers;

import com.ecole.interne.ecole.interne.entities.Former;
import com.ecole.interne.ecole.interne.entities.Learner;
import com.ecole.interne.ecole.interne.entities.User;
import com.ecole.interne.ecole.interne.repositories.FormerRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/former")
public class FormerController {

    private final FormerRepository formercrud;

    public FormerController(FormerRepository formercrud) {
        this.formercrud = formercrud;
    }


    @PostMapping
    public Former createFormerWithUser(@RequestBody Former former) {
        User nUser = createUser(former.getFname(), former.getLname(), "password");
        nUser.setFormer(former);
        nUser.setFname(former.getFname());
        nUser.setLname(former.getLname());
        former.setUser(nUser);
        Former nformer = formercrud.save(former);
        return nformer;
    }


    @GetMapping
    public List<Former> getAllFormer() {
        List<Former> formersList = formercrud.findAll();
        return formersList;
    }

    @GetMapping("/{formerId}")
    public Former getFormerById(@PathVariable Long formerId) {
        return formercrud.findById(formerId);

    }


    @PutMapping("/{formerId}")
    public Former updateFormer(@PathVariable Long formerId, @RequestBody Former formerUpdates) {
        Former existingFormer = formercrud.findById(formerId);
        User user = existingFormer.getUser();

        if (formerUpdates.getFname() != null) {
            existingFormer.setFname(formerUpdates.getFname());
            user.setFname(formerUpdates.getFname());
            String username = updateUsername(formerUpdates.getFname(), user.getLname());
            user.setUsername(username);
        }

        if (formerUpdates.getLname() != null) {
            existingFormer.setLname(formerUpdates.getLname());
            user.setLname(formerUpdates.getLname());
            String username = updateUsername(user.getFname(), formerUpdates.getLname());
            user.setUsername(username);
        }

        if (formerUpdates.getCohort() != null) {
            existingFormer.setCohort(formerUpdates.getCohort());
        }
        formercrud.save(existingFormer);

        return existingFormer;
    }


    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Integer id) {
        formercrud.deleteById(id);
    }


    public User createUser(String fname, String lname, String password) {
        String usernamecreated = fname.substring(0, 1).toUpperCase() + lname;
        String passwordcreate = password;
        User usercreated = new User(usernamecreated, passwordcreate);

        return usercreated;
    }

    public String updateUsername(String fname, String lname) {
        int k = 0;
        String usernamecreated = fname.substring(0, 1).toUpperCase() + lname + k++;

        return usernamecreated;
    }


}
