package com.ecole.interne.ecole.interne.controllers;

import com.ecole.interne.ecole.interne.entities.Learner;
import com.ecole.interne.ecole.interne.entities.User;
import com.ecole.interne.ecole.interne.repositories.LearnerRepository;
import com.ecole.interne.ecole.interne.repositories.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/learner")
public class LearnerController {

    private final LearnerRepository learnercrud;

    private final UserRepository usercrud;

    public LearnerController(LearnerRepository learnercrud, UserRepository usercrud) {
        this.learnercrud = learnercrud;
        this.usercrud = usercrud;
    }


    @GetMapping
    public List<Learner> getAllLearner() {
        List<Learner> learnerList = learnercrud.findAll();
        return learnerList;
    }

    @GetMapping("/{learnerId}")
    public Learner getLearnerById(@PathVariable Long learnerId) {
        return learnercrud.findById(learnerId);

    }

    @PostMapping
    public Learner createLearnerWithUser(@RequestBody Learner learner) {
        User nUser = createUser(learner.getFname(), learner.getLname(), "password");
        nUser.setLname(learner.getLname());
        nUser.setFname(learner.getFname());
        User savedUser = usercrud.save(nUser);
        learner.setUser(savedUser);
        Learner nlearner = learnercrud.save(learner);
        return nlearner;
    }


    @PutMapping("/learner/{learnerId}")
    public Learner updateLearner(@PathVariable Long learnerId, @RequestBody Learner learnerUpdates) {
        Learner existingLearner = learnercrud.findById(learnerId);
        User user = existingLearner.getUser();

        if (learnerUpdates.getFname() != null) {
            existingLearner.setFname(learnerUpdates.getFname());
            user.setFname(learnerUpdates.getFname());
            String username = createUserName(learnerUpdates.getFname(), user.getLname());
            user.setUsername(username);
        }

        if (learnerUpdates.getLname() != null) {
            existingLearner.setLname(learnerUpdates.getLname());
            user.setLname(learnerUpdates.getLname());
            String username = createUserName(user.getFname(), learnerUpdates.getLname());
            user.setUsername(username);
        }

        if (learnerUpdates.getCohort() != null) {
            existingLearner.setCohort(learnerUpdates.getCohort());
        }
        learnercrud.save(existingLearner);

        return existingLearner;
    }


    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Integer id) {
        learnercrud.deleteById(id);
    }


    public User createUser(String fname, String lname, String password) {
        int k = 0;
        String usernamecreated = fname.substring(0, 1).toUpperCase() + lname + k++;
        String passwordcreate = password + k++;
        User usercreated = new User(usernamecreated, passwordcreate);

        return usercreated;
    }

    public String createUserName(String fname, String lname) {
        int k = 0;
        String usernamecreated = fname.substring(0, 1).toUpperCase() + lname + k++;
        return usernamecreated;
    }


}
