package com.ecole.interne.ecole.interne.controllers;

import com.ecole.interne.ecole.interne.entities.Admin;
import com.ecole.interne.ecole.interne.entities.Former;
import com.ecole.interne.ecole.interne.entities.Learner;
import com.ecole.interne.ecole.interne.entities.User;
import com.ecole.interne.ecole.interne.repositories.AdminRepository;
import com.ecole.interne.ecole.interne.repositories.FormerRepository;
import com.ecole.interne.ecole.interne.repositories.LearnerRepository;
import com.ecole.interne.ecole.interne.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserControllers {

    private final UserRepository usercrud;

    private final LearnerRepository learnercrud;

    private final FormerRepository formercrud;

    private final AdminRepository admincrud;


    @Autowired
    public UserControllers(UserRepository usercrud, LearnerRepository learnercrud, FormerRepository formercrud, AdminRepository admincrud) {
        this.usercrud = usercrud;
        this.learnercrud = learnercrud;
        this.formercrud = formercrud;
        this.admincrud = admincrud;
    }

    @GetMapping
    public List<User> getAllUsers() {
        List<User> userList = usercrud.findAll();
        return userList;
    }

    @GetMapping("/{userId}")
    public User getUserById(@PathVariable Long userId) {
        return usercrud.findById(userId);

    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        User usercreated = createUser(user.getFname(), user.getLname());
        return usercrud.save(usercreated);
    }

    @PutMapping("/{userId}")
    public User updateUser(@PathVariable Long userId, @RequestBody User user) {
        User existingUser = usercrud.findById(userId);

        if (user.getLname() != null) {
            existingUser.setLname(user.getLname());
        }

        if (user.getFname() != null) {
            existingUser.setFname(user.getFname());
        }


        if (user.getUsername() != null) {
            existingUser.setUsername(user.getUsername());
        }

        if (user.getPassword() != null) {
            existingUser.setPassword(user.getPassword());
        }

        if (existingUser.getFormer() != null) {
            Former former = existingUser.getFormer();
            if (user.getLname() != null) {
                former.setLname(user.getLname());
            }
            if (user.getFname() != null) {
                former.setFname(user.getFname());
            }
        } else if (existingUser.getAdmin() != null) {
            Admin admin = existingUser.getAdmin();
            if (user.getLname() != null) {
                admin.setLname(user.getLname());
            }
            if (user.getFname() != null) {
                admin.setFname(user.getFname());
            }
        } else if (existingUser.getLearner() != null) {
            Learner learner = existingUser.getLearner();
            if (user.getLname() != null) {
                learner.setLname(user.getLname());
            }
            if (user.getFname() != null) {
                learner.setFname(user.getFname());
            }
        }

        usercrud.save(existingUser);
        return existingUser;

    }


    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Integer id) {
        usercrud.deleteById(id);
    }


    @PutMapping("/{userId}/role")
    public ResponseEntity<String> updateRole(@PathVariable Integer userId, @RequestParam String role) {
        Optional<User> optionalUser = usercrud.findById(userId);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();

            switch (role.toLowerCase()) {
                case "learner":
                    Learner learner = new Learner();
                    learner.setUser(user);
                    user.setLearner(learner);
                    learner.setFname(user.getFname());
                    learner.setLname(user.getLname());
                    learnercrud.save(learner);
                    break;
                case "former":
                    Former former = new Former();
                    former.setUser(user);
                    user.setFormer(former);
                    former.setFname(user.getFname());
                    former.setLname(user.getLname());
                    formercrud.save(former);
                    break;
                case "admin":
                    Admin admin = new Admin();
                    admin.setUser(user);
                    user.setAdmin(admin);
                    admin.setFname(user.getFname());
                    admin.setLname(user.getLname());
                    admincrud.save(admin);
                    break;
                default:
                    return ResponseEntity.badRequest().body("Le rôle spécifié n'est pas valide.");
            }

            usercrud.save(user);

            return ResponseEntity.ok("Rôle de l'utilisateur mis à jour avec succès.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/{userId}/role")
    public ResponseEntity<String> removeRole(@PathVariable Integer userId) {
        Optional<User> optionalUser = usercrud.findById(userId);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();

            if (user.getLearner() != null) {
                learnercrud.delete(user.getLearner());
                user.setLearner(null);
            }

            if (user.getFormer() != null) {
                formercrud.delete(user.getFormer());
                user.setFormer(null);
            }

            if (user.getAdmin() != null) {
                admincrud.delete(user.getAdmin());
                user.setAdmin(null);
            }

            usercrud.save(user);

            return ResponseEntity.ok("Association de rôle supprimée avec succès.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public User createUser(String fname, String lname) {
        int k = 0;
        String usernamecreated = fname.substring(0, 1).toUpperCase() + lname + k++;
        String passwordcreate = "password" + k++;
        User usercreated = new User(fname,lname,usernamecreated,passwordcreate);

        return usercreated;
    }

}



