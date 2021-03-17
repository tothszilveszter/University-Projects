package com.projects.FirstProject.controllers;

import com.projects.FirstProject.data.UserRepository;
import com.projects.FirstProject.models.User;
import com.projects.FirstProject.models.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("admin")
public class AdminController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public String displayUsers(Model model){

        model.addAttribute("title", "Employees");
        model.addAttribute("employees", userRepository.findAll());
        return "admin/index"; //cause it is in the ,,events,, folder
    }

    @GetMapping("update")
    public String displayUpdateEmployeeForm(Model model){
        model.addAttribute("title", "Update employee");
        model.addAttribute(new User());
        model.addAttribute("userTypes", UserType.values());
        return "admin/update";
    }

    @PostMapping("update")
    public String processUpdateEmployeeForm(@ModelAttribute User user, Model model){
        /*//thorough validation needed
        String msg="";
        int userId = user.getId();
        System.out.println(userId);

        Optional<User> result = userRepository.findById(userId);

        if (user.getUserEmail().equals(""))
            msg += "Email field problems!\n";
        if (userId > 0)
        {
            if (result.isEmpty()){
                msg += "Not a valid ID";
            }
        }
        if (!msg.equals("")) {
            model.addAttribute("title", "Update employee");
            model.addAttribute("errorMsg", msg);
            return "admin/update";
        }

        if (result.isPresent()) {
            User oldUser = result.get();
            oldUser.setUserEmail(user.getUserEmail());
            oldUser.setUserType(user.getUserType());

            userRepository.deleteById(userId);
            userRepository.save(oldUser);
            return "admin";
        }

        model.addAttribute("errorMsg", "Update not successful!");
        model.addAttribute("userTypes", UserType.values());
        return "admin/update";*/
        System.out.println(user.getId());
        model.addAttribute("title", "Update");
        Iterable<User> userList = userRepository.findAll();
        model.addAttribute("title", "Update User");
        if (user.getUserEmail().isEmpty()) {
            model.addAttribute("errorMsg", "Invalid fields!");
            return "admin/update";
        } else {
            for (User x : userList) {
                if (x.getId()==user.getId()) {
                    x.setUserEmail(user.getUserEmail());
                    x.setUserType(user.getUserType());
                    userRepository.deleteById(user.getId());
                    userRepository.save(x);
                }
            }
        }
        return "redirect:";
    }


    @GetMapping("toBeDone")
    public String displayForm(){

        return "toBeDone";
    }
}
